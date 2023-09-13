package ccu.pllab.tcgen.AbstractSyntaxTree;

import java.util.ArrayList;
import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.exe.main.Main;

public abstract class PropertyCallExp extends AbstractSyntaxTreeNode {
//result.hour or self.hour
	private VariableType type = new VoidType();
	private AbstractSyntaxTreeNode sourceExp;
	// private VariableType var_type;
	private String variable;// pathname
	private boolean timeExpression = false;
	private ArrayList<AbstractSyntaxTreeNode> qualifier;
	private int scopeId;
	private boolean isNewObj = false;
	private boolean isQualifiedName = false;// 判斷是qualified就不自動加self

	public PropertyCallExp(String variable) {
		super();
		this.variable = variable;
		this.sourceExp = null;
		if (this.variable.equals("mod") || this.variable.equals("div") || this.variable.equals("gcd"))
			this.type = new IntType();
		qualifier = new ArrayList<>();
	}

	public PropertyCallExp(String variable, AbstractSyntaxTreeNode source) {
		super();
		this.variable = variable;
		this.sourceExp = source;
		if (this.variable.equals("mod") || this.variable.equals("div") || this.variable.equals("gcd"))
			this.type = new IntType();
		qualifier = new ArrayList<>();
	}

	public PropertyCallExp(PropertyCallExp property) {
		super();
		this.variable = property.variable;
		if (this.variable.equals("mod") || this.variable.equals("div") || this.variable.equals("gcd"))
			this.type = new IntType();
		this.type = property.type;
		this.qualifier = property.getQualifier();
		this.sourceExp = property.getSourceExp();
		this.isNewObj = property.isNewObj();
	}

	public void setIsNewObj(boolean b) {
		this.isNewObj = b;
	}

	public int getScopeId() {
		return scopeId;
	}

	public void setScopeId(int scopeId) {
		this.scopeId = scopeId;
	}

	public boolean isNewObj() {
		return this.isNewObj;
	}

	public void setType(VariableType type) {
		this.type = type;
	}

	// 分析OCL 規格，parser 出來是 String
	public void setType(String type) {
		this.type = Main.typeTable.get(type, null);
	}

	public VariableType getType() {
		return this.type;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getVariable() {
		return this.variable;
	}

	public void setSourceExp(AbstractSyntaxTreeNode source) {
		this.sourceExp = source;
	}

	public AbstractSyntaxTreeNode getSourceExp() {
		return this.sourceExp;
	}

	public void setTimeExpression() {
		this.timeExpression = true;
	}

	public void setTimeExpression(boolean b) {
		this.timeExpression = b;
	}

	public boolean getTimeExpression() {
		return this.timeExpression;
	}

	public void addQualifier(AbstractSyntaxTreeNode node) {
		this.qualifier.add(node);
	}

	public ArrayList<AbstractSyntaxTreeNode> getQualifier() {
		return this.qualifier;
	}

	public void setQualifier(ArrayList<AbstractSyntaxTreeNode> nodeList) {
		for (AbstractSyntaxTreeNode n : nodeList) {
			this.qualifier.add(n.ASTclone());
		}
	}

	public boolean isQualifiedName() {
		return isQualifiedName;
	}

	@Override
	public void setIsQualifiedName(boolean isQualifiedName) {
		this.isQualifiedName = isQualifiedName;
	}

//	@Override
//	public void addVariableType(SymbolTable symbolTable,String methodName)
//	{
//		if(this.variable.equals("self"))
//			this.type=symbolTable.getClassName();
//		else
//		{
//			if(this.parameters!=null)
//			{
//				//HashMap<String, String> method=symbolTable.getMethodReturn();
//				//ArrayList<MethodToken> method=symbolTable.getMethod();
//				MethodToken method=symbolTable.getMethod().get(this.variable);
//				
////				for(MethodToken token:method)
////				{
////					if(token.getMethodName().equals(this.variable))
////					{
////						this.type=token.getReturnType();
////						break;
////					}
////				}
//				
//				if(method != null ) {
//					this.type=method.getReturnType();
//				}
//			}
//			else{//如果是屬性的token或是參數的token
////				ArrayList<VariableToken> attribute=symbolTable.getAttribute();
////				ArrayList<MethodToken> method=symbolTable.getMethod();
//				
//				LinkedHashMap<String, VariableToken> attribute=symbolTable.getAttribute();
//				LinkedHashMap<String, MethodToken> methodmap=symbolTable.getMethod();
//
//				//2021-01-31 dai
//				if(symbolTable.getAttribute().get(this.variable) != null) {
//					this.type= symbolTable.getAttribute().get(this.variable).getType().getType();
//				}else {
//					if(symbolTable.getMethod().containsKey(methodName)) {
//						MethodToken method = symbolTable.getMethod().get(methodName);
//						if(method.getArgument().containsKey(this.variable)) {
//							this.type = method.getArgument().get(this.variable).getType().getType();
//						}
//					}
//					
//				}
//				
//				
////				if(attribute!=null && attribute.contains(this.variable))
////				{
////					for(VariableToken variable:attribute)
////						if(variable.getVariableName().equals(this.variable))
////						{
////							this.type=variable.getType().toString();
////						}
////				}
////				else
////				{
////					for(MethodToken token:method)
////					{
////						for(VariableToken variable:token.getArgument())
////						{
////							if(variable.getVariableName().equals(this.variable))
////								this.type=variable.getType().toString();
////						}
////					}
////				}
//				/*HashMap<String, String> attribute=symbolTable.getAttribute();
//				HashMap<String, String> arg=symbolTable.getMethodArg(methodName);
//				if(attribute!=null && attribute.containsKey(this.variable))
//				{
//					this.type=attribute.get(this.variable);
//				}
//				else if(arg!=null && arg.containsKey(this.variable))
//				{
//					this.type=arg.get(this.variable);
//				}*/
//			}
//		}
//	}

	@Override
	public void changeAssignToEqual() {
	}

	@Override
	public void conditionChangeAssignToEqual() {
	}

	@Override
	// 20210302修改qualifier，但不知此函式用處，若有錯麻煩修正輸輸格式(逗號之類的
	public String childNodeInfo() {
		String info = this.variable;
		if (this.qualifier != null)
			for (AbstractSyntaxTreeNode qualifierNode : this.getQualifier())
				info += qualifierNode.childNodeInfo() + ", ";
		Utility.delEndRedundantSymbol(info, ",");
		return info;
	}

//	@Override
//	public String ASTInformation()
//	{
//		String astInformation=variable;
//		if(this.timeExpression)
//			astInformation+="@pre";
//		if(this.qualifier!=null && this.qualifier.size()>0)
//		 astInformation+="["+this.qualifier.get(0).NodeToString()+"]";
//		if(this.qualifier2!=null && this.qualifier2.size()>0)
//			 astInformation+="["+this.qualifier2.get(0).NodeToString()+"]";
//		if(this.parameters!=null&&this.parameters.size()>0)
//			astInformation+="()";
//		return "\""+"("+this.getID()+")"+astInformation+"\"";
//	}

//	@Override
//	public  CLGConstraint AST2CLG()
//	{
//		String name=this.variable;
//		CLGConstraint constraint=null;
//		if(this.parameters!=null)
//		{
//			ArrayList<String> argument=new ArrayList<String>();
//			for(int parameter=0;parameter<this.parameters.size();parameter++)
//				argument.add(this.parameters.get(parameter).NodeToString());
//			if( name.contains("::") ) {
//				int one=name.indexOf(':');
//				String classObjName= name.substring(0,one);
//				String methodname=name.substring(one+2);
//				constraint=new CLGMethodInvocationNode(classObjName, methodname,argument);
//			}
//			else constraint=new CLGMethodInvocationNode(Main.className,name,argument);
//		}
//		else
//		{
//			
//			if(this.timeExpression)
//					name+="_pre";//
//			if(this.qualifier!=null)
//			{
//				name+="["+this.qualifier.get(0).NodeToString()+"]";
//				this.type="Set";
//			}
//			if(this.qualifier2!=null)
//			{
//				name+="["+this.qualifier2.get(0).NodeToString()+"]";
//				this.type="Set";
//			}
//			if(name.contains("@"))
//			{
//			name=name.replaceAll("@", "");
//			name=name.replaceAll("->size", "size_pre");
//			}
//			name=name.replaceAll("sizepre", "size_pre"); //2019/05/22
//			constraint=new CLGVariableNode(name,this.type);
//			
//		}
//		return constraint;
//	}

//	@Override
//	public  CLGConstraint AST2CLG(boolean boundaryAnalysis)
//	{
//		String name=this.variable;
//		CLGConstraint constraint=null;
//		if(this.parameters!=null)
//		{
//			ArrayList<String> argument=new ArrayList<String>();
//			for(int parameter=0;parameter<this.parameters.size();parameter++)
//				argument.add(this.parameters.get(parameter).NodeToString());
//			
//			constraint=new CLGMethodInvocationNode(Main.className,name,argument);
//		}
//		else
//		{
//			
//			if(this.timeExpression)
//					name+="_pre";//
//			if(this.qualifier!=null)
//			{
//				name+="["+this.qualifier.get(0).childNodeInfo()+"]";
//				this.type="Set";
//			}
//			constraint=new CLGVariableNode(name,this.type);
//			
//		}
//		return constraint;
//	}

//	@Override
//	public String NodeToString() {
//		String name = this.variable;
//		if (this.qualifier != null) {
//			name += "[";
//			for (AbstractSyntaxTreeNode qualifierNode : this.getQualifier())
//				name += qualifierNode.NodeToString() + "][";
//			Utility.delEndRedundantSymbol(name, "[");
//		}
//		if (this.timeExpression)
//			name += "@pre";
//
//		return name;
//	}

//	@Override
//	public void toGraphViz()
//	{
//		int size=0;
//		if(this.parameters!=null&&this.parameters.size()>=0)
//		size=this.parameters.size();
//		for(int parameter=0;parameter<size;parameter++)
//		{
//			System.out.println(this.ASTInformation()+"->"+this.parameters.get(parameter).ASTInformation());
//			this.parameters.get(parameter).toGraphViz();
//		}
//	}

//	public PropertyCallExp clone()
//	{
//		PropertyCallExp property=new PropertyCallExp(this.variable);
//		property.setParameters(this.parameters);
//		return property;
//	}
	@Override
	public String AST2CLP(String attribute, String argument) {
		String obj_pre = "", arg_pre = "";
		if (attribute.length() > 0)
			obj_pre = attribute.replaceAll(",", "_pre,") + "_pre";
		if (argument.length() > 0)
			arg_pre = argument.replaceAll(",", "_pre,") + "_pre";
		String clp = "variable_" + this.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,Result):-\n" + "Result="
				+ this.variable.toUpperCase().charAt(0);
		if (this.variable.length() > 1)
			clp += this.variable.substring(1);
		if (this.timeExpression)
			clp += "_pre";
		return clp + ".\n\n";
	}

	@Override
	public String demonganAST2CLP(String attribute, String argument) {
		return "";
	}

//	@Override
//	public  void preconditionAddPre() {
//		if(this.qualifier==null && this.parameters==null)
//		this.timeExpression=true;
//	}
//	@Override
//	public  void postconditionAddPre() {
//		if(this.qualifier==null && this.parameters==null)
//		this.timeExpression=true;
//	}
	@Override
	public void demoganOperator() {
	}

	@Override
	public ASTGraphNode AST2ASTGraph() {
		return null;
	}

//	@Override
//	public AbstractSyntaxTreeNode ASTclone()
//	{
//		PropertyCallExp propertyCallExp=new PropertyCallExp(this.variable);
//		propertyCallExp.setType(this.type);
//		propertyCallExp.setParameters(this.parameters);
//		propertyCallExp.setQualifier(this.qualifier);
//		if(this.timeExpression)
//		propertyCallExp.setTimeExpression();
//		return propertyCallExp;
//	}
	public void findFromLocalVariableTable(ArrayList<SymbolTable> localVariableTable) {

		for (int i = 0; i < localVariableTable.size(); i++) {

			SymbolTable symbolTable = localVariableTable.get(i);

			if (symbolTable.getClassName().equals("")) {

				VariableRefExp var = symbolTable.getSomeOneLocalVariable(this.variable);
				if (var != null && var.getVariable().equals(this.getVariable())) {
					this.setType(var.getType());
					return;
				}
			} else { // class symbolTable
				VariableToken var = symbolTable.getSomeOneAttrible(this.variable);
				if (var != null && var.getVariableName().equals(this.getVariable())) {
					this.setType(var.getType());
					return;
				}

			}

		}
	}

	public void addVariableType(TypeTable typeTable) {

		if (this.type instanceof VoidType) {
			VariableType objectVariableType = new VoidType();
			int arrayDim = 1;
			if (this.getSourceExp() == null) {
				objectVariableType = Main.typeTable.get(Main.className, null);
			} else if (this.getSourceExp() instanceof VariableRefExp || this.getSourceExp() instanceof ClassCallExp) {

				if (((VariableRefExp) this.getSourceExp()).getVariable().equals("self")) {
					objectVariableType = Main.typeTable.get(Main.className, null);
				} else if (((PropertyCallExp) this.getSourceExp()).getQualifier().size() > 1) {
					objectVariableType = ((ArrayType) ((VariableRefExp) this.getSourceExp()).getType())
							.getNDimType(((PropertyCallExp) this.getSourceExp()).getQualifier().size());
				} else {
					objectVariableType = ((VariableRefExp) this.getSourceExp()).getType();
				}
			} else if (this.getSourceExp() instanceof OperatorExp) {
				objectVariableType = ((OperatorExp) this.getSourceExp()).getType();

			} else if (this.getSourceExp() instanceof MethodCallExp) {
				objectVariableType = ((MethodCallExp) this.getSourceExp()).getReturnType();
			}

			if (this.getVariable().equals("self")) {
				this.setType(objectVariableType);
				return;
			} else if (!this.getVariable().equals("result")) {
				if (objectVariableType instanceof UserDefinedType) {
					if (objectVariableType.getSymbolTable().getAttribute().containsKey(this.getVariable())) {
						this.setType(
								objectVariableType.getSymbolTable().getAttribute().get(this.getVariable()).getType());
					} else if (objectVariableType.getSymbolTable().getMethod().containsKey(this.getVariable()))
						this.setType(objectVariableType);
				} else {
					this.setType(objectVariableType);
				}
			}
		}
	}
}
