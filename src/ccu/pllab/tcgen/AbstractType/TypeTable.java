package ccu.pllab.tcgen.AbstractType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.PapyrusCDParser.SingleCDParser;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.launcher.BlackBoxLauncher;
import ccu.pllab.tcgen.launcher.WhiteBoxLauncher;
import ccu.pllab.tcgen.transform.Splitter;

public class TypeTable {
	private HashMap<String, VariableType> typelist;
	private HashMap<String, String> hashIDtoType;
	private BasicTypeMethodCall baseTypemethodCall;

	public TypeTable() {
		typelist = new HashMap<String, VariableType>();
		hashIDtoType = new HashMap<String, String>();
		init();
		baseTypemethodCall = new BasicTypeMethodCall();
	}

	public void init() {
		IntType intType = new IntType();
		StringType stringType = new StringType();
		BooleanType booleanType = new BooleanType();
		VoidType voidType = new VoidType();
		CharType charType = new CharType();
		RealType realType = new DoubleType();// 以後看要不要改
		FloatType floatType = new FloatType();
		DoubleType doubleType = new DoubleType();
		CollectionType collectionType = new CollectionType(); // ocl type
		SequenceType sequenceType = new SequenceType(); // ocl type

		typelist.put("int", intType); // 0
		hashIDtoType.put("int", "int");

		typelist.put("String", stringType); // 1
		hashIDtoType.put("String", "String");

		typelist.put("boolean", booleanType); // 2
		hashIDtoType.put("boolean", "boolean");

		typelist.put("void", voidType); // 3
		hashIDtoType.put("void", "void");

		typelist.put("char", charType); // 4
		hashIDtoType.put("char", "char");

		typelist.put("real", realType); // 5
		hashIDtoType.put("real", "real");

		typelist.put("float", floatType); // 6
		hashIDtoType.put("float", "float");

		typelist.put("double", doubleType); // 7
		hashIDtoType.put("double", "double");

		// ocl collection type
		typelist.put("Collection", collectionType);
		hashIDtoType.put("Collection", "Collection");

		typelist.put("Sequence", sequenceType);
		hashIDtoType.put("Sequence", "Sequence");
	}

	public void add(VariableType type) {
		if(!this.containsType(type.typeName, null)) {
			typelist.put(type.typeName, type);
			hashIDtoType.put(type.typeID, type.typeName);
		}
	}

	public boolean containsType(String typeName, String typeID) {
		if (this.typelist.get(this.baseTypeStrFix(typeName)) != null)
			return true;
		else if (typelist.get(hashIDtoType.get(typeID)) != null)
			return true;
		else
			return false;
	}

	public HashMap<String, VariableType> getTypeList() {
		return this.typelist;
	}

	public VariableType get(String typeName, String typeID) {
		if (typeName.equals("int") || typeName.equals("Int") || typeName.equals("Integer"))
			return typelist.get("int");
		else if (typeName.equals("string") || typeName.equals("String"))
			return typelist.get("String");
		else if (typeName.equals("boolean") || typeName.equals("Boolean"))
			return typelist.get("boolean");
		else if (typeName.equals("void") || typeName.equals("OclVoid"))
			return typelist.get("void");
		else if (typeName.equals("char") || typeName.equals("Char"))
			return typelist.get("char");
		else if (typeName.equals("real") || typeName.equals("Real"))
			return typelist.get("double");
		else if (typeName.equals("float") || typeName.equals("Float"))
			return typelist.get("float");
		else if (typeName.equals("double") || typeName.equals("Double"))
			return typelist.get("double");
		else {
			VariableType thetype = null;

			thetype = typelist.get(Utility.titleToUppercase(typeName));

			if (thetype == null) {
				thetype = typelist.get(hashIDtoType.get(typeID));
			}

			if (thetype != null) {
				if (thetype.typeName.contains("ArrayList(")) {
					return (ArrayListType) thetype;
				} else if (thetype.typeName.contains("Array[(")) {
					return (ArrayType) thetype;
				} else if (thetype.typeName.contains("Sequence(")) {
					return (SequenceType) thetype;
				} else {
					return (UserDefinedType) thetype;
				}
			}

			// if there isn't the type in the typeTable, search class file and parse

			if (thetype == null) {
				File newClassUml = new File(Main.CurrentEditorProjectPath + "/spec/" + typeName + ".uml");
				File newClassOCL = new File(Main.CurrentEditorProjectPath + "/spec/" + typeName + ".ocl");
				switch (Main.TestType) {
				case "WhiteBox":
					File newClassSrcCode = new File(Main.CurrentEditorProjectPath + "/spec/" + typeName + ".java");
					WhiteBoxLauncher classParser_W = new WhiteBoxLauncher(newClassSrcCode, newClassOCL, newClassUml,
							tcgenplugin_2.handlers.WhiteBoxHandler.whiteBoxTest.getPmonitor());
					classParser_W.genWhiteBoxTestScripts(false);
					break;
				case "BlackBox":
				default:
					BlackBoxLauncher classParser_B = new BlackBoxLauncher(newClassOCL, newClassUml,
							tcgenplugin_2.handlers.BlackBoxHandler.blackBoxTest.getPmonitor());
					classParser_B.genBlackBoxTestScripts(false);
					break;
				}

				thetype = typelist.get(typeName);
			}

			return thetype;
		} // else
	}

	private String baseTypeStrFix(String typeName) {
		if (typeName.equals("int") || typeName.equals("Integer"))
			return "int";
		else if (typeName.equals("string") || typeName.equals("String"))
			return "String";
		else if (typeName.equals("boolean") || typeName.equals("Boolean"))
			return "boolean";
		else if (typeName.equals("void") || typeName.equals("OclVoid"))
			return "void";
		else if (typeName.equals("char") || typeName.equals("Char"))
			return "char";
		else if (typeName.equals("real") || typeName.equals("Real"))
			return "double";
		else if (typeName.equals("float") || typeName.equals("Float"))
			return "float";
		else if (typeName.equals("double") || typeName.equals("Double"))
			return "double";
		else
			return typeName;
	}

	// 2021 0424 從SingleCDParser 移植
	// 創建自定義的CollectionType結構
	public CollectionType createCollectionType(String colleactiontypeName, VariableType element) {

		System.out.println("Collection String: " + colleactiontypeName);
		System.out.println(colleactiontypeName + "::" + element);

		CollectionType col_var = (CollectionType) this.typelist.get(colleactiontypeName);
		col_var.setElement(element);
		return col_var;

	} // createCollectionType()

	// 2021 0424 從SingleCDParser 移植
	// 創建自定義的ArrayListType結構
	public ArrayListType createArrayListType(String s) {
		System.out.println("ArrayList String: " + s);
		String type_str = null;
		String element = s.substring(s.indexOf("<") + 1, s.length() - 1);
		System.out.println(s + "::" + element);
		if (element.contains("<") && element.contains(">"))
			type_str = s.substring(0, s.indexOf("<"));

		System.out.println(s + "::" + type_str + "::" + element);
		if (Main.typeTable.containsType(element, element)) {
			return new ArrayListType(Main.typeTable.get(element, element));
		} else if (type_str != null && type_str.equals("ArrayList")) // 多維 muti-D
			return new ArrayListType(createArrayListType(element));
		else
			return new ArrayListType(createUserDefinedType(element));
	} // createArrayListType()

	// 2021 0424 從SingleCDParser 移植
	public UserDefinedType createUserDefinedType(String s) {
		// 去spec資料夾找同名的類別圖，並傳入PapyrusCDParser分析
		// 從ClassInfo拿資料
		// 創建結構並回傳
		Path CDumlPath = Paths.get(DataWriter.output_folder_path + "/spec/" + s + ".uml");
		File uml = CDumlPath.toFile();
		Splitter split = new Splitter(uml);
		File cdUml = null;
		try {
			cdUml = split.split2CDuml();
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SingleCDParser parser = new SingleCDParser();
		Document ref_doc = parser.init(cdUml);
		NodeList nList = ref_doc.getElementsByTagName("packagedElement");
		Node node = nList.item(0); // 只有一個class (如果那張圖有多個class，之後這邊要改
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			String type_name = e.getAttribute("name"); // class name
			String type_id = e.getAttribute("xmi:id"); // class id

			if (!Main.typeTable.containsType(type_name, type_id)) {
				try {
					parser.Parse(ref_doc);
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} // if

			return ((UserDefinedType) Main.typeTable.get(type_name, type_id));

		} // if

		return null;
	} // createUserDefinedType()

	public SymbolTable getBaseTypemethodCallSymbolTable(VariableType type) {
		return this.baseTypemethodCall.getSomeTypeSymbolTable(type);
	}

	public String getBaseTypemethodCallCLP() {
		return this.baseTypemethodCall.realMethodCLP() + this.baseTypemethodCall.intgMethodCLP()
				+ this.baseTypemethodCall.stringMethodCLP() + this.baseTypemethodCall.collectionMethodCLP()
				+ this.baseTypemethodCall.booleanMethodCLP();
	}

//	public String printTypeTableInfo() {
//		String s = "vvvvv測試TypeTable內容資訊vvvvv\n";
//		
//		for(int i = 0; i< this.typelist.size();i++) {
//			s =s+"NO."+i+" >>> "+typelist.get(i).toString()+"\n";
//		}
//		
//		s = s+"^^^^^測試TypeTable內容資訊^^^^^";
//		return s ;
//	}

}
