package sqlschemaAnalyzer;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;

public class tableCheck{
	
	final int aset = 255;
	
	// CHECK ( CEL CO CER )
	String[] checkVariables = new String[255];	//��check���Ҧ������W�� (ex. salary),�̧�Ū��������w�q�ƶq
	int numbersOfCheckVariable = 0;	//��check���p������`�� 
	String checkEquationL;	//��variable1���㪺�B�⦡ (ex. salary*50)
	String checkOperator;	
	String checkEquationR;	//��variable2���㪺�B�⦡
	
	int boundaryType = 0; //0���ΦҼ{boundary������(==/!=) 1�쥻�Dboundary-����ᬰboundary������(>/<) 2�쥻��boundary������(>=/<=)
	
	CLGOperatorNode ckNode;					//�Y��OPERATOR���OBOUNDARY not(<=/>=) �ϥΦ��զX
	CLGOperatorNode ckNodeNotBoundaryA;
	CLGOperatorNode ckNodeNotBoundaryB;
	
	CLGOperatorNode ckNodeBoundaryA;		//�Y��OPERATOR�OBOUNDARY(<=/>=) �ϥΦ��զX
	CLGOperatorNode ckNodeBoundaryB;
	CLGOperatorNode ckNodeNot;
	
	//UQ�ѻP 2�ӹ�
	boolean ck2Exist = false;
	
	String checkEquationL2;	//��variable1���㪺�B�⦡ (ex. salary*50)
	String checkOperator2;	
	String checkEquationR2;	//��variable2���㪺�B�⦡
	
	int boundaryType2 = 0; 
	
	CLGOperatorNode ckNode2;				//�Y��OPERATOR���OBOUNDARY not(<=/>=) �ϥΦ��զX
	CLGOperatorNode ckNode2NotBoundaryA;
	CLGOperatorNode ckNode2NotBoundaryB;
	
	CLGOperatorNode ckNode2BoundaryA;		//�Y��OPERATOR�OBOUNDARY(<=/>=) �ϥΦ��զX
	CLGOperatorNode ckNode2BoundaryB;
	CLGOperatorNode ckNode2Not;
	
	public void setCheckVariables(String v) {
		//�T�O�S���������
		for(int i=0; i<=this.numbersOfCheckVariable; i++) {
			if(i == this.numbersOfCheckVariable) {
				this.checkVariables[numbersOfCheckVariable] = v;
				this.numbersOfCheckVariable++;
			}
			if(v.equals(checkVariables[i])) {
				break;
			}
		}
	}
	
	public boolean findCheckVariable(String v) {
		//���ܼƦW�i�ӧ䦳�S���ۦP�W�٪� �Y����^true �Y�S����^false
		for(int i=0; i<=this.numbersOfCheckVariable; i++) {
			if(i == this.numbersOfCheckVariable) {
				return false;
			}
			if(v.equals(checkVariables[i])){
				return true;
			}
		}
		return false;
	}
	
	public void setCheck(String CEL, String CO, String CER) {
		this.checkEquationL = CEL;
		this.checkOperator = CO;
		this.checkEquationR = CER;
	} 
	
	public void setCheck(String CEL, String CO, String CER, CLGConstraint L, CLGConstraint R) {
		this.checkEquationL = CEL;
		this.checkOperator = CO;
		this.checkEquationR = CER;
		
		if(CO.equals(">")) {
			this.boundaryType = 1;
			
			this.ckNode = new CLGOperatorNode(">");
			this.ckNode.setLeftOperand(L);
			this.ckNode.setRightOperand(R);
			
			this.ckNodeNotBoundaryA = new CLGOperatorNode("<");
			this.ckNodeNotBoundaryA.setLeftOperand(L);
			this.ckNodeNotBoundaryA.setRightOperand(R);
			
			this.ckNodeNotBoundaryB = new CLGOperatorNode("==");
			this.ckNodeNotBoundaryB.setLeftOperand(L);
			this.ckNodeNotBoundaryB.setRightOperand(R);
		}
		else if(CO.equals("<")) {
			this.boundaryType = 1;
			
			this.ckNode = new CLGOperatorNode("<");
			this.ckNode.setLeftOperand(L);
			this.ckNode.setRightOperand(R);
			
			this.ckNodeNotBoundaryA = new CLGOperatorNode(">");
			this.ckNodeNotBoundaryA.setLeftOperand(L);
			this.ckNodeNotBoundaryA.setRightOperand(R);
			
			this.ckNodeNotBoundaryB = new CLGOperatorNode("==");
			this.ckNodeNotBoundaryB.setLeftOperand(L);
			this.ckNodeNotBoundaryB.setRightOperand(R);
		}
		else if(CO.equals("==")) {
			this.ckNode = new CLGOperatorNode("==");
			this.ckNode.setLeftOperand(L);
			this.ckNode.setRightOperand(R);
			
			this.ckNodeNot = new CLGOperatorNode("!=");
			this.ckNodeNot.setLeftOperand(L);
			this.ckNodeNot.setRightOperand(R);
		}
		else if(CO.equals("!=")) {
			this.ckNode = new CLGOperatorNode("!=");
			this.ckNode.setLeftOperand(L);
			this.ckNode.setRightOperand(R);
			
			this.ckNodeNot = new CLGOperatorNode("==");
			this.ckNodeNot.setLeftOperand(L);
			this.ckNodeNot.setRightOperand(R);
		}
		else if(CO.equals(">=")) {
			this.boundaryType = 2;
			
			this.ckNodeBoundaryA = new CLGOperatorNode(">");
			this.ckNodeBoundaryA.setLeftOperand(L);
			this.ckNodeBoundaryA.setRightOperand(R);
			
			this.ckNodeBoundaryB = new CLGOperatorNode("==");
			this.ckNodeBoundaryB.setLeftOperand(L);
			this.ckNodeBoundaryB.setRightOperand(R);
			
			this.ckNodeNot = new CLGOperatorNode("<");
			this.ckNodeNot.setLeftOperand(L);
			this.ckNodeNot.setRightOperand(R);
		}
		else if(CO.equals("<=")) {
			this.boundaryType = 2;
			
			this.ckNodeBoundaryA = new CLGOperatorNode("<");
			this.ckNodeBoundaryA.setLeftOperand(L);
			this.ckNodeBoundaryA.setRightOperand(R);
			
			this.ckNodeBoundaryB = new CLGOperatorNode("==");
			this.ckNodeBoundaryB.setLeftOperand(L);
			this.ckNodeBoundaryB.setRightOperand(R);
			
			this.ckNodeNot = new CLGOperatorNode(">");
			this.ckNodeNot.setLeftOperand(L);
			this.ckNodeNot.setRightOperand(R);
		}
		
	} 
	
	public void setCheck2(String CEL, String CO, String CER, CLGConstraint L, CLGConstraint R) {
		this.ck2Exist = true; 
		
		this.checkEquationL2 = CEL;
		this.checkOperator2 = CO;
		this.checkEquationR2 = CER;
		
		if(CO.equals(">")) {
			this.boundaryType2 = 1;
			
			this.ckNode2 = new CLGOperatorNode(">");
			this.ckNode2.setLeftOperand(L);
			this.ckNode2.setRightOperand(R);
			
			this.ckNode2NotBoundaryA = new CLGOperatorNode("<");
			this.ckNode2NotBoundaryA.setLeftOperand(L);
			this.ckNode2NotBoundaryA.setRightOperand(R);
			
			this.ckNode2NotBoundaryB = new CLGOperatorNode("==");
			this.ckNode2NotBoundaryB.setLeftOperand(L);
			this.ckNode2NotBoundaryB.setRightOperand(R);
		}
		else if(CO.equals("<")) {
			this.boundaryType2 = 1;
			
			this.ckNode2 = new CLGOperatorNode("<");
			this.ckNode2.setLeftOperand(L);
			this.ckNode2.setRightOperand(R);
			
			this.ckNode2NotBoundaryA = new CLGOperatorNode(">");
			this.ckNode2NotBoundaryA.setLeftOperand(L);
			this.ckNode2NotBoundaryA.setRightOperand(R);
			
			this.ckNode2NotBoundaryB = new CLGOperatorNode("==");
			this.ckNode2NotBoundaryB.setLeftOperand(L);
			this.ckNode2NotBoundaryB.setRightOperand(R);
		}
		else if(CO.equals("==")) {
			this.ckNode2 = new CLGOperatorNode("==");
			this.ckNode2.setLeftOperand(L);
			this.ckNode2.setRightOperand(R);
			
			this.ckNode2Not = new CLGOperatorNode("!=");
			this.ckNode2Not.setLeftOperand(L);
			this.ckNode2Not.setRightOperand(R);
		}
		else if(CO.equals("!=")) {
			this.ckNode2 = new CLGOperatorNode("!=");
			this.ckNode2.setLeftOperand(L);
			this.ckNode2.setRightOperand(R);
			
			this.ckNode2Not = new CLGOperatorNode("==");
			this.ckNode2Not.setLeftOperand(L);
			this.ckNode2Not.setRightOperand(R);
		}
		else if(CO.equals(">=")) {
			this.boundaryType2 = 2;
			
			this.ckNode2BoundaryA = new CLGOperatorNode(">");
			this.ckNode2BoundaryA.setLeftOperand(L);
			this.ckNode2BoundaryA.setRightOperand(R);
			
			this.ckNode2BoundaryB = new CLGOperatorNode("==");
			this.ckNode2BoundaryB.setLeftOperand(L);
			this.ckNode2BoundaryB.setRightOperand(R);
			
			this.ckNode2Not = new CLGOperatorNode("<");
			this.ckNode2Not.setLeftOperand(L);
			this.ckNode2Not.setRightOperand(R);
		}
		else if(CO.equals("<=")) {
			this.boundaryType2 = 2;
			
			this.ckNode2BoundaryA = new CLGOperatorNode("<");
			this.ckNode2BoundaryA.setLeftOperand(L);
			this.ckNode2BoundaryA.setRightOperand(R);
			
			this.ckNode2BoundaryB = new CLGOperatorNode("==");
			this.ckNode2BoundaryB.setLeftOperand(L);
			this.ckNode2BoundaryB.setRightOperand(R);
			
			this.ckNode2Not = new CLGOperatorNode(">");
			this.ckNode2Not.setLeftOperand(L);
			this.ckNode2Not.setRightOperand(R);
		}
		
	} 
	
	public void printCheck() {
		System.out.println("Check equationL : " + this.checkEquationL);
		System.out.println("Check operator : " + this.checkOperator);
		System.out.println("Check equationR : " + this.checkEquationR);
		System.out.print("Associated variables(columns) : ");
		for(int i=0; i<numbersOfCheckVariable; i++) {
			System.out.print("[" + checkVariables[i] + "], ");
		}
		System.out.println("\n " + numbersOfCheckVariable + " in total");
	}
	
	public String[] getVariables() {
		return this.checkVariables;
	}
	
	public int getNumbersOfCheckVariables() {
		return this.numbersOfCheckVariable;
	}
	
	public String getCheckEquationL() {
		return this.checkEquationL;
	}
	
	public String getCheckOperator() {
		return this.checkOperator;
	}
	
	public String getCheckEquationR() {
		return this.checkEquationR;
	}
	
	public CLGOperatorNode getCkNode() {
		return this.ckNode;
	}
	public CLGOperatorNode getCkNodeBoundaryA() {
		return this.ckNodeBoundaryA;
	}
	public CLGOperatorNode getCkNodeBoundaryB() {
		return this.ckNodeBoundaryB;
	}
	public CLGOperatorNode getCkNodeNot() {
		return this.ckNodeNot;
	}
	public CLGOperatorNode getCkNodeNotBoundaryA() {
		return this.ckNodeNotBoundaryA;
	}
	public CLGOperatorNode getCkNodeNotBoundaryB() {
		return this.ckNodeNotBoundaryB;
	}
	
	public int getBoundaryType() {
		return this.boundaryType;
	}
	
	public CLGOperatorNode getCkNode2() {
		return this.ckNode2;
	}
	public CLGOperatorNode getCkNode2BoundaryA() {
		return this.ckNode2BoundaryA;
	}
	public CLGOperatorNode getCkNode2BoundaryB() {
		return this.ckNode2BoundaryB;
	}
	public CLGOperatorNode getCkNode2Not() {
		return this.ckNode2Not;
	}
	public CLGOperatorNode getCkNode2NotBoundaryA() {
		return this.ckNode2NotBoundaryA;
	}
	public CLGOperatorNode getCkNode2NotBoundaryB() {
		return this.ckNode2NotBoundaryB;
	}
	
	public int getBoundaryType2() {
		return this.boundaryType2;
	}
	
	public boolean getCk2Exist() {
		return this.ck2Exist;
	}
}