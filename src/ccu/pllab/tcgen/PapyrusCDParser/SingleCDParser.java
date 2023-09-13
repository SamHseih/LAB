/*
 * 20180331 暺�∩撰
 * ���Papyrus蝜芾ˊ���������
 */


package ccu.pllab.tcgen.PapyrusCDParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.launcher.BlackBoxLauncher;
import ccu.pllab.tcgen.transform.Splitter;
import ccu.pllab.tcgen.transform.UmlTransformer;
import tcgenplugin_2.handlers.BlackBoxHandler;

import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class SingleCDParser {

	private ArrayList<Document> ref = null;  // �靘��閮�������隞����
	private ArrayList<ClassInfo> classList ; // �靘銝餉��
	private ArrayList<UserDefinedType> ref_typelist; // �靘��隞摰儔憿
	private String packageName=null;
	
	// constructor ===========================================================================
	// �銝����
	public SingleCDParser() {
		// this.cd = cd ;
		// init(cd);
	} 
	// constructor ===========================================================================
	
	
	
	// 憭������ method =======================================================================
	// Get package name
	
	public String getPkgName() {
		return this.packageName;   // XML�蝭�暺�
	}
	
	
	// Get Class List
	public ArrayList<ClassInfo> getClassList() {
		return classList;
	}
	// 憭������ method =======================================================================
	
	
	
	/*
	 * 隞乩�Parse銝餉�����method
	 * 
	 * 1. 撠�����om璅寧�蝯�� : init(), initRef()
	 * 2. ���蒂������ㄐ����� : Parse()
	 */ 
	
	// NO.1 =====================================================================================
	// ��蜓閬�葫閰衣���������om
	public Document init( File uml ) {
		// TODO Auto-generated method stub
		try {
			Splitter split = new Splitter(uml);
			File cd = split.split2CDuml();
			
			// 摰儔XML DOM parser閫���
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			// 撱箇�OM document
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// 撘xml
			Document doc = dBuilder.parse(cd);
			
			// ���ml�������ormalize
		    doc.getDocumentElement().normalize();
		    this.packageName=doc.getDocumentElement().getAttribute("name");
		    return doc;
		    
		} catch (Exception e) {
			 e.printStackTrace();
	    }
		return null;
	} // init()
	
	/*
	// ������ml��ist 頧�om
	private void initRef( ArrayList<File> rList ) {
		try {
			this.ref = new ArrayList<Document>();
			
			// 摰儔XML DOM parser閫���
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			// 撱箇�OM document
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			for(int i = 0 ; i < rList.size() ; i++ ) {
			    doc = dBuilder.parse(rList.get(i));       // 撘xml
			    doc.getDocumentElement().normalize();   // ���ml�������ormalize
			    this.ref.add(doc) ;
			} // for
		    
		} catch (Exception e) {
			 e.printStackTrace();
	    }
	} // initRef()
	*/
	// NO.1 =====================================================================================
	
	
	
	// NO.2 =====================================================================================
	// ����������lass�����
	public void Parse(Document doc) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		classList = new ArrayList<ClassInfo>() ;
		
		// ������lass
		NodeList nList = doc.getElementsByTagName("packagedElement");
		
		for (int i = 0; i < nList.getLength(); i++) {
			ArrayList<VariableInfo> property_List = null ;
			ArrayList<OperationInfo> operation_List = null ;
			ClassInfo c = new ClassInfo();
			UserDefinedType this_class_type = null;
			
            Node node = nList.item(i);                // 蝚枰�lass
            if (node.getNodeType() == Node.ELEMENT_NODE) {  
                Element e = (Element) node;
                c.setName(e.getAttribute("name"));    // class name
                c.setID(e.getAttribute("xmi:id"));    // class id
    			this_class_type = new UserDefinedType(c.getName(), c.getID());
    			//BlackBoxLauncher.typeTable.add(this_class_type);
    			Main.typeTable.add(this_class_type);
    			
    		    //System.out.println(BlackBoxLauncher.typeTable.printTypeTableInfo());
                
                // ---------- Property ----------
                NodeList pList = e.getElementsByTagName("ownedAttribute");   // ���迨class�����roperty
                if( pList.getLength() != 0 ) property_List = new ArrayList<VariableInfo>() ;
                
                // ���迨class鋆∠���roperty��ype & name
                for( int j = 0 ; j < pList.getLength() ; j++ ) {
                	Element child = (Element) pList.item(j);
                	VariableInfo p = new VariableInfo( getVarType(child, c.getID(), c.getName()), child.getAttribute("name"), 
                			                           child.getAttribute("xmi:id"), getAttrVisibility(child), 
                			                           c.getName() );
                	if (child.getElementsByTagName("lowerValue").item(0) != null) {
                		Element temp = (Element) child.getElementsByTagName("lowerValue").item(0);
                		if( temp.hasAttribute("value") ) p.setLowerValue(temp.getAttribute("value"));
                		else p.setLowerValue("0");
                	} // if
                	
                	if (child.getElementsByTagName("upperValue").item(0) != null) {
                		Element temp = (Element) child.getElementsByTagName("upperValue").item(0);
                		p.setUpperValue(temp.getAttribute("value"));
                	} // if
                	
                	// ArrayType
                	if(!p.getLowerValue().equals("1") && !p.getUpperValue().equals("1")) {
                		p.setType(new ArrayType(p.getType(), p.getUpperValue()));
                	}
                	
                	property_List.add( p ) ;    // ��roperty���烽ist
                } // for
                // ---------- Property ----------
                
                
                // ---------- Operation ----------
                NodeList oList = e.getElementsByTagName("ownedOperation");   // ���迨class�����peration
                if( oList.getLength() != 0 ) operation_List = new ArrayList<OperationInfo>() ;
                
                // ���迨class鋆∠���peration��ame, parameter, return type 
                for( int k = 0 ; k < oList.getLength() ; k++ ) {
                	OperationInfo method = new OperationInfo();
                	Element child = (Element) oList.item(k);
                	method.setName(child.getAttribute("name"));     // ���ethod name
                	method.setID(child.getAttribute("xmi:id"));     // ���ethod id
                	method.setVisibility(getAttrVisibility(child)); // ���ethod visibility
                	method.setClassName(c.getName());               // 閮剖��撅祇�
                	
                	NodeList parameter = child.getElementsByTagName("ownedParameter");  // �������(��return���)
                	System.out.println("CD Parser / Class Name: " + method.getName()+", ParaNum: " + parameter.getLength());
                	
                	// method 瘝�� , return type�void
                	if(parameter.getLength()== 0 ) {
                		System.out.println("CD Parser: No Arg.");
                		VariableInfo rt = new VariableInfo(new VoidType(), "", "", "", c.getName());
                		method.setReturnType(rt);
                	} // if
                	
                	// ���ethod�������ame, type 隞亙�� return type
                	else {
                	  ArrayList<VariableInfo> parameter_List = new ArrayList<VariableInfo>();
                      for( int p_i = 0 ; p_i < parameter.getLength() ; p_i++ ) {
                    	  Element eParameter = (Element) parameter.item(p_i);
                    	  
                          VariableInfo p = new VariableInfo( getVarType(eParameter,c.getID(),c.getName()), eParameter.getAttribute("name"), 
                          			                         eParameter.getAttribute("xmi:id"), "", c.getName() );
                          	
                          if (eParameter.getElementsByTagName("lowerValue").item(0) != null) {
                         	  Element temp = (Element) child.getElementsByTagName("lowerValue").item(0);
                       		  if( temp.hasAttribute("value") ) p.setLowerValue(temp.getAttribute("value"));
                       		  else p.setLowerValue("0");
                       	  } // if
                        	
                          if (eParameter.getElementsByTagName("upperValue").item(0) != null) {
                              Element temp = (Element) child.getElementsByTagName("upperValue").item(0);
                        	  p.setUpperValue(temp.getAttribute("value"));
                          } // if
                    	  // System.out.println(p.GetType()+" " + p.GetName());
                          
                      	// ArrayType
                      	if(!p.getLowerValue().equals("1") && !p.getUpperValue().equals("1")) {
                      		p.setType(new ArrayType(p.getType(), p.getUpperValue()));
                      	}
                        	
                      	  // ��
                      	  if ( ! eParameter.getAttribute("direction").equals("return"))
                      		  parameter_List.add( p ) ;    // ��arameter���烽ist
                    	  
                    	  // return variable
                    	  else method.setReturnType( p );
                      	  
                      } // for
                      
                      
                      if( parameter_List.size()!=0 )    // 銵函內method���
                    	  method.setParameter(parameter_List);
                      
                      // �����void
                      if(method.getReturnType()==null) {
                    	  System.out.println("VOID");
                    	  VariableInfo rt = new VariableInfo(new VoidType(), "", "", "", c.getName());
                  		  method.setReturnType(rt);
                      }
                	} // else
                	
                	operation_List.add( method ) ;    // ��peration���烽ist
                } // for
                // ---------- Operation ----------
                
                c.setProperties(property_List);     // class property
                c.setOperations(operation_List);    // class operation
            } // if
        	
        	this_class_type.setClassInfo(c);
            classList.add(c);   // ����������lassInfo���淆lass list
		} // for
		
	} // Parse()
	// NO.2 =====================================================================================
	
	
	
	// �Parse()銝剜�����ethod =================================================================
	
	// ���roperty��ype: �撱箇���, ����, ����
	private String getVarType_Str(Element e) {
    	if(e.hasAttribute("type")) return e.getAttribute("type");
    	
    	else {
    		Element et = (Element)e.getElementsByTagName("type").item(0);
        	String type_id = et.getAttribute("href");
        	type_id = type_id.substring(type_id.indexOf("#")+1, type_id.length());
    		return type_id;
    	}
	}
	
	
	// ���roperty��ype: �撱箇���, ����, ����
	private VariableType getVarType(Element e, String classID, String className ) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/*
		// 2020/05 �����銝����Ⅳ鈭�
    	if(e.hasAttribute("type")) return e.getAttribute("type");
    	*/
		if(e.hasAttribute("type") ) {//�������   敺耨
			if(Main.typeTable.containsType(e.getAttribute("type"), e.getAttribute("type")))
			//if(BlackBoxLauncher.typeTable.containsType(e.getAttribute("type"), e.getAttribute("type")))
				return Main.typeTable.get(e.getAttribute("type"), e.getAttribute("type"));
				//return BlackBoxLauncher.typeTable.get(e.getAttribute("type"), e.getAttribute("type"));
			//else return new UnDefinedType(e.getAttribute("type"));// ��霈���噶������ 敺耨�
		}
		
		
    	// 憒�蝙�憭��ype嚗�憭��ag�type����暺�鋆��mi:type����������摰儔��
    	Element et = (Element)e.getElementsByTagName("type").item(0);


    	// ����(int, float, boolean, char, ..)
    	if(et.getAttribute("xmi:type").equals("uml:PrimitiveType")) {
        	String s = et.getAttribute("href");
        	s = s.substring(s.indexOf("#")+1, s.length());
        	
        	/*�����array*/        	
        	String low = "1", up = "1";
        	
        	if (e.getElementsByTagName("lowerValue").item(0) != null) {
        		Element temp = (Element) e.getElementsByTagName("lowerValue").item(0);
        		if( temp.hasAttribute("value") )low = temp.getAttribute("value");
        	} // if
        	
        	if (e.getElementsByTagName("upperValue").item(0) != null) {
        		Element temp = (Element) e.getElementsByTagName("upperValue").item(0);
        		up = temp.getAttribute("value");
        	} // if
        	
        	// ArrayType
        	if(!low.equals("1") && !up.equals("1")) {
        		//ArrayType new_arrType = new ArrayType(BlackBoxLauncher.typeTable.get(s, s), up);
        		//BlackBoxLauncher.typeTable.add(new_arrType);
        		ArrayType new_arrType = new ArrayType(Main.typeTable.get(s, s), up);
        		Main.typeTable.add(new_arrType);
        		return new_arrType;
        	}
        	/*�����array*/
        	
        	//else return BlackBoxLauncher.typeTable.get(s, s);
        	else return Main.typeTable.get(s, s);
    	} // if
    	
    	// �摰儔�� (ArrayList, String, UserDefinedType)
    	else {
        	String s = et.getAttribute("href");
        	s = s.substring(0, s.indexOf("."));
        	
        	VariableType tempType = null;
        	
        	if(s.equals("ArrayList")) { // ArrayList��店閬憭�ㄐ���lement type
        		String comment_type = e.getElementsByTagName("ownedComment").item(0).getTextContent().trim();
        		System.out.println("CD Parser Test Type [ArrayList]: "+ comment_type);
        		// comment_type = comment_type.substring( comment_type.indexOf("<")+1,  comment_type.length()) ;
        		tempType = createArrayListType(comment_type);
        		Main.typeTable.add(tempType);
        		//BlackBoxLauncher.typeTable.add(tempType);
        	} // if
        	
        	//else if ( s.equals("String") )tempType = BlackBoxLauncher.typeTable.get("string", "string") ;
        	else if ( s.equals("String") )tempType = Main.typeTable.get("string", "string") ;
        	
        	else {
        		tempType = createUserDefinedType( s ); 
        	} // else

        	
        	/*�����array*/        	
        	String low="1", up = "1";
        	
        	if (e.getElementsByTagName("lowerValue").item(0) != null) {
        		Element temp = (Element) e.getElementsByTagName("lowerValue").item(0);
        		if( temp.hasAttribute("value") )low = temp.getAttribute("value");
        	} // if
        	
        	if (e.getElementsByTagName("upperValue").item(0) != null) {
        		Element temp = (Element) e.getElementsByTagName("upperValue").item(0);
        		up = temp.getAttribute("value");
        	} // if
        	
        	// ArrayType
        	if(!low.equals("1") && !up.equals("1")) {
        		ArrayType new_arrType = new ArrayType(tempType, up);
        		Main.typeTable.add(new_arrType);
        		//BlackBoxLauncher.typeTable.add(new_arrType);
        		return new_arrType;
        	}
        	/*�����array*/
        	
        	else return tempType;

    	} // else
	} // getVarType()
	
	
	
	
	// �撱箄摰儔��rrayListType蝯��
	private ArrayListType createArrayListType( String s) throws ParserConfigurationException, SAXException, IOException, TransformerException {	
		System.out.println("ArrayList String: " + s);
		String type_str = null;
		String element = s.substring(s.indexOf("<")+1, s.length()-1);
		System.out.println(s+"::"+element);
		if( element.contains("<") && element.contains(">") )
			type_str = s.substring(0, s.indexOf("<") );
		
		System.out.println(s+"::"+type_str+"::"+element);
		//if( BlackBoxLauncher.typeTable.containsType(element, element)) {
		//	return new ArrayListType( BlackBoxLauncher.typeTable.get(element, element) );
		if( Main.typeTable.containsType(element, element)) {
			return new ArrayListType( Main.typeTable.get(element, element) );
		}
		else if( type_str != null && type_str.equals("ArrayList") )  // 憭雁 muti-D
			return new ArrayListType( createArrayListType(element) );
		else
			return new ArrayListType( createUserDefinedType(element) );
	} // createArrayListType()
	
	
	// �撱箔蝙��摰儔��serDefinedType蝯��
	private UserDefinedType createUserDefinedType( String s ) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		//�spec鞈�冗���������蒂��PapyrusCDParser����
		//敺lassInfo�鞈��
		//�撱箇��蒂��
		Path CDumlPath = Paths.get(DataWriter.output_folder_path+"/spec/"+s+".uml");
		File uml = CDumlPath.toFile();
		Splitter split = new Splitter(uml);
		File cdUml = split.split2CDuml();
		
		SingleCDParser parser = new SingleCDParser();
		Document ref_doc = parser.init(cdUml);
		NodeList nList = ref_doc.getElementsByTagName("packagedElement");
		Node node = nList.item(0);                // �����lass (憒�撘萄����lass嚗�����
        if (node.getNodeType() == Node.ELEMENT_NODE) {  
        	Element e = (Element) node;
        	String type_name = e.getAttribute("name");    // class name
        	String type_id = e.getAttribute("xmi:id");    // class id
        	
        	//if( ! BlackBoxLauncher.typeTable.containsType( type_name, type_id) ) {
        	if( ! Main.typeTable.containsType( type_name, type_id) ) {
            	parser.Parse(ref_doc);
        	} // if
        	
        	//return ((UserDefinedType)BlackBoxLauncher.typeTable.get( type_name, type_id ));
        	return ((UserDefinedType)Main.typeTable.get( type_name, type_id ));

        } // if
        
        return null;
	} // createUserDefinedType()
	
	
	// ���isibility, ��閮剖�迨撅祆��, ��身���身���"public"
	private String getAttrVisibility(Element e) {
    	if(e.hasAttribute("visibility")) return e.getAttribute("visibility");
    	else return "public" ;
	}
	

	// ������eturn��
	private boolean hasReturn(NodeList list) {
		boolean result = false ;
		for(int i = 0 ; i < list.getLength() ; i++) {
			Element e = (Element) list.item(i);
			if ( e.hasAttribute("direction") && e.getAttribute("direction").equals("return") )
				return true;
		} // for
		
		return result;
	}
	
	
	// ���ype�array��roperty��ize(lowerValue & upperValue)
	private String getSize(Element e) {
    	if(e.hasAttribute("type")) return e.getAttribute("type");
    	else {
    		Element et = (Element)e.getElementsByTagName("type").item(0);
    		String s = et.getAttribute("href");
    		s = s.substring(s.indexOf("#")+1, s.length());
    		return s;
    	}
    	
	}
	
	
	/*
	private void changeRef() {
		for(int i =0; i < this.getClassList().size();i++) {
			 now = this.getClassList().get(i);
			if( searchRefUserDefinedType(now.getID(), now.getName()) == null );
			else 
				
				
		for( int i = 0 ; i < classList.size() ; i++ ) {
			ClassInfo c =classList.get(i);
			for(int j= 0; c.getProperties()!= null && j < c.getProperties().size();j++ ) {
			    VariableInfo p = c.getProperties().get(j);
			    if(p.getType() instanceof UserDefinedType) {
					if( searchRefUserDefinedType(now.getID(), now.getName()) == null );
			    }
			    	
			    }
			    	
			   for(int k= 0; c.getOperations()!= null && k < c.getOperations().size();k++ ) {
				   OperationInfo o = c.getOperations().get(k);
			    	s=s+o.getReturnType().getType().toString() +" " + o.getName() + " " + o.getID() + " " + o.getVisibility()+ " " + o.getClassName();
			    	s=s+"Parameter: ";
			    	for(int index = 0 ;o.getParameter()!= null && index < o.getParameter().size();index++) {
				    	VariableInfo p = o.getParameter().get(index);
				    	s=s+p.getType().toString() + " " + p.getName() + " " + p.getID() + " " + p.getVisibility()+ " " + p.getClassName();
			    	 }
			    	   s=s+"\n";
			    	}
			    	s=s+"\n================================";
			    }
	}*/
	/*
	// 2020/05 隞乩�撘�銝�� -----------------------------------------------------------------------------------------------------------------------------------------
	
	// 撠������撌勗遣蝡�lass��d�����, ��d���lass��ame
	// 憒��������name敺���]                                   ****敺
	public void changeTypeStr() {
		
		for (int  i= 0 ; i < this.classList.size();i++){
		    // class local variable
            for(int j = 0 ; this.classList.get(i).getProperties() != null && 
            		j < this.classList.get(i).getProperties().size();j++) {
            	VariableInfo p = this.classList.get(i).getProperties().get(j);
            	p.setType(typeIDtoName(p.getType()));
            	
            	// if(p.getSize() != null) p.setType(p.getType()+"[]"); // size��征������
		    } // for
            

		    // class method 
            for(int j = 0 ; this.classList.get(i).getOperations() != null &&
            		j < this.classList.get(i).getOperations().size();j++){
            	OperationInfo o=this.classList.get(i).getOperations().get(j);
            	// class method parameter
            	for(int para_i = 0 ; o.getParameter() != null && 
            			para_i < o.getParameter().size();para_i++) {
            		VariableInfo parameter = o.getParameter().get(para_i);
            		parameter.setType(typeIDtoName(parameter.getType()));
            		
            		// if(parameter.getSize() != null) parameter.setType(parameter.getType()+"[]"); // size��征������
                } // for
            	
            	// return type
            	VariableInfo rt = o.getReturnType() ;
            	if(o.getReturnType()!=null)
            	rt.setType(typeIDtoName(rt.getType()));
            	
            	// *** if(o.getReturnType().getSize() != null) o.setType(p.getType()+"[]"); // size��征������
            }//for operation
		}
	} // changeTypeStr()
	
	
	// 憒��摰儔���, �憿��ㄐtype�隞卉lass ID��耦撘���
	// ��隞亥�撠d頧��撠�����迂
	// 鋡剃hangeTypeStr()��
	private String typeIDtoName( String type ) {
		// System.out.println(classList.size());
		String s = type ;
		for(int k = 0 ; k < this.classList.size();k++ ) {
			// System.out.println((k+1) + this.classList.get(k).getID());
			
			if (type.equals(this.classList.get(k).getID())) {
				// System.out.println(type + "**"+this.classList.get(k).getID() + " "+ this.classList.get(k).getName());
				s = this.classList.get(k).getName();
			}
		} //for
		
		return s;
	} // typeIDtoName()
	
	
	public void parseRef() {
		NodeList list = doc.getElementsByTagName("packageImport");
		Node node = list.item(4);                // 蝚枰�lass
         if (node.getNodeType() == Node.ELEMENT_NODE) {  
             Element e = (Element) node;
             NodeList child = e.getElementsByTagName("importedPackage");
             Element ch = (Element) child.item(0);
             System.out.println(ch.getAttribute("href"));
		File f = new File(ch.getAttribute("href"));
		if( f.exists() ) System.out.println("O");
		else System.out.println("X");
         }
		
	}
	*/
	// �Parse()銝剜�����ethod =================================================================

	
	
	
	//    ��������
	public String printParseClassInfo( ClassInfo c ) {
		// TODO Auto-generated method stub
		try {
			String s = "";
  
			   s= "Class Name: " /*+(i+1)*/ + " "+c.getName() +/* " " + c.getID()+*/ "\n"+"Attributes: ";
		    	for(int j= 0; c.getProperties()!= null && j < c.getProperties().size();j++ ) {
		    		VariableInfo p = c.getProperties().get(j);
		    		s=s+p.getType().toString() + " " + p.getName() + " " + p.getID() + " " + p.getVisibility() + " " + p.getClassName();
		    	}
		    	
		    	s=s+"\nOperations: ";
		    	for(int k= 0; c.getOperations()!= null && k < c.getOperations().size();k++ ) {
		    		OperationInfo o = c.getOperations().get(k);
		    		s=s+o.getReturnType().getType().toString() +" " + o.getName() + " " + o.getID() + " " + o.getVisibility()+ " " + o.getClassName();
		    		s=s+"Parameter: ";
		    	    for(int index = 0 ;o.getParameter()!= null && index < o.getParameter().size();index++) {
			    		VariableInfo p = o.getParameter().get(index);
			    		s=s+p.getType().toString() + " " + p.getName() + " " + p.getID() + " " + p.getVisibility()+ " " + p.getClassName();
		    	    }
		    	    s=s+"\n";
		    	}
		    	s=s+"\n================================";
		    	
		    return s;

		} catch (Exception e) {
			 e.printStackTrace();
	    }
		return "Nothing";
	}
	
	

}
