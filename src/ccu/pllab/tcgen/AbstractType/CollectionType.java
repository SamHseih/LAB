package ccu.pllab.tcgen.AbstractType;

public class CollectionType extends VariableType {
	VariableType element;

	public CollectionType() {
		this.typeID = "Collection()";
		this.typeName = "Collection()";
		labelingPriority = 3;
	}

	public CollectionType(VariableType obj) {
		element = obj;
		this.typeID = "Collection(" + element.typeName + ")";
		this.typeName = "Collection(" + element.typeName + ")";
		labelingPriority = 3;
	}

	public void setElement(VariableType obj) {
		// TODO Auto-generated method stub
		this.element = obj;
	}

	public void setTypeNameAndID(String typestr) {
		// TODO Auto-generated method stub
		this.typeID = typestr;
		this.typeName = typestr;
	}

	public VariableType getElement() {
		// TODO Auto-generated method stub
		return element;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Collection(" + element.typeName + ")";
	}

	@Override
	public String toString() {
		return "Collection(" + element.toString() + ")";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	// 學長的
	@Override
	public CollectionType clone() {
		CollectionType cloneObj = new CollectionType(this.element.clone());
		return cloneObj;
	}

	@Override
	public String getTransformTypeCLP() {
		// 待改
		return "";
	}

}
