package ccu.pllab.tcgen.AbstractType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.PapyrusCDParser.ClassInfo;
import ccu.pllab.tcgen.PapyrusCDParser.OperationInfo;
import ccu.pllab.tcgen.PapyrusCDParser.VariableInfo;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.clgGraph2Path.CLGPath;
import ccu.pllab.tcgen.clgGraph2Path.CLGPathEnumerator;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class UserDefinedType extends VariableType {
	ClassInfo content;
	CLGGraph invCLG;
	static private String transformCLP = "";

	public UserDefinedType(String name, String id) {
		super.typeName = name;
		super.typeID = id;
		super.symbolTable = null;
		labelingPriority = 4;
	}

	public UserDefinedType(String type, ClassInfo info) {
		super.typeName = type;
		content = info;
		this.symbolTable = makeSymbolTable(info);
		labelingPriority = 4;
	}

	public UserDefinedType(String type) {
		typeName = type;
		labelingPriority = 4;
	}

	public UserDefinedType() {
		// TODO Auto-generated constructor stub
		labelingPriority = 4;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getTypeID() {
		return content.getID();
	}

	public void setClassInfo(ClassInfo info) {
		content = info;
		this.symbolTable = makeSymbolTable(info);
	}

	public ClassInfo getClassInfo() {
		return content;
	}

	public CLGGraph getInvCLG() {
		return this.invCLG;
	}

	public void addInvCLG(CLGGraph clg) {
		this.invCLG = clg;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// return typeName ;
		String s;
		s = "Name: " + typeName + ",,, ID: " + typeID;
		if (content != null) {
			for (int j = 0; content.getProperties() != null && j < content.getProperties().size(); j++) {
				VariableInfo p = content.getProperties().get(j);
				s = s + p.getName() + ":" + p.getType().toString() + " ";
			}

			s = s + "\nOperations: ";
			for (int k = 0; content.getOperations() != null && k < content.getOperations().size(); k++) {
				OperationInfo o = content.getOperations().get(k);
				s = s + " " + o.getReturnType().getType().typeName + " " + o.getName();
				s = s + "Parameter: ";
				for (int index = 0; o.getParameter() != null && index < o.getParameter().size(); index++) {
					VariableInfo p = o.getParameter().get(index);
					// s = s + "::" + p.getType().toString() + " " + p.getName();
				}
				s = s + "\n";
			}
		} // if

		return s;
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return getTypeName();
	}

	private SymbolTable makeSymbolTable(ClassInfo c) {
		// 開始做symbol table
		SymbolTable symbolTableTemp = new SymbolTable(c.getName());

		for (int j = 0; c.getProperties() != null && j < c.getProperties().size(); j++) {
			VariableInfo p = c.getProperties().get(j);
			// put property into symboltalbe
			VariableToken variable = new VariableToken(p.getName(), p.getType());
			symbolTableTemp.addAttribute(variable);
		}

		for (int k = 0; c.getOperations() != null && k < c.getOperations().size(); k++) {
			OperationInfo o = c.getOperations().get(k);
			MethodToken method = new MethodToken(o.getName());
			String varStr = o.getReturnType().getClass().toString().substring(0,
					o.getReturnType().getClass().toString().length() - 4);

			// 處理method的回傳值
			method.setReturnType(o.getReturnType().getType());
//			if (o.getReturnType().getType() instanceof VoidType)
//				method.setReturnType("OclVoid");
//			else
//				method.setReturnType(varStr); // 2020待改 array/arraylist -> int[x] int[x][x]

			// 處理參數
			for (int index = 0; o.getParameter() != null && index < o.getParameter().size(); index++) {
				VariableInfo p = o.getParameter().get(index);
				method.addArgument(new VariableToken(p.getName(), p.getType()));
			}
			// put method into symboltable
			symbolTableTemp.addMethod(method);
		}

		return symbolTableTemp;
	}

	// generate inv clp and append attribute name with objName
	@Override
	public String genInvCLP(String objName, boolean isPre) {
		if (this.invCLG == null)
			return "";
		String clpStr = "";
		HashMap<String, Integer> variableSet = new HashMap<>();
		HashMap<String, String> renameAtt = new HashMap<>();
		ArrayList<CLGConstraint> constraintList = new ArrayList<CLGConstraint>();
		for (String att : this.symbolTable.getAttribute().keySet()) {
			// self class attribute needn't pass objName
			if (!objName.equals(""))
				renameAtt.put("Self_" + att, objName + (isPre ? "_pre" : "") + "_" + att);
		}
		// get inv path
		CLGPathEnumerator clgPathEnumerator = new CLGPathEnumerator();
		clgPathEnumerator.init(this.invCLG);
		CLGPath path = clgPathEnumerator.next();
		for (int i = 1; i < path.getPathNodes().size() - 1; i++) {
			if (path.getPathNodes().get(i) instanceof CLGConstraintNode) {
				((CLGConstraintNode) path.getPathNodes().get(i)).getId();
				constraintList.add(((CLGConstraintNode) path.getPathNodes().get(i)).getConstraint().clone());
			}
		}
		// inv clg2clp

		for (CLGConstraint c : constraintList) {
			c.renameWithMap(renameAtt);
			CLPInfo c_clpinfo = c.getCLPInfo(variableSet, new HashMap<>());
			clpStr += "\n" + c_clpinfo.getReturnCLP() + ",";

			for (String str : c_clpinfo.getMethodCallCLP()) {
				clpStr += "\n" + str + ",";
			}
		}

		return clpStr;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		if (super.symbolTable == null)
			return;
		String typeName = Utility.titleToUppercase(this.getType());
		// Do not generate clp repeatedly
		if (labelingMap.containsKey(typeName))
			return;
		String labelingCLPStr = "\r\nlabeling_" + typeName + "(" + typeName + "List, " + labelingType + "):-\r\n"
				+ "	(foreach(" + typeName + ", " + typeName + "List),\r\n" + "	param(" + labelingType + ")\r\n"
				+ "	do\r\n" + "\t" + typeName + " = [";
		// flatten
		for (String attName : super.symbolTable.getAttribute().keySet()) {
			labelingCLPStr += typeName + "_" + attName + ", ";
		}
		labelingCLPStr = Utility.delEndRedundantSymbol(labelingCLPStr, ", ") + "],";

		// 增加inv clp
		String invCLP = "";
		if (this.invCLG != null) {
			invCLP = this.genInvCLP(typeName, false).replace("\\r\\n", "\\r\\n\\t");
		} else {// 回到Main.typeTable找完整的型態資訊
			this.addInvCLG(((UserDefinedType) Main.typeTable.get(typeName, null)).getInvCLG());
			invCLP = this.genInvCLP(typeName, false).replace("\\r\\n", "\\r\\n\\t");
		}
		labelingCLPStr += invCLP;

		// collect the same type of attribute and labeling together
		LinkedHashMap<String, ArrayList<String>> labelingList = new LinkedHashMap<>();
		for (String attName : super.symbolTable.getAttribute().keySet()) {
			VariableType attType = super.symbolTable.getAttribute().get(attName).getType();
			// add the element type lableing clp
			attType.genDclLabelingCLP(labelingMap, dclArr, labelingArr);
			if (attType instanceof ArrayType) {
				// get every dimension size from attType
				String dimList = "";
				while (attType instanceof ArrayType) {
					String arraySizeName = ((ArrayType) attType).getSize();
					if (!Utility.isNumeric(arraySizeName))
						arraySizeName = typeName + "_" + arraySizeName;
					dimList += arraySizeName + ", ";
					attType = ((ArrayType) attType).getElement();
				}
				// call labeling array clause and its argument
				String attTypeName = Utility.titleToUppercase(attType.getType());
				dimList = Utility.delEndRedundantSymbol(dimList, ", ");
				labelingCLPStr += "\r\n\t(" + labelingType + "=\"dcl\"->dcl_array(" + typeName + "_" + attName + ", ["
						+ dimList + "], \"" + attTypeName + "\");";
				labelingCLPStr += "\r\n\tlabeling_array(" + typeName + "_" + attName + ", [" + dimList + "], \""
						+ attTypeName + "\")),";
			} else {
				// collect the same type(non-array) of attribute together
				String attTypeName = Utility.titleToUppercase(attType.getType());
				if (labelingList.containsKey(attTypeName))
					labelingList.get(attTypeName).add(attName);
				else {
					labelingList.put(attTypeName, new ArrayList<String>());
					labelingList.get(attTypeName).add(attName);
				}
			}
		}

		// sort
		TreeMap<Integer, LinkedHashMap<String, ArrayList<String>>> labelingSort = new TreeMap<>();
		for (String attTypeName : labelingList.keySet()) {
			VariableType type = Main.typeTable.get(attTypeName, typeName);

			if (labelingSort.containsKey(type.getLabelingPriority())) {
				labelingSort.get(type.getLabelingPriority()).put(attTypeName, labelingList.get(attTypeName));
			} else {
				labelingSort.put(type.getLabelingPriority(), new LinkedHashMap<String, ArrayList<String>>());
				labelingSort.get(type.getLabelingPriority()).put(attTypeName, labelingList.get(attTypeName));
			}

		}

		// call labeling clause for the same type
		for (Integer priority : labelingSort.keySet()) {
			LinkedHashMap<String, ArrayList<String>> priorityLabelingList = labelingSort.get(priority);
			for (String attTypeName : priorityLabelingList.keySet()) {
				labelingCLPStr += "\r\n\tlabeling_" + attTypeName + "([";
				for (String attName : priorityLabelingList.get(attTypeName)) {
					labelingCLPStr += typeName + "_" + attName + ", ";
				}
				labelingCLPStr = Utility.delEndRedundantSymbol(labelingCLPStr, ", ") + "], " + labelingType + "),";
			}
		}
		labelingCLPStr = Utility.delEndRedundantSymbol(labelingCLPStr, ",") + ").\r\n";
		// dcl array clp, labeling array clp
		this.genLabelingArrayCLP(dclArr, labelingArr);

		labelingMap.put(typeName, labelingCLPStr);

		return;
	}

	// the array type labeling clp of every user defined type
	private void genLabelingArrayCLP(LinkedHashMap<String, String> dclArr, LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		dclArr.put(typeNameFix,
				"\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_" + typeNameFix + "([Ele], \"dcl\");");
		labelingArr.put(typeNameFix,
				"\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_" + typeNameFix + "([Ele], \"random\");");
		return;
	}

	@Override
	public void genDclTransformCLP(LinkedHashMap<String, String> transformMap,
			LinkedHashMap<String, String> transformArr) {
		if (super.symbolTable == null)
			return;
		String typeName = Utility.titleToUppercase(this.getType());
		// Do not generate clp repeatedly
		if (transformMap.containsKey(typeName))
			return;

		String transformCLPStr = "\r\ntransform_" + typeName + "([],[]).\r\n" + "transform_" + typeName + "(["
				+ typeName + "|" + typeName + "List], [New" + typeName + "|New" + typeName + "List]):-\r\n" + "	length("
				+ typeName + ", Len),\r\n" + "	length(New" + typeName + ", Len),\r\n";

		// flatten and collect
		String flattanstr = "[";
		String newFlattanstr = "[";
		LinkedHashMap<String, ArrayList<String>> attribute = new LinkedHashMap<>();
		LinkedHashMap<String, ArrayList<String>> attributeArray = new LinkedHashMap<>();
		for (Entry<String, VariableToken> att : super.symbolTable.getAttribute().entrySet()) {

			flattanstr += typeName + "_" + att.getValue().getVariableName() + ", ";
			newFlattanstr += "New" + typeName + "_" + att.getValue().getVariableName() + ", ";
			att.getValue().getType().genDclTransformCLP(transformMap, transformArr);

			if (att.getValue().getType() instanceof ArrayType) {
				VariableType eleType = ((ArrayType) att.getValue().getType()).getUnitType();
				if (attributeArray.containsKey(eleType.getType())) {
					attributeArray.get(eleType.getType()).add(att.getValue().getVariableName());
				} else {
					ArrayList<String> thisTypeAttributeList = new ArrayList<String>();
					String thisTypeName = eleType.getType();
					thisTypeAttributeList.add(att.getValue().getVariableName());
					attributeArray.put(thisTypeName, thisTypeAttributeList);
				}
			} else {
				if (attribute.containsKey(att.getValue().getType().getType())) {
					attribute.get(att.getValue().getType().getType()).add(att.getValue().getVariableName());
				} else {
					ArrayList<String> thisTypeAttributeList = new ArrayList<String>();
					String thisTypeName = att.getValue().getType().getType();
					thisTypeAttributeList.add(att.getValue().getVariableName());
					attribute.put(thisTypeName, thisTypeAttributeList);
				}
			}

		}
		flattanstr = Utility.delEndRedundantSymbol(flattanstr, ", ") + "]";
		newFlattanstr = Utility.delEndRedundantSymbol(newFlattanstr, ", ") + "]";

		transformCLPStr += "	" + typeName + " = " + flattanstr + ",";
		transformCLPStr += "\r\n	New" + typeName + " = " + newFlattanstr + ",";

		// 陣列要分開做
		for (Entry<String, ArrayList<String>> type : attributeArray.entrySet()) {
			for (String attName : type.getValue()) {
				transformCLPStr += "\r\ntransform_Array(";

				String oldAttributeStr = "[";
				String newAttributeStr = "[";

				oldAttributeStr += typeName + "_" + attName + ", ";
				newAttributeStr += "New" + typeName + "_" + attName + ", ";

				transformCLPStr += Utility.delEndRedundantSymbol(oldAttributeStr, ", ") + "],";
				transformCLPStr += Utility.delEndRedundantSymbol(newAttributeStr, ", ") + "],";
				transformCLPStr += "\"" + Utility.titleToUppercase(type.getKey()) + "\"),";
			}
		}

		for (Entry<String, ArrayList<String>> type : attribute.entrySet()) {
			transformCLPStr += "\r\ntransform_" + Utility.titleToUppercase(type.getKey()) + "(";

			String oldAttributeStr = "[";
			String newAttributeStr = "[";
			for (String attName : type.getValue()) {
				oldAttributeStr += typeName + "_" + attName + ", ";
				newAttributeStr += "New" + typeName + "_" + attName + ", ";
			}
			transformCLPStr += Utility.delEndRedundantSymbol(oldAttributeStr, ", ") + "],";
			transformCLPStr += Utility.delEndRedundantSymbol(newAttributeStr, ", ") + "]),";

		}
		transformCLPStr += "\r\n\ttransform_" + typeName + "(" + typeName + "List, New" + typeName + "List).\r\n";

		this.transformCLP = transformCLPStr;
		transformMap.put(typeName, transformCLPStr);
		transformArr.put(typeName,
				"\r\n\t\tEleType=\"" + typeName + "\"->transform_" + typeName + "([Ele], [NewEle]);");
		return;
	}

	@Override
	public UserDefinedType clone() {
		UserDefinedType cloneObj = new UserDefinedType(super.typeName, super.typeID);
		if (super.symbolTable != null)
			cloneObj.setSymbolTable(super.symbolTable.cloneOnlyAtt());
		return cloneObj;
	}

	@Override
	public String flattenObj(String objName, boolean isPre, boolean needGenInvCLP, HashSet<String> haveBeenFlattened,
			HashSet<String> realTypeVar) {
		String flattenStr = "";// 扁平化結果字串
		String objNameFix = "";
		TypeTable typeTab = Main.typeTable;
		ArrayList<String> attFlatten = new ArrayList<>();
		objNameFix = Utility.titleToUppercase(objName) + (isPre ? "_pre" : "");
		if (typeTab.get(this.getType(), null).getSymbolTable() != null) {
			SymbolTable thisTypeSym = typeTab.get(this.getType(), null).getSymbolTable();
			boolean stopFlatten = false;

			// 父類子類雙向關聯(遇到要展開的類別重複時)不做扁平化
			if (haveBeenFlattened.contains(this.getType()))
				stopFlatten = true;
			haveBeenFlattened.add(this.getType());
			// 雙向關聯處理end

			if (needGenInvCLP)
				flattenStr += this.genInvCLP(objName, isPre);

			// get the all attributes of UserDefinedType, and make them flatten
			for (String attKey : thisTypeSym.getAttribute().keySet()) {
				attFlatten.add(objNameFix + "_" + attKey);
				VariableToken att = thisTypeSym.getAttribute().get(attKey);

				if (att.getType() instanceof FloatType || att.getType() instanceof DoubleType) {
					realTypeVar.add(objNameFix + "_" + attKey);
				}

				if (!stopFlatten)
					flattenStr += thisTypeSym.getAttribute().get(attKey).getType().flattenObj(objNameFix + "_" + attKey,
							false, needGenInvCLP, haveBeenFlattened, realTypeVar);
			}
		}
		// flattening end
		// 扁平化前變數與扁平化後的結果等於
		flattenStr += "\r\n" + objNameFix + "=" + (attFlatten.size() == 0 ? "[]" : attFlatten) + ",";

		return flattenStr;
	}

	@Override
	public HashMap<String, String> splitTestData(String testDatas) {
		HashMap<String, String> retMap = new HashMap<>();
		String splitTestData = "";
		testDatas = testDatas.trim();
		if (testDatas.indexOf("[") == 0) {
			testDatas = testDatas.substring(1, testDatas.length()).trim();// delete the head "["
			splitTestData += "[";
			for (VariableToken att : this.symbolTable.getAttribute().values()) {
				HashMap<String, String> splitStrMap = new HashMap<>();
				splitStrMap = att.getType().splitTestData(testDatas);
				testDatas = splitStrMap.get(testDataStrRemain).trim();
				splitTestData += splitStrMap.get(splitVar);
				if (testDatas.indexOf(",") == 0) {
					testDatas = testDatas.substring(1).trim();
					splitTestData += ", ";
				}
			}
			testDatas = testDatas.trim();
			if (testDatas.indexOf("]") == 0) {
				testDatas = testDatas.substring(1);
				splitTestData += "]";
			}
		}

		retMap.put(splitVar, splitTestData);
		retMap.put(testDataStrRemain, testDatas);
		return retMap;
	}

	@Override
	public String getTransformTypeCLP() {

		if (this.transformCLP.equals(""))
			this.genDclTransformCLP(new LinkedHashMap<String, String>(), new LinkedHashMap<String, String>());
		return this.transformCLP;
	}

	/*
	 * @Override public String genDomainCLP(String obj) { // TODO Auto-generated
	 * method stub String s="" ; String name=obj+"=["; for(int i = 0; i
	 * <content.getProperties().size();i++) {
	 * 
	 * String temp=content.getProperties().get(i).getName();
	 * name=name+temp.substring(0, 1).toUpperCase()+temp.substring(1)+","; }
	 * name=name.substring(0, name.length()-1)+"],"; for(int i = 0; i
	 * <content.getProperties().size();i++) {
	 * 
	 * String temp=content.getProperties().get(i).getName();
	 * s=s+content.getProperties().get(i).getType().genDomainCLP(temp)+","; } return
	 * name+s.substring(0,s.length()-1); }
	 */

}
