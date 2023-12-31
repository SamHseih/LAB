package ccu.pllab.tcgen.AbstractCLG;


import java.util.ArrayList;

public class CLGEndNode extends CLGNode{
	private static ArrayList visted = new ArrayList();
	private static ArrayList vistedForClass = new ArrayList(); //20200917 dai

	public CLGEndNode() {
		super();
	}
	public String toGetImgInfo(){
		String result ="";	
		result += (this.getId() + " " + ("[style=filled, fillcolor=black, shape=\"doublecircle\", label=\"\", fixedsize=true, width=.2, height=.2, xlabel=\"[-1]\"]"+ "\n"));
		return result;
	}
	@Override
	public String toCLPInfo() {
		return ".";
	}
	@Override
	public ArrayList genMethodCLP(String className, String methodName, ArrayList classAttributes, ArrayList methodParameters, ArrayList localParameters, String result) {
		ArrayList<ArrayList<String>> clp = new ArrayList();
		
		if (visted.contains(this.getId()) != true) {
			visted.add(this.getId());
			ArrayList a = new ArrayList();
			a.add("999_999");
			a.add(className + methodName + "_endNode(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception):- \n");
			a.add("	Exception=[].\n");
			clp.add(a);
		}		
		localParameters.clear();
		return clp;
	}
	
	public ArrayList genMethodCLPForClass(String className, String methodName, ArrayList classAttributes, ArrayList methodParameters, ArrayList localParameters, String result) {
		ArrayList<ArrayList<String>> clp = new ArrayList();
		
		if (vistedForClass.contains(this.getId()) != true) {
			vistedForClass.add(this.getId());
			ArrayList a = new ArrayList();
			a.add("999_999");
			//a.add(className + methodName + "_endNode(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception):- \n");
			//a.add("	Exception=[].\n");
			
			a.add(className + methodName + "_endNode(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception).\n");
			a.add("typeCheck([T],R):-\n");
			a.add("    integer(T) -> R = T ; (string(T) -> R = T ; R = []).\n");

			
			clp.add(a);
		}		
		localParameters.clear();
		return clp;
	}
	
//	@Override
//	public String genMethodCLP(String className, String methodName, ArrayList classAttributes, ArrayList methodParameters) {
//		String CLP = "";
//		
//		if (visted.contains(this.getId()) != true) {
//			visted.add(this.getId());
//		}
//		return CLP;
//	}
	
	public String toString(){
		return "(CLGEndNode)";
	}
}
