package ccu.pllab.tcgen.AbstractType;

public abstract class VariableType { //���A���O����H���O�A��l���A���O���~�ө�
	String typeName;
	String typeID;
	
	public VariableType() {}
	public abstract String toString();
	public abstract String genDomainCLP(String obj);
}
