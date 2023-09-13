package ccu.pllab.tcgen.AbstractSyntaxTree;

import java.util.ArrayList;

import ccu.pllab.tcgen.AbstractConstraint.CLGClassNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGLetNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGMethodInvocationNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.AbstractType.BasicTypeMethodCall;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.RealType;
import ccu.pllab.tcgen.AbstractType.SequenceType;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;

public class MethodCallExp extends PropertyCallExp {
	private ArrayList<AbstractSyntaxTreeNode> parameters;
	private VariableType return_type = new VoidType();

	public MethodCallExp(String variable) {
		super(variable);
		parameters = new ArrayList<AbstractSyntaxTreeNode>();
		return_type = new VoidType();
	}

	public MethodCallExp(String variable, AbstractSyntaxTreeNode source) {
		super(variable, source);
		parameters = new ArrayList<AbstractSyntaxTreeNode>();
		return_type = new VoidType();

	}

	public MethodCallExp(PropertyCallExp propertyCall) {
		super(propertyCall);
		if (propertyCall instanceof VariableRefExp) {
			parameters = new ArrayList<AbstractSyntaxTreeNode>();
		} else {
			parameters = ((MethodCallExp) propertyCall).getParameters();
		}
		return_type = new VoidType();
	}

	public void setParameters(ArrayList<AbstractSyntaxTreeNode> parameters) {
		if (parameters == null)
			this.parameters = new ArrayList<AbstractSyntaxTreeNode>();
		else
			this.parameters = parameters;
	}

	public void addParameters(AbstractSyntaxTreeNode... parameters) {
		if (this.parameters == null)
			this.parameters = new ArrayList<AbstractSyntaxTreeNode>();
		for (AbstractSyntaxTreeNode parameter : parameters)
			this.parameters.add(parameter);
	}

	public ArrayList<AbstractSyntaxTreeNode> getParameters() {
		return this.parameters;
	}

	// 如果是使用者定義型態的 Method
	public void setReturnType(VariableType returnType) {
		this.return_type = returnType;
	}

	// 如果是基本型態的 Method
	public void setReturnType(VariableType sourceType, TypeTable typeTable) {

		MethodToken method = null;

		if (sourceType == null || sourceType instanceof VoidType) // Int.mod(),Int.div()
			method = typeTable.getBaseTypemethodCallSymbolTable(new IntType()).getMethod().get(this.getVariable());
		else
			method = typeTable.getBaseTypemethodCallSymbolTable(sourceType).getMethod().get(this.getVariable());

		if (method != null) {

			if (sourceType instanceof SequenceType) {
				if (method.getReturnType() == null) {
					// Sequence->at(i) 看Sequence 存的是怎樣的型態
					this.return_type = ((SequenceType) sourceType).getElement();
				} else {
					// 明確定義回傳的type
					this.return_type = method.getReturnType();
				}

			} else {
				this.return_type = method.getReturnType();
			}
		} else {
			this.return_type = new VoidType();
		}
	}

	public VariableType getReturnType() {
		return this.return_type;
	}

	@Override
	public void addVariableType(TypeTable typeTable, String methodName) {
		if (this.getVariable().equals("self"))
			this.setType(typeTable.get(methodName, null));
		else {
			SymbolTable theSymbolTable = null;
			// MethodToken method=symbolTable.getMethod().get(this.getVariable());
			MethodToken method = null;
			if (this.getSourceExp() != null) {

				if (this.getSourceExp() instanceof MethodCallExp) {
					VariableType theobjectType = ((MethodCallExp) this.getSourceExp()).getReturnType();
					theSymbolTable = theobjectType.getSymbolTable();
				} else {
					VariableType theobjectType = ((PropertyCallExp) this.getSourceExp()).getType();
					theSymbolTable = theobjectType.getSymbolTable();
				}

				method = theSymbolTable.getMethod().get(this.getVariable());

			} else { // methodName ?? constructor 嗎
				theSymbolTable = typeTable.get(methodName, null).getSymbolTable();
				method = theSymbolTable.getMethod().get(this.getVariable());
			}
			if (method != null) {
				this.setType(Main.typeTable.get(theSymbolTable.getClassName(), null));
				this.return_type = method.getReturnType();
			}
		}
	}

	@Override
	public String ASTInformation() {
		String astInformation = this.getVariable();
		if (this.getTimeExpression())
			astInformation += "@pre";
		if (this.getQualifier() != null && this.getQualifier().size() > 0)
			astInformation += this.getQualifier().toString();
		if (this.parameters != null && this.parameters.size() > 0)
			astInformation += "()";
		return "\"" + "(" + this.getID() + ")" + astInformation + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		// methodCall暫未處理陣列索引值
		String name = this.getVariable();
		CLGConstraint constraint = null;

		// 20210216將參數由AST點用AST2CLG轉成CLGConstraint在傳入methodInvocationNode
		ArrayList<CLGConstraint> parameters2CLG = new ArrayList<>();
		for (AbstractSyntaxTreeNode parameter : this.parameters) {
			parameters2CLG.add(parameter.AST2CLG(sym, methodName, isCondition));
		}

		if (this.getSourceExp() != null) {
			CLGConstraint object = this.getSourceExp().AST2CLG(sym, methodName, isCondition);

			VariableType objectType = null;

			if (object instanceof CLGOperatorNode) {
				objectType = ((CLGOperatorNode) object).getType();
			} else if (object instanceof CLGLetNode) {
				System.out.println("CLGLetNode");
			} else {
				objectType = ((CLGVariableNode) object).getType();
			}

			constraint = new CLGMethodInvocationNode(object, objectType, this.getVariable(), parameters2CLG,
					this.return_type);
			((CLGMethodInvocationNode) constraint).setIsNewObj(this.isNewObj());
		} else {

			constraint = new CLGMethodInvocationNode(new CLGObjectNode("null", this.getType()), this.getType(),
					this.getVariable(), parameters2CLG, this.return_type);
			((CLGMethodInvocationNode) constraint).setIsNewObj(this.isNewObj());
		}
		return constraint;
	}

	@Override
	public void toGraphViz() {
		int size = 0;
		if (this.parameters != null && this.parameters.size() >= 0)
			size = this.parameters.size();
		for (int parameter = 0; parameter < size; parameter++) {
			System.out.println(this.ASTInformation() + "->" + this.parameters.get(parameter).ASTInformation());
			this.parameters.get(parameter).toGraphViz();
		}
	}

	public PropertyCallExp clone() {
		MethodCallExp property = new MethodCallExp(this.getVariable(), this.getSourceExp());
		property.setParameters(this.parameters);
		property.setQualifier((ArrayList<AbstractSyntaxTreeNode>) this.getQualifier().clone());
		return property;
	}

	@Override
	public void preconditionAddPre() {
		if (this.getQualifier() == null && this.getParameters() == null)
			this.setTimeExpression();
	}

	@Override
	public void postconditionAddPre() {
		if (this.getQualifier() == null && this.getParameters() == null)
			this.setTimeExpression();
		;
	}

	public AbstractSyntaxTreeNode ASTclone() {
		MethodCallExp methodCallExp = new MethodCallExp(this.getVariable(), this.getSourceExp());
		methodCallExp.setType(this.getType());
		methodCallExp.setParameters(this.parameters);
		methodCallExp.setQualifier((ArrayList<AbstractSyntaxTreeNode>) this.getQualifier().clone());
		if (this.getTimeExpression())
			methodCallExp.setTimeExpression();
		return methodCallExp;
	}

}
