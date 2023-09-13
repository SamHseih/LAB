package ccu.pllab.tcgen.AbstractType;

public class SequenceType extends CollectionType {

	public SequenceType() {
		this.typeID = "Sequence()";
		this.typeName = "Sequence()";
		labelingPriority = 3;
	}

	public SequenceType(VariableType obj) {
		element = obj;
		this.typeID = "Sequence(" + element.getType() + ")";
		this.typeName = "Sequence(" + element.getType() + ")";
		labelingPriority = 3;
	}

	@Override
	public String getType() {
		return "Sequence(" + element.toString() + ")";
	}

	@Override
	public String toString() {
		return "Sequence(" + element.toString() + ")";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SequenceType clone() {
		SequenceType cloneObj = new SequenceType(this.element.clone());
		return cloneObj;
	}

}
