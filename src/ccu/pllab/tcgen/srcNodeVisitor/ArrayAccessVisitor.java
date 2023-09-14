package ccu.pllab.tcgen.srcNodeVisitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Expression;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;

public class ArrayAccessVisitor extends JAVA2CLG implements SrcNodeVisit {
	CLGConstraint constraint;

	/***********************************************************/

	public boolean visit(ArrayAccess node) {
		ArrayList<CLGConstraint> indexList = new ArrayList<>();
		Expression arrVar = node;
		// �]arrayAccess�O���j���c�A�G�ϥ�while�@�h�h���X���ޭ�
		while (arrVar instanceof ArrayAccess) {
			indexList.add(BasicExpVisitor.VisitorAssign(((ArrayAccess) arrVar).getIndex()));
			arrVar = ((ArrayAccess) arrVar).getArray();
		}
		// �̤��h�|�O�}�C�ܼƪ���
		CLGObjectNode arrConstraint = (CLGObjectNode) BasicExpVisitor.VisitorAssign(arrVar);
		arrConstraint.setQualifier(indexList);

		constraint = arrConstraint;
		return false;
	}

	/**********************************************************/
	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLGConstraint getConstraint() {
		// TODO Auto-generated method stub
		return constraint;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
