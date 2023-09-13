package ccu.pllab.tcgen.AbstractSyntaxTree;

import java.util.LinkedHashMap;

import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;

public class VariableRefExp extends PropertyCallExp {

	public VariableRefExp(String variable) {
		super(variable);
		super.setScopeId(-1);
	}

	public VariableRefExp(String variable, int scopeId) {
		super(variable);
		super.setScopeId(scopeId);
	}

	public VariableRefExp(String variable, AbstractSyntaxTreeNode source) {
		super(variable, source);
		super.setScopeId(-1);// not local variable

	}

	public VariableRefExp(String variable, VariableType type, int scopeId) {
		super(variable);
		super.setType(type);
		super.setScopeId(scopeId);// not local variable
	}

	public VariableRefExp(String variable, AbstractSyntaxTreeNode source, int scopeId) {
		super(variable, source);
		super.setScopeId(scopeId);// not local variable
	}

	@Override
	public void addVariableType(TypeTable typeTable, String methodName) {
		if (this.getVariable().equals("self"))
			this.setType(typeTable.get(methodName, null));
		else {

			SymbolTable theSymbolTable = null;

//			LinkedHashMap<String, VariableToken> attribute=symbolTable.getAttribute();
//			LinkedHashMap<String, MethodToken> methodmap=symbolTable.getMethod();

			LinkedHashMap<String, VariableToken> attribute = null;
			LinkedHashMap<String, MethodToken> methodmap = null;

			if (this.getSourceExp() != null) {

				if (this.getSourceExp() instanceof MethodCallExp) {
					VariableType theobjectType = ((MethodCallExp) this.getSourceExp()).getReturnType();
					theSymbolTable = theobjectType.getSymbolTable();
				} else {
					VariableType theobjectType = ((PropertyCallExp) this.getSourceExp()).getType();
					theSymbolTable = theobjectType.getSymbolTable();
				}

			} else {
				theSymbolTable = typeTable.get(methodName, null).getSymbolTable();
			}

			// 2021-01-31 dai
			if (theSymbolTable.getAttribute().get(this.getVariable()) != null) {
				this.setType(theSymbolTable.getAttribute().get(this.getVariable()).getType());
			} else {
				if (theSymbolTable.getMethod().containsKey(methodName)) {
					MethodToken method = theSymbolTable.getMethod().get(methodName);
					if (method.getArgument().containsKey(this.getVariable())) {
						this.setType(method.getArgument().get(this.getVariable()).getType());
					}
				}

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
		return "\"" + "(" + this.getID() + ")" + astInformation + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		String name = this.getVariable();
		CLGConstraint constraint = null;

		// 如果是屬性，會在最後跟self一起加
		if (methodName == "" || !Utility.isAtt(this.getVariable(), sym, methodName)) {
			if (this.getTimeExpression())
				name += "_pre";//
		}

		if (name.contains("@")) {
			name = name.replaceAll("@", "");
			name = name.replaceAll("->size", "size_pre");
		}
		name = name.replaceAll("sizepre", "size_pre"); // 2019/05/22

		constraint = new CLGObjectNode(name, this.getType(), getScopeId());

		if (this.getQualifier() != null) {
			// 如果有索引值，則型態不是陣列型態
			VariableType eleType = this.getType();
			while (eleType instanceof ArrayType)
				eleType = ((ArrayType) eleType).getElement();
			constraint = new CLGObjectNode(name, eleType, this.getScopeId());

			for (AbstractSyntaxTreeNode qualifierNode : this.getQualifier()) {
				((CLGObjectNode) constraint).addQualifier(qualifierNode.AST2CLG(sym, methodName, isCondition));
			}
		} else
			constraint = new CLGObjectNode(name, this.getType(), this.getScopeId());

		// 如果是屬性(非參數)，則會在CLP時加self
		// methodName為空白時(inv)不加self
		if (/* methodName!="" && */!this.isQualifiedName() && Utility.isAtt(this.getVariable(), sym, methodName)) {
			((CLGObjectNode) constraint).setSelf(this.getTimeExpression());
		}

		return constraint;
	}

	@Override
	public void toGraphViz() {
	}

	public PropertyCallExp clone() {
		VariableRefExp variable = new VariableRefExp(this.getVariable(), this.getSourceExp());
		for (AbstractSyntaxTreeNode n : this.getQualifier())
			variable.addQualifier(n.ASTclone());
		return variable;
	}

	@Override
	public void preconditionAddPre() {
		if (this.getQualifier() == null || this.getQualifier().isEmpty())
			this.setTimeExpression();
	}

	@Override
	public void postconditionAddPre() {
		if (this.getQualifier() == null)
			this.setTimeExpression();
	}

	public AbstractSyntaxTreeNode ASTclone() {
		VariableRefExp variableRefExp = new VariableRefExp(this.getVariable(), this.getSourceExp());
		variableRefExp.setType(this.getType());
		for (AbstractSyntaxTreeNode n : this.getQualifier())
			variableRefExp.addQualifier(n.ASTclone());
		if (this.getTimeExpression())
			variableRefExp.setTimeExpression();
		return variableRefExp;
	}
}
