package sqlschemaAnalyzer;

import java.util.ArrayList;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.transform.SQL2CLG;

public class schemaTable{
	
	final int aset = 255;
	
	String tableName = "";
	int numbersOfVariable = 0;
	int numbersOfCheck = 0;
	
	int numbersOfConstraintSet = 0;
	
	tableVariable[] TV = new tableVariable[aset];
	tableCheck[] TC = new tableCheck[aset];
	
	CLGGraph firstPath;
	CLGGraph tableCLG;
	
	SymbolTable st;
	
	ArrayList<String> localARG = new ArrayList<String>();
	ArrayList<String> localARGtype = new ArrayList<String>();
	ArrayList<String> foreignTableName = new ArrayList<String>();
	ArrayList<ArrayList<String>> foreignARG = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> foreignARGtype = new ArrayList<ArrayList<String>>();
	
	ArrayList<String> foreignTableNameTableCLGSkip = new ArrayList<String>();
	
	public void setTableName(String name) {
		System.out.println("New table create : " + name);
		this.tableName = name;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	
	public int getNumbersOfVariable() {
		return this.numbersOfVariable;
	}
	
	public int getNumbersOfCheck() {
		return this.numbersOfCheck;
	}
	
	public String getVariableName(int i) {
		return TV[i].getVariableName();
	}
	
	public int getVariableNo(String s) {
		for (int i=0; i<numbersOfVariable; i++) {
			if(TV[i].getVariableName().equals(s)) return i;
		}
		return -1;
	}
	
	public void addNumbersOfVariable() {
		this.numbersOfVariable++;
	}
	
	public void printVariable(int n) {
		System.out.print("Variable[" + n + "] : ");
		TV[n].printVariable();
	}
	
	public void printAllVariable() {
		for(int i = 0;i < this.numbersOfVariable;i++) {
			System.out.print("Variable[" + i + "] : ");
			TV[i].printVariable();
		}
	}
	
	/*
	public void setForeignKey(int key, String ftable, String fkey) {
		TV[key].setForeignKey(ftable, fkey);
	}
	*/
	
	public void addNumbersOfCheck() {
		this.numbersOfCheck++;
	}
	
	public void printAllCheck() {
		for(int i = 0;i < this.numbersOfCheck;i++) {
			System.out.println("Check[" + i + "] : ");
			TC[i].printCheck();
		}
	}
	
	//�O�d��
	/*
	public void createFirstPath(schemaTable[] schemaTables, int numbersOfTables) {
		this.firstPath = new CLGGraph();
		
		//startnode �򥻳]�w  �b��k�̫�]�w�ܼ� 
		ArrayList<String> mpal = new ArrayList<String>();
		ArrayList<String> mptal = new ArrayList<String>();
				
        for(int i=0; i<this.numbersOfVariable; i++) {
        	mpal.add(this.TV[i].getVariableName());
        	if(this.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(this.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(this.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	
        	//UQPK
        	if(this.TV[i].variable_ispk || this.TV[i].variable_isun) {
        		mpal.add(this.TV[i].getVariableName()+"U");
        		if(this.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(this.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(this.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
        
        this.localARG.addAll(mpal);
        this.localARGtype.addAll(mptal);
		
		//�����ͥ~����`�I�󳻺�
        
		for(int i=0; i<numbersOfVariable; i++) {
			for(int j=0; j<numbersOfTables; j++) {
				if(schemaTables[j].getTableName().equals(this.TV[i].getForeignTable())) {
					CLGGraph fkp = tempFirstPath(schemaTables, numbersOfTables, schemaTables[j]);
					this.firstPath.graphAnd(fkp);
					
					//�ǤJ�]�w
					mpal.addAll(((CLGStartNode) (fkp.getStartNode())).getMethodParameters());
					mptal.addAll(((CLGStartNode) (fkp.getStartNode())).getMethodParameterTypes());
					
					this.foreignTableName.add(this.TV[i].getForeignTable());
					this.foreignARG.add(((CLGStartNode) (fkp.getStartNode())).getMethodParameters());
					this.foreignARGtype.add(((CLGStartNode) (fkp.getStartNode())).getMethodParameterTypes());
				}
			}
		}
		
        
        
		for(int i=0; i<this.numbersOfVariable; i++) {
			
			//NN set
			if (this.TV[i].variable_isnn) {
				CLGGraph nn = new CLGGraph(this.TV[i].getNnNode());
				this.firstPath.graphAnd(nn);
			}
			
			//PK set
			if (this.TV[i].variable_ispk) {
				CLGGraph pk = new CLGGraph(this.TV[i].getPkNode());
				this.firstPath.graphAnd(pk);
			}
			
			//UQ set
			if (this.TV[i].variable_isun) {
				CLGGraph un = new CLGGraph(this.TV[i].getUnNode());
				this.firstPath.graphAnd(un);
			}
			
			//FK set
			if (this.TV[i].variable_isfk) {
				CLGGraph fk = new CLGGraph(this.TV[i].getFkNode());
				CLGGraph fk2 = new CLGGraph(this.TV[i].getFkNode2());
				this.firstPath.graphAnd(fk);
				this.firstPath.graphAnd(fk2);
			}
		}
		
		//checks
		for(int i=0; i<this.numbersOfCheck; i++) {
			
			//���`���Ĥ@��
			if(this.TC[i].getBoundaryType() == 1) {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				this.firstPath.graphAnd(ck);
			}
			else if(this.TC[i].getBoundaryType() == 2) {
				CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNodeBoundaryA());
				CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNodeBoundaryB());
				ck1.graphOr(ck2);
				this.firstPath.graphAnd(ck1);
			}
			else {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				this.firstPath.graphAnd(ck);
			}
			
			//�s�bUQ���ĤG�I
			if(this.TC[i].getCk2Exist()) {
				if(this.TC[i].getBoundaryType2() == 1) {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					this.firstPath.graphAnd(ck);
				}
				else if(this.TC[i].getBoundaryType2() == 2) {
					CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNode2BoundaryA());
					CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNode2BoundaryB());
					ck1.graphOr(ck2);
					this.firstPath.graphAnd(ck1);
				}
				else {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					this.firstPath.graphAnd(ck);
				}
			}
		}

		//resultNode
		CLGVariableNode rL = new CLGVariableNode("result") ;
		CLGLiteralNode rR = new CLGLiteralNode("success", "String") ;
        CLGOperatorNode rO = new CLGOperatorNode("=");
        rO.setLeftOperand(rL);
        rO.setRightOperand(rR);
        CLGGraph rG = new CLGGraph(rO);
        this.firstPath.graphAnd(rG);
        
        //�����]�wstartnode
        //start node should define classname  methodname returntype parameters and parameters' type
        ((CLGStartNode) (this.firstPath.getStartNode())).setClassName(this.tableName);
		((CLGStartNode) (this.firstPath.getStartNode())).setMethodName(this.tableName);
		((CLGStartNode) (this.firstPath.getStartNode())).setRetType("String");
		((CLGStartNode) (this.firstPath.getStartNode())).setMethodParameters(mpal);
		((CLGStartNode) (this.firstPath.getStartNode())).setMethodParameterTypes(mptal);
		
	}
	
	
	
	public void createTableCLG(schemaTable[] schemaTables, int numbersOfTables) {
		
		this.tableCLG = new CLGGraph();
		
		//startnode �򥻳]�w  �b��k�̫�]�w�ܼ� 
		ArrayList<String> mpal = new ArrayList<String>();
		ArrayList<String> mptal = new ArrayList<String>();
				
        for(int i=0; i< this.numbersOfVariable; i++) {
        	mpal.add(this.TV[i].getVariableName());
        	if(this.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(this.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(this.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	
        	//UQPK
        	if(this.TV[i].variable_ispk || this.TV[i].variable_isun) {
        		mpal.add(this.TV[i].getVariableName()+"U");
        		if(this.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(this.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(this.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
		
		//�����ͥ~����`�I�󳻺�
		for(int i=0; i<numbersOfVariable; i++) {
			for(int j=0; j<numbersOfTables; j++) {
				if(schemaTables[j].getTableName().equals(this.TV[i].getForeignTable())) {
					CLGGraph fkp = tempFirstPath(schemaTables, numbersOfTables, schemaTables[j]);
					this.tableCLG.graphAnd(fkp);
					
					//�ǤJ�]�w
					mpal.addAll(((CLGStartNode) (fkp.getStartNode())).getMethodParameters());
					mptal.addAll(((CLGStartNode) (fkp.getStartNode())).getMethodParameterTypes());
				}
			}
		}
		
		//���������| �O���`�I�ռƶq
		CLGGraph body = new CLGGraph();
		
		for(int i=0; i<this.numbersOfVariable; i++) {
			
			//NN set
			if (this.TV[i].variable_isnn) {
				CLGGraph nn = new CLGGraph(this.TV[i].getNnNode());
				body.graphAnd(nn);
				this.numbersOfConstraintSet++;
			}
			
			//PK set
			if (this.TV[i].variable_ispk) {
				CLGGraph pk = new CLGGraph(this.TV[i].getPkNode());
				body.graphAnd(pk);
				this.numbersOfConstraintSet++;
			}
			
			//UQ set
			if (this.TV[i].variable_isun) {
				CLGGraph un = new CLGGraph(this.TV[i].getUnNode());
				body.graphAnd(un);
				this.numbersOfConstraintSet++;
			}
			
			//FK set
			if (this.TV[i].variable_isfk) {
				CLGGraph fk = new CLGGraph(this.TV[i].getFkNode());
				CLGGraph fk2 = new CLGGraph(this.TV[i].getFkNode2());
				body.graphAnd(fk);
				body.graphAnd(fk2);
				this.numbersOfConstraintSet++;
			}
		}
		
		//checks
		for(int i=0; i<this.numbersOfCheck; i++) {
			
			//���`���Ĥ@��
			if(this.TC[i].getBoundaryType() == 1) {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				body.graphAnd(ck);
			}
			else if(this.TC[i].getBoundaryType() == 2) {
				CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNodeBoundaryA());
				CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNodeBoundaryB());
				ck1.graphOr(ck2);
				body.graphAnd(ck1);
			}
			else {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				body.graphAnd(ck);
			}
			
			//�s�bUQ���ĤG�I
			if(this.TC[i].getCk2Exist()) {
				if(this.TC[i].getBoundaryType2() == 1) {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					body.graphAnd(ck);
				}
				else if(this.TC[i].getBoundaryType2() == 2) {
					CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNode2BoundaryA());
					CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNode2BoundaryB());
					ck1.graphOr(ck2);
					body.graphAnd(ck1);
				}
				else {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					body.graphAnd(ck);
				}
			}
			
			this.numbersOfConstraintSet++;
		}

		//resultNode
		CLGVariableNode rL = new CLGVariableNode("result") ;
		CLGLiteralNode rR = new CLGLiteralNode("success", "String") ;
        CLGOperatorNode rO = new CLGOperatorNode("=");
        rO.setLeftOperand(rL);
        rO.setRightOperand(rR);
        CLGGraph rG = new CLGGraph(rO);
        body.graphAnd(rG);
        
        //�}�l���j
        
        for(int k=0; k<this.numbersOfConstraintSet; k++) {
        	CLGGraph nextPath = new CLGGraph();
        	int currentConstraint = 0;
        	String errorMessage = "";
        	
        	//�������|�O���F���ͿW�S�ʮרҴ��ꪺ���X ���w�糧�a���ĤG���~�����p���ͭ���`�I �_�h�~�����p��ܥ��a�������|���w����
        	//�����X �]�ȾA�Υ��@�����a�� ����ʤ��|�v�T���ո�ƺc��       	
        	boolean thisPathIsAboutPKUN = false; 
        	
        	//�������|�O���F�@��첣��NOT NULL
        	//���ꪺ���X �����ͻP������������L�`�I �D�n�� CHECK FOREIGN KEY
        	
        	boolean thisPathIsAboutNN = false;
        	int thisPathIsAboutNNcolumn = -1;
            
        	//keywords
        	for(int i=0; i<this.numbersOfVariable; i++) {
        		//NN set
        		if (this.TV[i].variable_isnn) {
        			
        			if(k == currentConstraint) {
        				CLGGraph nnn = new CLGGraph(this.TV[i].getNnNodeNot());
        				nextPath.graphAnd(nnn);
        					
        				errorMessage = new String(this.TV[i].getVariableName() + "_nn");
        				thisPathIsAboutNN = true;
        				thisPathIsAboutNNcolumn = i;
        			}
        			else {
        				CLGGraph nn = new CLGGraph(this.TV[i].getNnNode());
        				nextPath.graphAnd(nn);
        			}
        				
        			currentConstraint++;
        			
        			
        		}
        		
        		//PK set
        		if (this.TV[i].variable_ispk && !thisPathIsAboutNN) {
        			if(k == currentConstraint) {
        				CLGGraph pkn = new CLGGraph(this.TV[i].getPkNodeNot());
        				nextPath.graphAnd(pkn);
        				
        				errorMessage = new String(this.TV[i].getVariableName() + "_pk");
        				thisPathIsAboutPKUN = true;
        			}
        			else {
        				CLGGraph pk = new CLGGraph(this.TV[i].getPkNode());
        				nextPath.graphAnd(pk);
        			}
        			
        			currentConstraint++;
        		}
        		
        		//UQ set
        		if (this.TV[i].variable_isun && !thisPathIsAboutNN) {
        			if(k == currentConstraint) {
        				CLGGraph unn = new CLGGraph(this.TV[i].getUnNodeNot());
        				nextPath.graphAnd(unn);
        				
        				errorMessage = new String(this.TV[i].getVariableName() + "_un");
        				thisPathIsAboutPKUN = true;
        			}
        			else {
        				CLGGraph un = new CLGGraph(this.TV[i].getUnNode());
        				nextPath.graphAnd(un);
        			}
        			
        			currentConstraint++;
        		}
        		
        		//FK set
        		if (this.TV[i].variable_isfk && !thisPathIsAboutNN) {
        			if(k == currentConstraint) {
        				
        				CLGGraph fkn = new CLGGraph(this.TV[i].getFkNodeNot());
        				CLGGraph fk2n = new CLGGraph(this.TV[i].getFkNode2Not());
        				nextPath.graphAnd(fkn);
        				nextPath.graphAnd(fk2n);
        				
        				errorMessage = new String(this.TV[i].getVariableName() + "_fk_"+ this.TV[i].getForeignTable() + "_" + this.TV[i].getForeignKey());
        				
        			}
        			else {
        				CLGGraph fk = new CLGGraph(this.TV[i].getFkNode());
        				
        				nextPath.graphAnd(fk);
        				
        				
        				if (!thisPathIsAboutPKUN) {
        					CLGGraph fk2 = new CLGGraph(this.TV[i].getFkNode2());
        					nextPath.graphAnd(fk2);
        				}
        			}
        			
        			currentConstraint++;
        		}
        	}
        
			//checks
        	for(int i=0; i<this.numbersOfCheck; i++) {
        		
        		//�H��NN���|�� �P�_���O�_�s�bCHECK�̭� �Y���u �����͸�CHECK�`�I
        		boolean skipThisCk = false;
        		if(thisPathIsAboutNN) {
        			for(int j=0; j<this.TC[i].numbersOfCheckVariable; j++) {
            			if(this.TV[thisPathIsAboutNNcolumn].getVariableName().equals(this.TC[i].checkVariables[j])) {
            				skipThisCk = true;
            			}
            		}
        		}
        		
        		if(!skipThisCk) {
        			if(k == currentConstraint) {
            			//���`���Ĥ@��
            			if(this.TC[i].getBoundaryType() == 1) {
            				CLGGraph ck1n = new CLGGraph(this.TC[i].getCkNodeNotBoundaryA());
            				CLGGraph ck2n = new CLGGraph(this.TC[i].getCkNodeNotBoundaryB());
            				ck1n.graphOr(ck2n);
            				nextPath.graphAnd(ck1n);
            			}
            			else if(this.TC[i].getBoundaryType() == 2) {
            				CLGGraph ckn = new CLGGraph(this.TC[i].getCkNodeNot());
            				nextPath.graphAnd(ckn);
            			}
            			else {
            				CLGGraph ckn = new CLGGraph(this.TC[i].getCkNodeNot());
            				nextPath.graphAnd(ckn);
            			}
            			
            			//�s�bUQ���ĤG�I
            			if(this.TC[i].getCk2Exist()) {
            				if(this.TC[i].getBoundaryType2() == 1) {
            					CLGGraph ck1n = new CLGGraph(this.TC[i].getCkNode2NotBoundaryA());
            					CLGGraph ck2n = new CLGGraph(this.TC[i].getCkNode2NotBoundaryB());
            					ck1n.graphOr(ck2n);
            					nextPath.graphAnd(ck1n);
            				}
            				else if(this.TC[i].getBoundaryType2() == 2) {
            					CLGGraph ckn = new CLGGraph(this.TC[i].getCkNode2Not());
            					nextPath.graphAnd(ckn);
            				}
            				else {
            					CLGGraph ckn = new CLGGraph(this.TC[i].getCkNode2Not());
            					nextPath.graphAnd(ckn);
            				}
            			}
            			
            			errorMessage = "chk_" + (i+1);
            		}
            		else {
            			//���`���Ĥ@��
            			if(this.TC[i].getBoundaryType() == 1) {
            				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
            				nextPath.graphAnd(ck);
            			}
            			else if(this.TC[i].getBoundaryType() == 2) {
            				CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNodeBoundaryA());
            				CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNodeBoundaryB());
            				ck1.graphOr(ck2);
            				nextPath.graphAnd(ck1);
            			}
            			else {
            				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
            				nextPath.graphAnd(ck);
            			}
            			
            			//�s�bUQ���ĤG�I
            			if(this.TC[i].getCk2Exist()) {
            				if(this.TC[i].getBoundaryType2() == 1) {
            					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
            					nextPath.graphAnd(ck);
            				}
            				else if(this.TC[i].getBoundaryType2() == 2) {
            					CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNode2BoundaryA());
            					CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNode2BoundaryB());
            					ck1.graphOr(ck2);
            					nextPath.graphAnd(ck1);
            				}
            				else {
            					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
            					nextPath.graphAnd(ck);
            				}
            			}
            		}
        		}
        		
        		currentConstraint++;
        	}
        	
        	//resultNodeConnect
        	CLGVariableNode EL = new CLGVariableNode("result") ;
            CLGOperatorNode EO = new CLGOperatorNode("=");
            CLGGraph EG = new CLGGraph();
            CLGLiteralNode ER = new CLGLiteralNode(errorMessage, "String") ;
	        EO.setLeftOperand(EL);
	        EO.setRightOperand(ER);
	        EG = new CLGGraph(EO);
            nextPath.graphAnd(EG);
            
            System.out.println("["+k+"]: " + errorMessage);
            
            body.graphOr(nextPath);
        }

		//���� �`�M
        tableCLG.graphAnd(body);
        
        //�����]�wstartnode
        //start node should define classname  methodname returntype parameters and parameters' type
        ((CLGStartNode) (this.tableCLG.getStartNode())).setClassName(this.tableName);
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodName(this.tableName);
		((CLGStartNode) (this.tableCLG.getStartNode())).setRetType("String");
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodParameters(mpal);
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodParameterTypes(mptal);
        
	}
	
	public CLGGraph tempFirstPath(schemaTable[] schemaTables, int numbersOfTables, schemaTable fkTable) {
		//�ѼƥΩ󲣥� ���k��fkpath
		//Ū��fkTable���ݩʧ�F��s�TfkPath
		
		CLGGraph fkPath = new CLGGraph();
		
		//startnode �򥻳]�w  �b��k�̫�]�w�ܼ� fk�ɥi��|�l��
		ArrayList<String> mpal = new ArrayList<String>();
        ArrayList<String> mptal = new ArrayList<String>();
        	
        for(int i=0; i<fkTable.numbersOfVariable; i++) {
        	mpal.add(fkTable.TV[i].getVariableName());
        	if(fkTable.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(fkTable.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(fkTable.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	if(fkTable.TV[i].variable_ispk || fkTable.TV[i].variable_isun) {
        		mpal.add(fkTable.TV[i].getVariableName()+"U");
            	if(fkTable.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(fkTable.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(fkTable.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
		
		//keywords
		for(int i=0; i<fkTable.numbersOfVariable; i++) {
			//NN set
			if (fkTable.TV[i].variable_isnn) {			
				CLGGraph nn = new CLGGraph(fkTable.TV[i].getNnNode());
				fkPath.graphAnd(nn);
			}
				
			//PK set
			if (fkTable.TV[i].variable_ispk) {
				CLGGraph pk = new CLGGraph(fkTable.TV[i].getPkNode());
				fkPath.graphAnd(pk);
			}
			
			//UQ set
			if (fkTable.TV[i].variable_isun) {
				CLGGraph un = new CLGGraph(fkTable.TV[i].getUnNode());
				fkPath.graphAnd(un);
			}
			
			//FK set
			if (fkTable.TV[i].variable_isfk) {
				CLGGraph fk = new CLGGraph(fkTable.TV[i].getFkNode());
				CLGGraph fk2 = new CLGGraph(fkTable.TV[i].getFkNode2());
				fkPath.graphAnd(fk);
				fkPath.graphAnd(fk2);
			}
		}
		
		//checks
		for(int i=0; i<fkTable.numbersOfCheck; i++) {
			if(fkTable.TC[i].getBoundaryType() == 1) {
				CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode());
				fkPath.graphAnd(ck);
			}
			else if(fkTable.TC[i].getBoundaryType() == 2) {
				CLGGraph ck1 = new CLGGraph(fkTable.TC[i].getCkNodeBoundaryA());
				CLGGraph ck2 = new CLGGraph(fkTable.TC[i].getCkNodeBoundaryB());
				ck1.graphOr(ck2);
				fkPath.graphAnd(ck1);
			}
			else {
				CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode());
				fkPath.graphAnd(ck);
			}
			if(fkTable.TC[i].getCk2Exist()) {
				if(fkTable.TC[i].getBoundaryType2() == 1) {
					CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode2());
					fkPath.graphAnd(ck);
				}
				else if(fkTable.TC[i].getBoundaryType2() == 2) {
					CLGGraph ck1 = new CLGGraph(fkTable.TC[i].getCkNode2BoundaryA());
					CLGGraph ck2 = new CLGGraph(fkTable.TC[i].getCkNode2BoundaryB());
					ck1.graphOr(ck2);
					fkPath.graphAnd(ck1);
				}
				else {
					CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode2());
					fkPath.graphAnd(ck);
				}
			}
		}
		
		//�o�̤��� resultNode 
        
        //�����]�wstartnode
        //start node should define classname  methodname returntype parameters and parameters' type
        ((CLGStartNode) (fkPath.getStartNode())).setClassName(fkTable.tableName);
		((CLGStartNode) (fkPath.getStartNode())).setMethodName(fkTable.tableName);
		((CLGStartNode) (fkPath.getStartNode())).setRetType("String");
		((CLGStartNode) (fkPath.getStartNode())).setMethodParameters(mpal);
		((CLGStartNode) (fkPath.getStartNode())).setMethodParameterTypes(mptal);
		
		return fkPath;
	}
	
	*/
	
	public void createFirstPath(schemaTable[] schemaTables, int numbersOfTables) {
		this.firstPath = new CLGGraph();
		
		//startnode �򥻳]�w  �b��k�̫�]�w�ܼ� 
		ArrayList<String> mpal = new ArrayList<String>();
		ArrayList<String> mptal = new ArrayList<String>();
				
        for(int i=0; i<this.numbersOfVariable; i++) {
        	mpal.add(this.TV[i].getVariableName());
        	if(this.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(this.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(this.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	
        	//UQPK
        	if(this.TV[i].variable_ispk || this.TV[i].variable_isun) {
        		mpal.add(this.TV[i].getVariableName()+"U");
        		if(this.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(this.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(this.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
        
        this.localARG.addAll(mpal);
        this.localARGtype.addAll(mptal);
		
		//�����ͥ~����`�I�󳻺�
        
		for(int i=0; i<numbersOfVariable; i++) {
			for(int j=0; j<numbersOfTables; j++) {
				if(schemaTables[j].getTableName().equals(this.TV[i].getForeignTable())) {
					
					//���ƪ��~��������
					if(!this.foreignTableName.contains(this.TV[i].getForeignTable())) {
						//���ͥ~���귽
						tempPath fkp = tempFirstPath(schemaTables, numbersOfTables, schemaTables[j]);
						
						//�s���ϧ�
						this.firstPath.graphAnd(fkp.tempFkPath);
						
						//�]�w�Ѽ�-startNode
						for(int k=0; k<fkp.foreignColumn.size(); k++) {
							mpal.addAll(fkp.foreignColumn.get(k));
							mptal.addAll(fkp.foreignColumnType.get(k));
						}
						
						//�]�w�~���ݩ�
						this.foreignTableName.addAll(fkp.foreignTableName);
						this.foreignARG.addAll(fkp.foreignColumn);
						this.foreignARGtype.addAll(fkp.foreignColumnType);
						
					}
				}
			}
		}
		
        
        
		for(int i=0; i<this.numbersOfVariable; i++) {
			
			//NN set
			if (this.TV[i].variable_isnn) {
				CLGGraph nn = new CLGGraph(this.TV[i].getNnNode());
				this.firstPath.graphAnd(nn);
			}
			
			//PK set
			if (this.TV[i].variable_ispk) {
				CLGGraph pk = new CLGGraph(this.TV[i].getPkNode());
				this.firstPath.graphAnd(pk);
			}
			
			//UQ set
			if (this.TV[i].variable_isun) {
				CLGGraph un = new CLGGraph(this.TV[i].getUnNode());
				this.firstPath.graphAnd(un);
			}
			
			//FK set
			if (this.TV[i].variable_isfk) {
				CLGGraph fk = new CLGGraph(this.TV[i].getFkNode());
				CLGGraph fk2 = new CLGGraph(this.TV[i].getFkNode2());
				this.firstPath.graphAnd(fk);
				this.firstPath.graphAnd(fk2);
			}
		}
		
		//checks
		for(int i=0; i<this.numbersOfCheck; i++) {
			
			//���`���Ĥ@��
			if(this.TC[i].getBoundaryType() == 1) {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				this.firstPath.graphAnd(ck);
			}
			else if(this.TC[i].getBoundaryType() == 2) {
				CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNodeBoundaryA());
				CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNodeBoundaryB());
				ck1.graphOr(ck2);
				this.firstPath.graphAnd(ck1);
			}
			else {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				this.firstPath.graphAnd(ck);
			}
			
			//�s�bUQ���ĤG�I
			if(this.TC[i].getCk2Exist()) {
				if(this.TC[i].getBoundaryType2() == 1) {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					this.firstPath.graphAnd(ck);
				}
				else if(this.TC[i].getBoundaryType2() == 2) {
					CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNode2BoundaryA());
					CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNode2BoundaryB());
					ck1.graphOr(ck2);
					this.firstPath.graphAnd(ck1);
				}
				else {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					this.firstPath.graphAnd(ck);
				}
			}
		}

		//resultNode
		CLGVariableNode rL = new CLGVariableNode("result") ;
		CLGLiteralNode rR = new CLGLiteralNode("success", "String") ;
        CLGOperatorNode rO = new CLGOperatorNode("=");
        rO.setLeftOperand(rL);
        rO.setRightOperand(rR);
        CLGGraph rG = new CLGGraph(rO);
        this.firstPath.graphAnd(rG);
        
        //�����]�wstartnode
        //start node should define classname  methodname returntype parameters and parameters' type
        ((CLGStartNode) (this.firstPath.getStartNode())).setClassName(this.tableName);
		((CLGStartNode) (this.firstPath.getStartNode())).setMethodName(this.tableName);
		((CLGStartNode) (this.firstPath.getStartNode())).setRetType("String");
		((CLGStartNode) (this.firstPath.getStartNode())).setMethodParameters(mpal);
		((CLGStartNode) (this.firstPath.getStartNode())).setMethodParameterTypes(mptal);
		
	} 
	
	
	
	public void createTableCLG(schemaTable[] schemaTables, int numbersOfTables) {
		
		this.tableCLG = new CLGGraph();
		
		//startnode �򥻳]�w  �b��k�̫�]�w�ܼ� 
		ArrayList<String> mpal = new ArrayList<String>();
		ArrayList<String> mptal = new ArrayList<String>();
				
        for(int i=0; i< this.numbersOfVariable; i++) {
        	mpal.add(this.TV[i].getVariableName());
        	if(this.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(this.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(this.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	
        	//UQPK
        	if(this.TV[i].variable_ispk || this.TV[i].variable_isun) {
        		mpal.add(this.TV[i].getVariableName()+"U");
        		if(this.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(this.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(this.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
		
		//�����ͥ~����`�I�󳻺�
		for(int i=0; i<numbersOfVariable; i++) {
			for(int j=0; j<numbersOfTables; j++) {
				if(schemaTables[j].getTableName().equals(this.TV[i].getForeignTable())) {
					
					//���ƪ��~��������
					if(!this.foreignTableNameTableCLGSkip.contains(this.TV[i].getForeignTable())) {
						//���ͥ~���귽
						tempPath fkp = tempFirstPath(schemaTables, numbersOfTables, schemaTables[j]);
						
						//�s���ϧ�
						this.tableCLG.graphAnd(fkp.tempFkPath);
						
						//�]�w�Ѽ�-startNode
						for(int k=0; k<fkp.foreignColumn.size(); k++) {
							mpal.addAll(fkp.foreignColumn.get(k));
							mptal.addAll(fkp.foreignColumnType.get(k));
						}
						
						//�]�w���ͪ��~����
						this.foreignTableNameTableCLGSkip.addAll(fkp.foreignTableName);
					}
				}
			}
		}
		
		//���������| �O���`�I�ռƶq
		CLGGraph body = new CLGGraph();
		
		for(int i=0; i<this.numbersOfVariable; i++) {
			
			//NN set
			if (this.TV[i].variable_isnn) {
				CLGGraph nn = new CLGGraph(this.TV[i].getNnNode());
				body.graphAnd(nn);
				this.numbersOfConstraintSet++;
			}
			
			//PK set
			if (this.TV[i].variable_ispk) {
				CLGGraph pk = new CLGGraph(this.TV[i].getPkNode());
				body.graphAnd(pk);
				this.numbersOfConstraintSet++;
			}
			
			//UQ set
			if (this.TV[i].variable_isun) {
				CLGGraph un = new CLGGraph(this.TV[i].getUnNode());
				body.graphAnd(un);
				this.numbersOfConstraintSet++;
			}
			
			//FK set
			if (this.TV[i].variable_isfk) {
				CLGGraph fk = new CLGGraph(this.TV[i].getFkNode());
				CLGGraph fk2 = new CLGGraph(this.TV[i].getFkNode2());
				body.graphAnd(fk);
				body.graphAnd(fk2);
				this.numbersOfConstraintSet++;
			}
		}
		
		//checks
		for(int i=0; i<this.numbersOfCheck; i++) {
			
			//���`���Ĥ@��
			if(this.TC[i].getBoundaryType() == 1) {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				body.graphAnd(ck);
			}
			else if(this.TC[i].getBoundaryType() == 2) {
				CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNodeBoundaryA());
				CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNodeBoundaryB());
				ck1.graphOr(ck2);
				body.graphAnd(ck1);
			}
			else {
				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
				body.graphAnd(ck);
			}
			
			//�s�bUQ���ĤG�I
			if(this.TC[i].getCk2Exist()) {
				if(this.TC[i].getBoundaryType2() == 1) {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					body.graphAnd(ck);
				}
				else if(this.TC[i].getBoundaryType2() == 2) {
					CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNode2BoundaryA());
					CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNode2BoundaryB());
					ck1.graphOr(ck2);
					body.graphAnd(ck1);
				}
				else {
					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
					body.graphAnd(ck);
				}
			}
			
			this.numbersOfConstraintSet++;
		}

		//resultNode
		CLGVariableNode rL = new CLGVariableNode("result") ;
		CLGLiteralNode rR = new CLGLiteralNode("success", "String") ;
        CLGOperatorNode rO = new CLGOperatorNode("=");
        rO.setLeftOperand(rL);
        rO.setRightOperand(rR);
        CLGGraph rG = new CLGGraph(rO);
        body.graphAnd(rG);
        
        //�}�l���j
        
        for(int k=0; k<this.numbersOfConstraintSet; k++) {
        	CLGGraph nextPath = new CLGGraph();
        	int currentConstraint = 0;
        	String errorMessage = "";
        	
        	//�������|�O���F���ͿW�S�ʮרҴ��ꪺ���X ���w�糧�a���ĤG���~�����p���ͭ���`�I �_�h�~�����p��ܥ��a�������|���w����
        	//�����X �]�ȾA�Υ��@�����a�� ����ʤ��|�v�T���ո�ƺc��       	
        	boolean thisPathIsAboutPKUN = false; 
        	
        	//�������|�O���F�@��첣��NOT NULL
        	//���ꪺ���X �����ͻP������������L�`�I �D�n�� CHECK FOREIGN KEY
        	
        	boolean thisPathIsAboutNN = false;
        	int thisPathIsAboutNNcolumn = -1;
            
        	//keywords
        	for(int i=0; i<this.numbersOfVariable; i++) {
        		//NN set
        		if (this.TV[i].variable_isnn) {
        			
        			if(k == currentConstraint) {
        				CLGGraph nnn = new CLGGraph(this.TV[i].getNnNodeNot());
        				nextPath.graphAnd(nnn);
        					
        				errorMessage = new String(this.TV[i].getVariableName() + "_nn");
        				thisPathIsAboutNN = true;
        				thisPathIsAboutNNcolumn = i;
        			}
        			else {
        				CLGGraph nn = new CLGGraph(this.TV[i].getNnNode());
        				nextPath.graphAnd(nn);
        			}
        				
        			currentConstraint++;
        			
        			
        		}
        		
        		//PK set
        		if (this.TV[i].variable_ispk && !thisPathIsAboutNN) {
        			if(k == currentConstraint) {
        				CLGGraph pkn = new CLGGraph(this.TV[i].getPkNodeNot());
        				nextPath.graphAnd(pkn);
        				
        				errorMessage = new String(this.TV[i].getVariableName() + "_pk");
        				thisPathIsAboutPKUN = true;
        			}
        			else {
        				CLGGraph pk = new CLGGraph(this.TV[i].getPkNode());
        				nextPath.graphAnd(pk);
        			}
        			
        			currentConstraint++;
        		}
        		
        		//UQ set
        		if (this.TV[i].variable_isun && !thisPathIsAboutNN) {
        			if(k == currentConstraint) {
        				CLGGraph unn = new CLGGraph(this.TV[i].getUnNodeNot());
        				nextPath.graphAnd(unn);
        				
        				errorMessage = new String(this.TV[i].getVariableName() + "_un");
        				thisPathIsAboutPKUN = true;
        			}
        			else {
        				CLGGraph un = new CLGGraph(this.TV[i].getUnNode());
        				nextPath.graphAnd(un);
        			}
        			
        			currentConstraint++;
        		}
        		
        		//FK set
        		if (this.TV[i].variable_isfk && !thisPathIsAboutNN) {
        			if(k == currentConstraint) {
        				
        				CLGGraph fkn = new CLGGraph(this.TV[i].getFkNodeNot());
        				CLGGraph fk2n = new CLGGraph(this.TV[i].getFkNode2Not());
        				nextPath.graphAnd(fkn);
        				nextPath.graphAnd(fk2n);
        				
        				errorMessage = new String(this.TV[i].getVariableName() + "_fk_"+ this.TV[i].getForeignTable() + "_" + this.TV[i].getForeignKey());
        				
        			}
        			else {
        				CLGGraph fk = new CLGGraph(this.TV[i].getFkNode());
        				
        				nextPath.graphAnd(fk);
        				
        				
        				if (!thisPathIsAboutPKUN) {
        					CLGGraph fk2 = new CLGGraph(this.TV[i].getFkNode2());
        					nextPath.graphAnd(fk2);
        				}
        			}
        			
        			currentConstraint++;
        		}
        	}
        
			//checks
        	for(int i=0; i<this.numbersOfCheck; i++) {
        		
        		//�H��NN���|�� �P�_���O�_�s�bCHECK�̭� �Y���u �����͸�CHECK�`�I
        		boolean skipThisCk = false;
        		if(thisPathIsAboutNN) {
        			for(int j=0; j<this.TC[i].numbersOfCheckVariable; j++) {
            			if(this.TV[thisPathIsAboutNNcolumn].getVariableName().equals(this.TC[i].checkVariables[j])) {
            				skipThisCk = true;
            			}
            		}
        		}
        		
        		if(!skipThisCk) {
        			if(k == currentConstraint) {
            			//���`���Ĥ@��
            			if(this.TC[i].getBoundaryType() == 1) {
            				CLGGraph ck1n = new CLGGraph(this.TC[i].getCkNodeNotBoundaryA());
            				CLGGraph ck2n = new CLGGraph(this.TC[i].getCkNodeNotBoundaryB());
            				ck1n.graphOr(ck2n);
            				nextPath.graphAnd(ck1n);
            			}
            			else if(this.TC[i].getBoundaryType() == 2) {
            				CLGGraph ckn = new CLGGraph(this.TC[i].getCkNodeNot());
            				nextPath.graphAnd(ckn);
            			}
            			else {
            				CLGGraph ckn = new CLGGraph(this.TC[i].getCkNodeNot());
            				nextPath.graphAnd(ckn);
            			}
            			
            			//�s�bUQ���ĤG�I
            			if(this.TC[i].getCk2Exist()) {
            				if(this.TC[i].getBoundaryType2() == 1) {
            					CLGGraph ck1n = new CLGGraph(this.TC[i].getCkNode2NotBoundaryA());
            					CLGGraph ck2n = new CLGGraph(this.TC[i].getCkNode2NotBoundaryB());
            					ck1n.graphOr(ck2n);
            					nextPath.graphAnd(ck1n);
            				}
            				else if(this.TC[i].getBoundaryType2() == 2) {
            					CLGGraph ckn = new CLGGraph(this.TC[i].getCkNode2Not());
            					nextPath.graphAnd(ckn);
            				}
            				else {
            					CLGGraph ckn = new CLGGraph(this.TC[i].getCkNode2Not());
            					nextPath.graphAnd(ckn);
            				}
            			}
            			
            			errorMessage = "chk_" + (i+1);
            		}
            		else {
            			//���`���Ĥ@��
            			if(this.TC[i].getBoundaryType() == 1) {
            				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
            				nextPath.graphAnd(ck);
            			}
            			else if(this.TC[i].getBoundaryType() == 2) {
            				CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNodeBoundaryA());
            				CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNodeBoundaryB());
            				ck1.graphOr(ck2);
            				nextPath.graphAnd(ck1);
            			}
            			else {
            				CLGGraph ck = new CLGGraph(this.TC[i].getCkNode());
            				nextPath.graphAnd(ck);
            			}
            			
            			//�s�bUQ���ĤG�I
            			if(this.TC[i].getCk2Exist()) {
            				if(this.TC[i].getBoundaryType2() == 1) {
            					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
            					nextPath.graphAnd(ck);
            				}
            				else if(this.TC[i].getBoundaryType2() == 2) {
            					CLGGraph ck1 = new CLGGraph(this.TC[i].getCkNode2BoundaryA());
            					CLGGraph ck2 = new CLGGraph(this.TC[i].getCkNode2BoundaryB());
            					ck1.graphOr(ck2);
            					nextPath.graphAnd(ck1);
            				}
            				else {
            					CLGGraph ck = new CLGGraph(this.TC[i].getCkNode2());
            					nextPath.graphAnd(ck);
            				}
            			}
            		}
        		}
        		
        		currentConstraint++;
        	}
        	
        	//resultNodeConnect
        	CLGVariableNode EL = new CLGVariableNode("result") ;
            CLGOperatorNode EO = new CLGOperatorNode("=");
            CLGGraph EG = new CLGGraph();
            CLGLiteralNode ER = new CLGLiteralNode(errorMessage, "String") ;
	        EO.setLeftOperand(EL);
	        EO.setRightOperand(ER);
	        EG = new CLGGraph(EO);
            nextPath.graphAnd(EG);
            
            System.out.println("["+k+"]: " + errorMessage);
            
            body.graphOr(nextPath);
        }

		//���� �`�M
        tableCLG.graphAnd(body);
        
        //�����]�wstartnode
        //start node should define classname  methodname returntype parameters and parameters' type
        ((CLGStartNode) (this.tableCLG.getStartNode())).setClassName(this.tableName);
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodName(this.tableName);
		((CLGStartNode) (this.tableCLG.getStartNode())).setRetType("String");
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodParameters(mpal);
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodParameterTypes(mptal);
        
	}
	
public void createTableCLGNew(schemaTable[] schemaTables, int numbersOfTables) {
		
		this.tableCLG = new CLGGraph();
		
		//startnode �򥻳]�w  �b��k�̫�]�w�ܼ� 
		ArrayList<String> mpal = new ArrayList<String>();
		ArrayList<String> mptal = new ArrayList<String>();
				
        for(int i=0; i< this.numbersOfVariable; i++) {
        	mpal.add(this.TV[i].getVariableName());
        	if(this.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(this.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(this.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	
        	//UQPK
        	if(this.TV[i].variable_ispk || this.TV[i].variable_isun) {
        		mpal.add(this.TV[i].getVariableName()+"U");
        		if(this.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(this.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(this.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
		
		//�����ͥ~����`�I�󳻺�
		for(int i=0; i<numbersOfVariable; i++) {
			for(int j=0; j<numbersOfTables; j++) {
				if(schemaTables[j].getTableName().equals(this.TV[i].getForeignTable())) {
					
					//���ƪ��~��������
					if(!this.foreignTableNameTableCLGSkip.contains(this.TV[i].getForeignTable())) {
						//���ͥ~���귽
						tempPath fkp = tempFirstPath(schemaTables, numbersOfTables, schemaTables[j]);
						
						//�s���ϧ�
						this.tableCLG.graphAnd(fkp.tempFkPath);
						
						//�]�w�Ѽ�-startNode
						for(int k=0; k<fkp.foreignColumn.size(); k++) {
							mpal.addAll(fkp.foreignColumn.get(k));
							mptal.addAll(fkp.foreignColumnType.get(k));
						}
						
						//�]�w���ͪ��~����
						this.foreignTableNameTableCLGSkip.addAll(fkp.foreignTableName);
					}
				}
			}
		}
		
		//�O�⭭��ƶq�P�y�z
		ArrayList<ArrayList<String>> constraintInfo = new ArrayList<ArrayList<String>>();
		
		//����r�t 
		//["column",���W��,���s��,��������]
		for(int i=0; i<this.numbersOfVariable; i++) {
			if (this.TV[i].variable_isnn) {
				ArrayList<String> tempInfo = new ArrayList<String>();
				tempInfo.add("column");
				tempInfo.add(this.TV[i].getVariableName());
				tempInfo.add(String.valueOf(i));
				tempInfo.add("nn");
				constraintInfo.add(tempInfo);
				this.numbersOfConstraintSet++;
			}
			if (this.TV[i].variable_ispk) {
				ArrayList<String> tempInfo = new ArrayList<String>();
				tempInfo.add("column");
				tempInfo.add(this.TV[i].getVariableName());
				tempInfo.add(String.valueOf(i));
				tempInfo.add("pk");
				constraintInfo.add(tempInfo);
				this.numbersOfConstraintSet++;
			}
			if (this.TV[i].variable_isun) {
				ArrayList<String> tempInfo = new ArrayList<String>();
				tempInfo.add("column");
				tempInfo.add(this.TV[i].getVariableName());
				tempInfo.add(String.valueOf(i));
				tempInfo.add("un");
				constraintInfo.add(tempInfo);
				this.numbersOfConstraintSet++;
			}
			if (this.TV[i].variable_isfk) {
				ArrayList<String> tempInfo = new ArrayList<String>();
				tempInfo.add("column");
				tempInfo.add(this.TV[i].getVariableName());
				tempInfo.add(String.valueOf(i));
				tempInfo.add("fk");
				constraintInfo.add(tempInfo);
				this.numbersOfConstraintSet++;
			}
		}
		
		//checks
		//["check", chk�s��]
		for(int i=0; i<this.numbersOfCheck; i++) {
			ArrayList<String> tempInfo = new ArrayList<String>();
			tempInfo.add("check");
			tempInfo.add(String.valueOf(i));
			constraintInfo.add(tempInfo);
			this.numbersOfConstraintSet++;
		}
		
		System.out.println("setOfConstraints:" + this.numbersOfConstraintSet);
		System.out.println(constraintInfo);
		
		//�I�s���kø�Ͼ�
		CLGGraph body = recursiveGene(0, constraintInfo);


		//���� �`�M
        tableCLG.graphAnd(body);
        
        //�����]�wstartnode
        //start node should define classname  methodname returntype parameters and parameters' type
        ((CLGStartNode) (this.tableCLG.getStartNode())).setClassName(this.tableName);
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodName(this.tableName);
		((CLGStartNode) (this.tableCLG.getStartNode())).setRetType("String");
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodParameters(mpal);
		((CLGStartNode) (this.tableCLG.getStartNode())).setMethodParameterTypes(mptal);
        
	}

	public CLGGraph recursiveGene(int number, ArrayList<ArrayList<String>> constraintInfo) {
		
		System.out.println("���k " + number);
		System.out.println("�귽 " + constraintInfo.get(number));
		
		//�ھڥثe�B�z������ͭ����޿赲�c �M��I�s�U�@�ӭ���ò��� 
		//�N�U�@�h�����c�s���b��������� �M��ھڷ�e���������M�w�O�_��H�ϸ��|���ͫ���귽
		//���k���覡����
		//�b�̲׼h�����������|���ݥ[�Wsuccess
		
		CLGGraph tempBlock = new CLGGraph();
		CLGGraph fulfillBlock = new CLGGraph();
		CLGGraph violetBlock = new CLGGraph();
		
		//�H��nn
		//�����| �H�ϫ᪺���|�ɥR�� ���������`�I���ݲ��� (fk/check)
		boolean violetNN = false;
		
		String errorMessage = new String();
		
		//���ͺ����P�H�ϸ`�I�귽
		
		if (constraintInfo.get(number).get(0).equals("column")) {
			String columnName = constraintInfo.get(number).get(1);
			int columnNo = Integer.valueOf(constraintInfo.get(number).get(2));
			String constraintType = constraintInfo.get(number).get(3);

			
			switch (constraintType) {
				case "nn":
					
					fulfillBlock = new CLGGraph(this.TV[columnNo].getNnNode());
					violetBlock = new CLGGraph(this.TV[columnNo].getNnNodeNot());
    				errorMessage = new String(columnName + "_nn");
    				violetNN = true;
				
					break;
				
				case "pk":
					
					fulfillBlock = new CLGGraph(this.TV[columnNo].getPkNode());
					violetBlock = new CLGGraph(this.TV[columnNo].getPkNodeNot());
    				errorMessage = new String(columnName + "_pk");
					
					break;
					
				case "un":
					
					fulfillBlock = new CLGGraph(this.TV[columnNo].getUnNode());
					violetBlock = new CLGGraph(this.TV[columnNo].getUnNodeNot());
    				errorMessage = new String(columnName + "_un");
    				
					break;
			
				case "fk":
					
					if(this.TV[columnNo].variable_ispk || this.TV[columnNo].variable_isun) {
						CLGGraph fkA = new CLGGraph(this.TV[columnNo].getFkNode());
						CLGGraph fkB = new CLGGraph(this.TV[columnNo].getFkNode2());
						fkA.graphAnd(fkB);
						fulfillBlock = fkA;
					}
					else {
						fulfillBlock = new CLGGraph(this.TV[columnNo].getFkNode());
					}
					
					violetBlock = new CLGGraph(this.TV[columnNo].getFkNodeNot());
    				errorMessage = new String(columnName + "_fk_" + this.TV[columnNo].getForeignTable() + "_" + this.TV[columnNo].getForeignKey());
					
					break;
			}
			
		}
		else if(constraintInfo.get(number).get(0).equals("check")) {
			int checkNo = Integer.valueOf(constraintInfo.get(number).get(1));
			
			errorMessage = new String("chk_"+(checkNo+1));
			
			if(this.TC[checkNo].getBoundaryType() == 1) {
				fulfillBlock = new CLGGraph(this.TC[checkNo].getCkNode());
				
				CLGGraph cknA = new CLGGraph(this.TC[checkNo].getCkNodeNotBoundaryA());
				CLGGraph cknB = new CLGGraph(this.TC[checkNo].getCkNodeNotBoundaryB());
				cknA.graphOr(cknB);
				violetBlock.graphAnd(cknA);
			}
			else if(this.TC[checkNo].getBoundaryType() == 2) {
				CLGGraph ckA = new CLGGraph(this.TC[checkNo].getCkNodeBoundaryA());
				CLGGraph ckB = new CLGGraph(this.TC[checkNo].getCkNodeBoundaryB());
				ckA.graphOr(ckB);
				fulfillBlock.graphAnd(ckA);
				
				violetBlock = new CLGGraph(this.TC[checkNo].getCkNodeNot());
			}
			else {
				fulfillBlock = new CLGGraph(this.TC[checkNo].getCkNode());
				violetBlock = new CLGGraph(this.TC[checkNo].getCkNodeNot());
			}
			
			if(this.TC[checkNo].ck2Exist) {
				//�G���`�I �u�������϶����� �H�ϰ϶�������
				CLGGraph ck2 = new CLGGraph();
				if(this.TC[checkNo].getBoundaryType2() == 1) {
					ck2 = new CLGGraph(this.TC[checkNo].getCkNode2());
				}
				else if(this.TC[checkNo].getBoundaryType2() == 2) {
					ck2 = new CLGGraph(this.TC[checkNo].getCkNode2BoundaryA());
					CLGGraph ck2B = new CLGGraph(this.TC[checkNo].getCkNode2BoundaryB());
					ck2.graphOr(ck2B);
				}
				else {
					ck2 = new CLGGraph(this.TC[checkNo].getCkNode2());
				}
				
				fulfillBlock.graphAnd(ck2);
			}
			
		}
		
		//���ͺ����`�I�᪺��L����귽 �i�J���k
		
		CLGGraph nextBlock = new CLGGraph();
		
		if(number<this.numbersOfConstraintSet-1) {
			nextBlock = recursiveGene(number+1, constraintInfo);
			fulfillBlock.graphAnd(nextBlock);
		}
		
        //���͹H�ϸ`�I�᪺�����귽
        //�Y�w�g�O�̲׼h �h�������϶����͵����`�I
        if(number == this.numbersOfConstraintSet-1) {
        	CLGVariableNode RL = new CLGVariableNode("result") ;
            CLGOperatorNode RO = new CLGOperatorNode("=");
            CLGLiteralNode RR = new CLGLiteralNode("success", "String") ;
            RO.setLeftOperand(RL);
            RO.setRightOperand(RR);
            CLGGraph RG = new CLGGraph(RO);
            fulfillBlock.graphAnd(RG);
        }
        else {
        	for(int i = number+1; i<this.numbersOfConstraintSet; i++) {
        		CLGGraph constraintFulfillNode = new CLGGraph();
        		if(constraintInfo.get(i).get(0).equals("column")) {
        			String columnName = constraintInfo.get(i).get(1);
        			int columnNo = Integer.valueOf(constraintInfo.get(i).get(2));
        			String constraintType = constraintInfo.get(i).get(3);
        			
        			switch (constraintType) {
    					case "nn":
    						constraintFulfillNode = new CLGGraph(this.TV[columnNo].getNnNode());
    						break;
    				
    					case "pk":
    						constraintFulfillNode = new CLGGraph(this.TV[columnNo].getPkNode());
    						break;
    					
    					case "un":
    						constraintFulfillNode = new CLGGraph(this.TV[columnNo].getUnNode());
    						break;
    			
    					case "fk":
    					
    						if(violetNN && columnName.equals(constraintInfo.get(number).get(1))) {
    							//�H�ϸ`�I��NN
    							//�����X ��������쪺FK
    							//���ͺ����`�I
    						}
    						else {
    							constraintFulfillNode = new CLGGraph(this.TV[columnNo].getFkNode());
    						}
    					
    						break;
        			}
        			
        		}
        		
        		if(constraintInfo.get(i).get(0).equals("check")) {
        			int checkNo = Integer.valueOf(constraintInfo.get(i).get(1));
        			
        			//�H�ϸ`�I��NN
					//�����X ��������즳�ѻP����CK
					//���ͺ����`�I
        			boolean genCkNodes = true;
        			if(violetNN) {
        				for(int j=0; j<this.TC[checkNo].numbersOfCheckVariable; j++) {
        					if(constraintInfo.get(number).get(1).equals(this.TC[checkNo].checkVariables[j])) {
        						genCkNodes = false;
        					}
            			}
        			}
        			
        			
        			if(genCkNodes) {
        				if(this.TC[checkNo].getBoundaryType() == 1) {
            				constraintFulfillNode = new CLGGraph(this.TC[checkNo].getCkNode());
            			}
            			else if(this.TC[checkNo].getBoundaryType() == 2) {
            				CLGGraph ckA = new CLGGraph(this.TC[checkNo].getCkNodeBoundaryA());
            				CLGGraph ckB = new CLGGraph(this.TC[checkNo].getCkNodeBoundaryB());
            				ckA.graphOr(ckB);
            				constraintFulfillNode.graphAnd(ckA);
            			}
            			else {
            				constraintFulfillNode = new CLGGraph(this.TC[checkNo].getCkNode());
            			}
            			
            			if(this.TC[checkNo].ck2Exist) {
            				//�G���`�I �u�������϶����� �H�ϰ϶�������
            				CLGGraph ck2 = new CLGGraph();
            				if(this.TC[checkNo].getBoundaryType2() == 1) {
            					ck2 = new CLGGraph(this.TC[checkNo].getCkNode2());
            				}
            				else if(this.TC[checkNo].getBoundaryType2() == 2) {
            					ck2 = new CLGGraph(this.TC[checkNo].getCkNode2BoundaryA());
            					CLGGraph ck2B = new CLGGraph(this.TC[checkNo].getCkNode2BoundaryB());
            					ck2.graphOr(ck2B);
            				}
            				else {
            					ck2 = new CLGGraph(this.TC[checkNo].getCkNode2());
            				}
            				
            				constraintFulfillNode.graphAnd(ck2);
            			}
        			}
        			
        		}
        		violetBlock.graphAnd(constraintFulfillNode);
      		}
        }
        
        //�H�ϰ϶����͵����`�I
		
    	CLGVariableNode EL = new CLGVariableNode("result") ;
        CLGOperatorNode EO = new CLGOperatorNode("=");
        CLGLiteralNode ER = new CLGLiteralNode(errorMessage, "String") ;
        EO.setLeftOperand(EL);
        EO.setRightOperand(ER);
        CLGGraph EG = new CLGGraph(EO);
        violetBlock.graphAnd(EG);
        
		tempBlock.graphAnd(fulfillBlock);
		tempBlock.graphOr(violetBlock);
		
		return tempBlock;
	}
	
	public tempPath tempFirstPath(schemaTable[] schemaTables, int numbersOfTables, schemaTable fkTable) {
		//�ѼƥΩ󲣥�fkpath
		//Ū��fkTable���ݩʧ�F��s�TfkPath
		
		tempPath fkPath = new tempPath();
		fkPath.tempFkPath = new CLGGraph();
		
		//�~�����~����d��
		
		System.out.println("FKTABLE " + fkTable.tableName);
		
		for(int i=0; i<fkTable.numbersOfVariable; i++) {
			for(int j=0; j<numbersOfTables; j++) {
				//�s�b�~�����p���
				if(schemaTables[j].getTableName().equals(fkTable.TV[i].getForeignTable())) {
					//�Y�w�g�s�b ���[�J�ӥ~��
					if(!fkPath.foreignTableName.contains(fkTable.TV[i].getForeignTable())) {
						tempPath fkp = tempFirstPath(schemaTables, numbersOfTables, schemaTables[j]);
						
						fkPath.tempFkPath.graphAnd(fkp.tempFkPath);
						fkPath.foreignTableName.addAll(fkp.foreignTableName);
						fkPath.foreignColumn.addAll(fkp.foreignColumn);
						fkPath.foreignColumnType.addAll(fkp.foreignColumnType);
					}
				}
			}
		}
		
		//column type �򥻳]�w  �b��k�̫�]�w�ܼ� fk�ɥi��|�l��
		ArrayList<String> mpal = new ArrayList<String>();
        ArrayList<String> mptal = new ArrayList<String>();
        	
        for(int i=0; i<fkTable.numbersOfVariable; i++) {
        	mpal.add(fkTable.TV[i].getVariableName());
        	if(fkTable.TV[i].variable_type==0) {
        		mptal.add("Boolean");
        	}
        	else if(fkTable.TV[i].variable_type==1) {
        		mptal.add("Integer");
        	}
        	else if(fkTable.TV[i].variable_type==2) {
        		mptal.add("String");
        	}
        	else {
        		mptal.add("Integer");
        	}
        	if(fkTable.TV[i].variable_ispk || fkTable.TV[i].variable_isun) {
        		mpal.add(fkTable.TV[i].getVariableName()+"U");
            	if(fkTable.TV[i].variable_type==0) {
            		mptal.add("Boolean");
            	}
            	else if(fkTable.TV[i].variable_type==1) {
            		mptal.add("Integer");
            	}
            	else if(fkTable.TV[i].variable_type==2) {
            		mptal.add("String");
            	}
            	else {
            		mptal.add("Integer");
            	}
        	}
        }
        
        fkPath.foreignTableName.add(fkTable.getTableName());
		fkPath.foreignColumn.add(mpal);
		fkPath.foreignColumnType.add(mptal);
		
		//keywords
		for(int i=0; i<fkTable.numbersOfVariable; i++) {
			System.out.println("execute variable " + fkTable.TV[i].variable_name );
			
			//NN set
			if (fkTable.TV[i].variable_isnn) {			
				CLGGraph nn = new CLGGraph(fkTable.TV[i].getNnNode());
				fkPath.tempFkPath.graphAnd(nn);
			}
				
			//PK set
			if (fkTable.TV[i].variable_ispk) {
				CLGGraph pk = new CLGGraph(fkTable.TV[i].getPkNode());
				fkPath.tempFkPath.graphAnd(pk);
			}
			
			//UQ set
			if (fkTable.TV[i].variable_isun) {
				CLGGraph un = new CLGGraph(fkTable.TV[i].getUnNode());
				fkPath.tempFkPath.graphAnd(un);
			}
			
			//FK set
			if (fkTable.TV[i].variable_isfk) {
				CLGGraph fk = new CLGGraph(fkTable.TV[i].getFkNode());
				CLGGraph fk2 = new CLGGraph(fkTable.TV[i].getFkNode2());
				fkPath.tempFkPath.graphAnd(fk);
				fkPath.tempFkPath.graphAnd(fk2);
			}
		}
		
		//checks
		for(int i=0; i<fkTable.numbersOfCheck; i++) {
			if(fkTable.TC[i].getBoundaryType() == 1) {
				CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode());
				fkPath.tempFkPath.graphAnd(ck);
			}
			else if(fkTable.TC[i].getBoundaryType() == 2) {
				CLGGraph ck1 = new CLGGraph(fkTable.TC[i].getCkNodeBoundaryA());
				CLGGraph ck2 = new CLGGraph(fkTable.TC[i].getCkNodeBoundaryB());
				ck1.graphOr(ck2);
				fkPath.tempFkPath.graphAnd(ck1);
			}
			else {
				CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode());
				fkPath.tempFkPath.graphAnd(ck);
			}
			if(fkTable.TC[i].getCk2Exist()) {
				if(fkTable.TC[i].getBoundaryType2() == 1) {
					CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode2());
					fkPath.tempFkPath.graphAnd(ck);
				}
				else if(fkTable.TC[i].getBoundaryType2() == 2) {
					CLGGraph ck1 = new CLGGraph(fkTable.TC[i].getCkNode2BoundaryA());
					CLGGraph ck2 = new CLGGraph(fkTable.TC[i].getCkNode2BoundaryB());
					ck1.graphOr(ck2);
					fkPath.tempFkPath.graphAnd(ck1);
				}
				else {
					CLGGraph ck = new CLGGraph(fkTable.TC[i].getCkNode2());
					fkPath.tempFkPath.graphAnd(ck);
				}
			}
		}
		
		//�o�̤��� resultNode startnode

		return fkPath;
	}
	
	
	
	public CLGGraph getTableCLG() {
		return this.tableCLG;
	}
		
	public CLGGraph getFirstPath() {
		return this.firstPath;
	}
	
	public void setSymbolTable() {
		
		this.st = new SymbolTable(this.tableName);
		
		MethodToken mt = new MethodToken(this.tableName);
        mt.setReturnType("class ccu.pllab.tcgen.PapyrusCDParser.Variable");
		
		ArrayList<VariableToken> vtl = new ArrayList<VariableToken>();
		
		//�qclggraph startnode�h��symboltable
		ArrayList<String> mpal = (((CLGStartNode) (this.tableCLG.getStartNode())).getMethodParameters());
		ArrayList<String> mptal = (((CLGStartNode) (this.tableCLG.getStartNode())).getMethodParameterTypes());
		
		System.out.println("mpal: " + mpal);
		System.out.println(mptal);
		
		for(int i=0; i<mpal.size(); i++) {
			
			if(mptal.get(i).equals("Boolean")) {
				BooleanType bt = new BooleanType();
		        VariableToken vt = new VariableToken(mpal.get(i), bt);
		        vtl.add(vt);
		        mt.addArgument(vt);
		        this.st.addArgumentMap(vt);
		        System.out.println("symboltable add boolean " + mpal.get(i));
			}
			else if(mptal.get(i).equals("Integer")) {
				IntType it = new IntType();
				VariableToken vt = new VariableToken(mpal.get(i), it);
				vtl.add(vt);
				mt.addArgument(vt);
				this.st.addArgumentMap(vt);
				System.out.println("symboltable add integer " +mpal.get(i));
			}
			else if(mptal.get(i).equals("String")) {
				StringType st = new StringType();
				VariableToken vt = new VariableToken(mpal.get(i), st);
				vtl.add(vt);
				mt.addArgument(vt);	
				System.out.println("symboltable add string " +mpal.get(i));
				//st.addArgumentMap(vt); ?���䴩StringType
			}
			else {
				IntType it = new IntType();
				VariableToken vt = new VariableToken(mpal.get(i), it);
				vtl.add(vt);
				mt.addArgument(vt);
				this.st.addArgumentMap(vt);
				System.out.println("symboltable add integer else " +mpal.get(i));
			}
		}
		
		
		this.st.addArgument(vtl);
		this.st.addMethod(mt);
	}
	
	public SymbolTable getSymbolTable() {
		return this.st;
	}
	
	public boolean hasForeignKey() {
		for(int i=0; i<numbersOfVariable; i++) {
			if (TV[i].variable_isfk) {
				return true;
			}
		}
		return false;
	}
	
	public void printFirstPathStartNodeElements() {
		
		ArrayList<String> mpal = (((CLGStartNode) (this.firstPath.getStartNode())).getMethodParameters());
		ArrayList<String> mptal = (((CLGStartNode) (this.firstPath.getStartNode())).getMethodParameterTypes());
		
		System.out.println("MPAL: " + mpal);
		System.out.println("MPTAL: " + mptal);
	}
}