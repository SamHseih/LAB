package ccu.pllab.tcgen.AbstractType;

public class ArrayListType extends VariableType {

	VariableType element;

	public ArrayListType(VariableType obj) {
		element = obj;
		this.typeID = "ArrayList<" + element.typeName + ">";
		this.typeName = "ArrayList<" + element.typeName + ">";
		labelingPriority = 3;

	}

	public ArrayListType() {
		// TODO Auto-generated constructor stub
		this.typeID = "ArrayList";
		labelingPriority = 3;
	}

	public VariableType getElement() {
		// TODO Auto-generated method stub
		return element;
	}

	public String getSize() {
		return "x";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ArrayList<" + element.toString() + ">";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "ArrayList<" + element.toString() + ">";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayListType clone() {
		ArrayListType cloneObj = new ArrayListType(this.element.clone());
		return cloneObj;
	}

	@Override
	public String getTransformTypeCLP() {
		// л▌зя
		return "";
	}

}
