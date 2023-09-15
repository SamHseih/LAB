package sqlschemaAnalyzer;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;

public class tableVariable{
	
	String variable_name;
	int variable_type; // 0 for boolean (tinyint), 1 for int, 2 for string
	int variable_length; // 若變數型態為String 其字串長度, 預設0
	
	boolean variable_ispk; // primary key
	boolean variable_isfk; // foreign key
	boolean variable_isnn; // not null
	boolean variable_isun; // unique
	
	CLGOperatorNode pkNode;
	CLGOperatorNode pkNodeNot;
	CLGOperatorNode unNode;
	CLGOperatorNode unNodeNot;
	
	CLGOperatorNode nnNode;
	CLGOperatorNode nnNodeNot;
	
	CLGOperatorNode fkNode;
	CLGOperatorNode fkNodeNot;
	CLGOperatorNode fkNode2;
	CLGOperatorNode fkNode2Not;
	
	CLGOperatorNode booleanNode;
	
	String fk_table; // foreign key reference table
	String fk_key; // foreign key reference column
	int fk_number;
	
	public void setVariableName(String name) {
		this.variable_name = name;
	}
	
	public void setVariableType(int t) {
		this.variable_type = t;
	}
	
	public void setVariableType(int t, int l) {
		this.variable_type = t;
		this.variable_length = l;
	}
	
	public void setBooleanNode() {
		this.nnNode = new CLGOperatorNode("<>");
		this.nnNodeNot = new CLGOperatorNode("==");
		
		//-999代指null 方便執行 在測試案例程式(junit)產生處理時作修正
		CLGVariableNode L = new CLGVariableNode(this.variable_name, "String") ;
	    CLGLiteralNode R = new CLGLiteralNode("TRUE", "String") ;
	    
	    this.booleanNode.setLeftOperand(L);
	    this.booleanNode.setRightOperand(R);
	    
	}
	
	public void setVariableIspk() {
		this.pkNode = new CLGOperatorNode("<>");
		this.pkNodeNot = new CLGOperatorNode("==");
		
		CLGVariableNode L = new CLGVariableNode(this.variable_name, "String") ;
	    CLGVariableNode R = new CLGVariableNode(this.variable_name+"U", "String") ;
	    
	    this.pkNode.setLeftOperand(L);
	    this.pkNode.setRightOperand(R);
	    
	    this.pkNodeNot.setLeftOperand(L);
	    this.pkNodeNot.setRightOperand(R);
	    
	    this.variable_ispk = true;
	}
	
	public void setVariableIsnn() {
		this.nnNode = new CLGOperatorNode("<>");
		this.nnNodeNot = new CLGOperatorNode("==");
		
		//-999代指null 方便執行 在測試案例程式(junit)產生處理時作修正
		CLGVariableNode L = new CLGVariableNode(this.variable_name, "String") ;
	    CLGLiteralNode R = new CLGLiteralNode("0", "int") ;
	    
	    this.nnNode.setLeftOperand(L);
	    this.nnNode.setRightOperand(R);
	    
	    this.nnNodeNot.setLeftOperand(L);
	    this.nnNodeNot.setRightOperand(R);
	    
		this.variable_isnn = true;
	}
	
	public void setVariableIsun() {
		this.unNode = new CLGOperatorNode("<>");
		this.unNodeNot = new CLGOperatorNode("==");
		
		CLGVariableNode L = new CLGVariableNode(this.variable_name, "String") ;
	    CLGVariableNode R = new CLGVariableNode(this.variable_name+"U", "String") ;
	    
	    this.unNode.setLeftOperand(L);
	    this.unNode.setRightOperand(R);
	    
	    this.unNodeNot.setLeftOperand(L);
	    this.unNodeNot.setRightOperand(R);
	    
	    this.variable_isun = true;
	}
	
	/*public void setForeignKey(String ftable, String fkey) {
		this.fkNode = new CLGOperatorNode("=");
		this.fkNodeNot = new CLGOperatorNode("<>");
		
		CLGVariableNode L = new CLGVariableNode(this.variable_name, "String");
		CLGVariableNode R = new CLGVariableNode(fkey, "String");
		
		this.fkNode.setLeftOperand(L);
		this.fkNode.setRightOperand(R);
		
		this.fkNodeNot.setLeftOperand(L);
		this.fkNodeNot.setRightOperand(R);
		
		this.fkNode2 = new CLGOperatorNode("=");
		this.fkNode2Not = new CLGOperatorNode("<>");
		
		CLGVariableNode L2 = new CLGVariableNode(this.variable_name+"U", "String");
		CLGVariableNode R2 = new CLGVariableNode(fkey+"U", "String");
		
		this.fkNode2.setLeftOperand(L2);
		this.fkNode2.setRightOperand(R2);
		
		this.fkNode2Not.setLeftOperand(L2);
		this.fkNode2Not.setRightOperand(R2);
		
		this.fk_table = ftable;
		this.fk_key = fkey; 
		this.variable_isfk = true;
	}
	*/
	
	public void setForeignKey(String ftable, String fkey, int fkNumber) {
		this.fkNode = new CLGOperatorNode("=");
		this.fkNodeNot = new CLGOperatorNode("<>");
		
		CLGVariableNode L = new CLGVariableNode(this.variable_name, "String");
		CLGVariableNode R = new CLGVariableNode(fkey, "String");
		
		this.fkNode.setLeftOperand(L);
		this.fkNode.setRightOperand(R);
		
		this.fkNodeNot.setLeftOperand(L);
		this.fkNodeNot.setRightOperand(R);
		
		this.fkNode2 = new CLGOperatorNode("=");
		this.fkNode2Not = new CLGOperatorNode("<>");
		
		CLGVariableNode L2 = new CLGVariableNode(this.variable_name+"U", "String");
		CLGVariableNode R2 = new CLGVariableNode(fkey+"U", "String");
		
		this.fkNode2.setLeftOperand(L2);
		this.fkNode2.setRightOperand(R2);
		
		this.fkNode2Not.setLeftOperand(L2);
		this.fkNode2Not.setRightOperand(R2);
		
		this.fk_table = ftable;
		this.fk_key = fkey; 
		this.fk_number = fkNumber;
		this.variable_isfk = true;
	}
	
	public void setNewVariable(String name, int type, int isString_length, boolean ispk, boolean isnn, boolean isun) {
		this.variable_name = name;
		this.variable_type = type;
		this.variable_length = isString_length;
		this.variable_ispk = ispk;
		this.variable_isfk = false;
		this.variable_isnn = isnn;
		this.variable_isun = isun;
	}
	
	public void printVariable() {
		System.out.print(this.variable_name);
		System.out.print(" ,[type]: " + this.variable_type);
		System.out.print(" ,[ispk]: " + this.variable_ispk);
		System.out.print(" ,[isfk]: " + this.variable_isfk);
		System.out.print(" ,[isnn]: " + this.variable_isnn);
		System.out.print(" ,[isun]: " + this.variable_isun + "\n");
		if(this.variable_isfk == true) {
			System.out.print("reference to table [" + this.fk_table + "] on variable [" + this.fk_key + "]\n");
		}
	}
	
	public String getVariableName() {
		return this.variable_name;
	}
	
	public int getVariableType() {
		return this.variable_type;
	}
	
	public int getVariableLength() {
		return this.variable_length;
	}
	
	public boolean getVariableIspk() {
		return this.variable_ispk;
	}
	
	public boolean getVariableIsnn() {
		return this.variable_isnn;
	}
	
	public boolean getVariableIsun() {
		return this.variable_isun;
	}
	
	public boolean getVariableIsfk() {
		return this.variable_isfk;
	}
	
	public String getForeignTable() {
		return this.fk_table;
	}
	
	public String getForeignKey() {
		return this.fk_key;
	}
	
	public int getForeignKeyNumber() {
		return this.fk_number;
	}
	
	public CLGOperatorNode getNnNode() {
		return this.nnNode;
	}
	
	public CLGOperatorNode getNnNodeNot() {
		return this.nnNodeNot;
	}
	
	public CLGOperatorNode getFkNode() {
		return this.fkNode;
	}
	
	public CLGOperatorNode getFkNodeNot() {
		return this.fkNodeNot;
	}
	
	public CLGOperatorNode getFkNode2() {
		return this.fkNode2;
	}
	
	public CLGOperatorNode getFkNode2Not() {
		return this.fkNode2Not;
	}
	
	public CLGOperatorNode getPkNode() {
		return this.pkNode;
	}
	
	public CLGOperatorNode getPkNodeNot() {
		return this.pkNodeNot;
	}
	
	public CLGOperatorNode getUnNode() {
		return this.unNode;
	}
	
	public CLGOperatorNode getUnNodeNot() {
		return this.unNodeNot;
	}
	
}