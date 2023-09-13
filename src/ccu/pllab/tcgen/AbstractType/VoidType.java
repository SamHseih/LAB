package ccu.pllab.tcgen.AbstractType;

public class VoidType extends VariableType {

	public VoidType() {
		super.typeID = "void";
		super.typeName = "void";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "void";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "void";
	}

	@Override
	public VoidType clone() {
		VoidType cloneObj = new VoidType();
		return cloneObj;
	}

	@Override
	public String getTransformTypeCLP() {
		// TODO Auto-generated method stub
		return "";
	}

}
