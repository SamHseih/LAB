package ccu.pllab.tcgen.AbstractType;

public abstract class VariableType { //型態類別的抽象類別，其餘型態類別皆繼承於它
	String typeName;
	String typeID;
	
	public VariableType() {}
	public abstract String toString();
	public abstract String genDomainCLP(String obj);
}
