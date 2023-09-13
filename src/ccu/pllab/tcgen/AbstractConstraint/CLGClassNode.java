package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

//���O�غc�l�A�qClassCallExp�� AST2CLG�Ӫ�
// new Date.Date() ��Date�A��ܨϥάY�� Class ��Constructor
public class CLGClassNode extends CLGVariableNode {

	public CLGClassNode() {
		super();
	}

	public CLGClassNode(String name) {
		super(name);
	}

	public CLGClassNode(String name, VariableType type) {
		super(name, type);
	}

	public CLGClassNode(String name, VariableType type, CLGConstraint constraint) {
		super(name, type, constraint);
	}

	public CLGClassNode(CLGClassNode node) {
		super(node.getName(), node.getType(), node.getConstraint());
	}

	@Override
	public CLGConstraint clone() {

		CLGConstraint newConstrain = null;
		if (this.getConstraint() != null) {
			newConstrain = this.getConstraint().clone();
		}

		CLGConstraint cons = new CLGClassNode(this);
		((CLGClassNode) cons).setConstraint(newConstrain);
		cons.setCloneId(this.getConstraintId());
		return cons;

	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		String new_name = this.getName();
		if (this.getConstraint() == null) {
			return new CLPInfo(Utility.titleToUppercase(new_name), new ArrayList<String>());
		} else if (new_name.equals("")) { // �����D�b������
			return this.getConstraint().getCLPInfo(variableSet, containKey);
		} else if (!(this.getConstraint() instanceof CLGOperatorNode)) { // �i��n��A�i�dQQ
			// �غc�l�ɡA�^�ǭȬ�obj_post�A�G��classNode CLP�^�ǭȰ��ץ�
			if (this.getConstraint() instanceof CLGMethodInvocationNode)
				if (((CLGMethodInvocationNode) this.getConstraint()).isNewObj())
					return this.getConstraint().getCLPInfo(variableSet, containKey);
				else {
					return new CLPInfo(
							this.getConstraint().getCLPInfo(variableSet, containKey).getReturnCLP() + "_" + new_name,
							new ArrayList<String>());
				}
		}

		return new CLPInfo(new_name, new ArrayList<String>());

	}

}
