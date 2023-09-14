package ccu.pllab.tcgen.clgGraph2Path;

import java.io.File;

//2020/4/6 ��z�{���A���N�t�δ��ܬ�"never used", "not use" �����ذ�����

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import ccu.pllab.tcgen.AbstractCLG.CLGConnectionNode;
import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractCLG.CLGCriterionTransformer;
import ccu.pllab.tcgen.AbstractCLG.CLGEdge;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGMethodInvocationNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.TestCase.TestData;
import ccu.pllab.tcgen.TestCase.TestDataClassLevel;
import ccu.pllab.tcgen.TestCase.TestData_New;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.pathCLPFinder.CLPTranslator;
import ccu.pllab.tcgen.transform.TestDate2TestDataTree;
import ccu.pllab.tcgen.typeTestData.MethodTestData;

public class CoverageCriterionManager implements CLGCoverageCriterion {
	private CLGGraph targetCLG;
	private Set<CLGPath> feasible_path;// 4
	private CLGPathEnumerator clgPathEnumerator;// 1
	private CLPTranslator clpTranslator;// 2
	private CLPSolver clpSolver;// 3
	private List<TestData> testDatas;// 5
	private List<MethodTestData> testDataList;
	private List<TestDataClassLevel> testClassDatas;
	private CoverageCheckingTable coverageCheckingTable;// ....

	// ���Ǧa��pathNo�S�[�� �����b�ͦ��ɮ׫e�[
	private int pathNocheck;
	// --
	private Criterion criterionState;
	private CoverageCheckingTable coverageDUPCheckingTable;
	private String criteria;
	private CLGReachingDefinitionAnalyzer clgRDA;
	private ArrayList<DUP> oriDUP;
	private ArrayList<DUP> feasibledup;
	private ArrayList<CLGPath> infeasiblePath;
	private ArrayList<DUP> infeasibledup;

	private ArrayList<String> attribute = new ArrayList<String>();
	private String criterion;
	private String invCLP;
	private String pathObjInfo = "";
	private static Boolean infeasibleTilStartNode = false;
	private static Boolean skipSIPPart = false; // skipSIPPart�ΨӸ��LSIP&CCS����������A�ΨӸѨM�ض�unbound&sortedarray�����D
	private static List<List<CLGNode>> SIPNodesList = new ArrayList<>();
	private Set<List<CLGConstraintNode>> conflictConstraints = new HashSet<>();
	private String className;
	private String methodName;
	private boolean printFail = true;// print fail ecl

	/*
	 * public void setAttribute(ArrayList<String> attribute) {
	 * this.attribute=attribute; } public void setCriterion(String criterion) {
	 * this.criterion=criterion; } public void setin
	 */
	/*******************************************************************/

//	�½c�O�ϥγo��
	public CoverageCriterionManager() {
		this.feasible_path = new LinkedHashSet<CLGPath>();
		this.clgPathEnumerator = new CLGPathEnumerator();
		this.clpTranslator = new CLPTranslator();
		this.clpSolver = new CLPSolver();
		this.coverageCheckingTable = new CoverageCheckingTable();
		// ---
		this.coverageDUPCheckingTable = new CoverageCheckingTable();
		this.criteria = "";
		this.infeasiblePath = new ArrayList<CLGPath>();
		SIPNodesList.clear();
		conflictConstraints.clear();
	}

	public CoverageCriterionManager(ArrayList<String> attribute, String criterion, String invCLP) {
		this.feasible_path = new LinkedHashSet<CLGPath>();
		this.clgPathEnumerator = new CLGPathEnumerator();
		this.clpTranslator = new CLPTranslator();
		this.clpSolver = new CLPSolver();
		this.coverageCheckingTable = new CoverageCheckingTable();
		// ---
		this.coverageDUPCheckingTable = new CoverageCheckingTable();
		this.criteria = "";
		this.infeasiblePath = new ArrayList<CLGPath>();

	}

//	�½c�ϥ�
	@Override
	public void init(CLGGraph graph) {
		this.targetCLG = graph;
		// this.selectCLGGraphCriteria(graph, Main.criterion);//61�M62�P
		this.clgPathEnumerator.init(targetCLG);
		this.testDatas = new ArrayList<TestData>();
//		�o�Ӱj��Nclg�����C����(edge)���X�A��m��coverageCheckingTable��
//		�s��Φ�:{ <1> -> [9]}=0 �����᪺01�N����O�_�л\
		for (CLGEdge edge : graph.getAllBranches()) {
			this.coverageCheckingTable.put(edge, 0);
		}

//		2020/2/10 �H�U��dup�A�ثe���|�Ψ�
		oriDUP = new ArrayList<DUP>();
		if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
				|| Main.criterion.equals(Criterion.mccdup)) {
			System.out.println("part 1 ");
			CLGReachingDefinitionAnalyzer clg2dup = new CLGReachingDefinitionAnalyzer();
			String classname = "";
			classname = ((CLGStartNode) graph.getStartNode()).getClassName();
			clg2dup.dupGenerate(graph, classname, ((CLGStartNode) graph.getStartNode()).getMethodName());

			ArrayList<DUP> clgDUP = clg2dup.dupGenerate(this.targetCLG, classname, "");
			for (int i = 0; i < clgDUP.size(); i++)
				this.coverageDUPCheckingTable.put(clgDUP.get(i), 0);
			oriDUP = clgDUP;
			for (int i1 = 0; i1 < oriDUP.size(); i1++) {
				System.out.println("([" + oriDUP.get(i1).getDefineNode().getXLabelId() + "]," + "["
						+ oriDUP.get(i1).getUseNode().getXLabelId() + "]" + "," + oriDUP.get(i1).getVariable() + ")");
			}
			feasibledup = new ArrayList<DUP>();
			infeasibledup = new ArrayList<DUP>();
			clgRDA = new CLGReachingDefinitionAnalyzer();
		}

	}

	// �i��[@Override
	public void init(CLGGraph graph, String criterion) {
		this.targetCLG = graph;
		this.className = ((CLGStartNode) this.targetCLG.getStartNode()).getClassName();
		this.methodName = ((CLGStartNode) this.targetCLG.getStartNode()).getMethodName();
		this.attribute = attribute;
		this.criterion = criterion;
		// this.selectCLGGraphCriteria(graph, Main.criterion);//61�M62�P
		this.clgPathEnumerator.init(targetCLG);
		this.testDatas = new ArrayList<TestData>();
		for (CLGEdge edge : graph.getAllBranches()) {
			this.coverageCheckingTable.put(edge, 0);
		}

		oriDUP = new ArrayList<DUP>();
		if (criterion.equals("dcdup") || criterion.equals("dccdup") || criterion.equals("mccdup")) {
			System.out.println("part 1 ");
			CLGReachingDefinitionAnalyzer clg2dup = new CLGReachingDefinitionAnalyzer();
			String classname = "";
			classname = ((CLGStartNode) graph.getStartNode()).getClassName();
			clg2dup.dupGenerate(graph, classname, ((CLGStartNode) graph.getStartNode()).getMethodName());

			ArrayList<DUP> clgDUP = clg2dup.dupGenerate(this.targetCLG, classname, "");
			for (int i = 0; i < clgDUP.size(); i++)
				this.coverageDUPCheckingTable.put(clgDUP.get(i), 0);
			oriDUP = clgDUP;
			for (int i1 = 0; i1 < oriDUP.size(); i1++) {
				System.out.println("([" + oriDUP.get(i1).getDefineNode().getXLabelId() + "]," + "["
						+ oriDUP.get(i1).getUseNode().getXLabelId() + "]" + "," + oriDUP.get(i1).getVariable() + ")");
			}
			feasibledup = new ArrayList<DUP>();
			infeasibledup = new ArrayList<DUP>();
			clgRDA = new CLGReachingDefinitionAnalyzer();
		}

	}

	// �ϥΫض��Ǫ� ocl parser 202103 dai
	public void init(CLGGraph graph, ArrayList<CLGGraph> ocl_clg, String criterion) {
		this.targetCLG = graph;
		this.attribute = attribute;
		this.criterion = criterion;
		// this.selectCLGGraphCriteria(graph, Main.criterion);//61�M62�P
		this.clgPathEnumerator.init(targetCLG);
		this.testDatas = new ArrayList<TestData>();
		this.testClassDatas = new ArrayList<TestDataClassLevel>();

		for (CLGEdge edge : graph.getAllBranches()) {
			this.coverageCheckingTable.put(edge, 0);
		}

		oriDUP = new ArrayList<DUP>();
		if (criterion.equals("dcdup") || criterion.equals("dccdup") || criterion.equals("mccdup")) {
			System.out.println("part 1 ");
			CLGReachingDefinitionAnalyzer clg2dup = new CLGReachingDefinitionAnalyzer();
//					String classname = "";
//					classname = ((CLGStartNode) graph.getStartNode()).getClassName();
//					clg2dup.dupGenerate(ocl_clg, classname, ((CLGStartNode) graph.getStartNode()).getMethodName());

			String classname = "";
			classname = ((CLGStartNode) ocl_clg.get(0).getStartNode()).getClassName();
			for (int i = 0; i < ocl_clg.size(); i++) {
				clg2dup.dupGenerate(ocl_clg.get(i), classname,
						((CLGStartNode) ocl_clg.get(i).getStartNode()).getMethodName());
			} // end for i

			ArrayList<DUP> clgDUP = clg2dup.dupGenerate(this.targetCLG, classname, "");
			for (int i = 0; i < clgDUP.size(); i++)
				this.coverageDUPCheckingTable.put(clgDUP.get(i), 0);
			oriDUP = clgDUP;
			for (int i1 = 0; i1 < oriDUP.size(); i1++) {
				System.out.println("([" + oriDUP.get(i1).getDefineNode().getXLabelId() + "]," + "["
						+ oriDUP.get(i1).getUseNode().getXLabelId() + "]" + "," + oriDUP.get(i1).getVariable() + ")");
			}
			feasibledup = new ArrayList<DUP>();
			infeasibledup = new ArrayList<DUP>();
			clgRDA = new CLGReachingDefinitionAnalyzer();
		}

	}

	/* State diagram analysis */
	public void init(CLGGraph graph, Criterion criterionState, List<ccu.pllab.tcgen.ast.Constraint> OCLCons) {
		this.targetCLG = graph;
		this.selectCLGGraphCriteria(graph, Main.criterion);
		this.criterionState = criterionState;
		this.clgPathEnumerator.init(targetCLG);
		this.testDatas = new ArrayList<TestData>();
		this.testClassDatas = new ArrayList<TestDataClassLevel>();
		for (CLGEdge edge : graph.getAllBranches()) {
			this.coverageCheckingTable.put(edge, 0);
		}
		this.criteria = criteria;

		// --DUP--
		oriDUP = new ArrayList<DUP>();
		if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
				|| Main.criterion.equals(Criterion.mccdup)) {
			System.out.println("part 1 ");
			CLGReachingDefinitionAnalyzer clg2dup = new CLGReachingDefinitionAnalyzer();
			String classname = "";
			for (int i = 0; i < OCLCons.size(); i++) {
				if (OCLCons.get(i).getConstraintKind() == "postcondition") {
					clg2dup.dupGenerate(OCLCons.get(i).OCL2CLG(), OCLCons.get(i).getConstraintedClassName(),
							OCLCons.get(i).getConstraintedMethodName());
				}
				classname = OCLCons.get(i).getConstraintedClassName();
			} // end for i

			ArrayList<DUP> clgDUP = clg2dup.dupGenerate(this.targetCLG, classname, "");
			for (int i = 0; i < clgDUP.size(); i++)
				this.coverageDUPCheckingTable.put(clgDUP.get(i), 0);
			oriDUP = clgDUP;
			for (int i1 = 0; i1 < oriDUP.size(); i1++) {
				System.out.println("([" + oriDUP.get(i1).getDefineNode().getXLabelId() + "]," + "["
						+ oriDUP.get(i1).getUseNode().getXLabelId() + "]" + "," + oriDUP.get(i1).getVariable() + ")");
			}
			feasibledup = new ArrayList<DUP>();
			infeasibledup = new ArrayList<DUP>();
			clgRDA = new CLGReachingDefinitionAnalyzer();
		}
		// ---
	}

//	�½c�ϥ� ~line 1622
	@SuppressWarnings("rawtypes")
	public List<MethodTestData> genTestSuite(SymbolTable symbolTable) throws IOException, CloneNotSupportedException {
		String path = "";
		this.clpTranslator = new CLPTranslator();
		this.clpTranslator.setSymbolTable(symbolTable);
		String graphClassName = ((CLGStartNode) this.targetCLG.getStartNode()).getFirstUpperClassName();
		String graphMethodName = ((CLGStartNode) this.targetCLG.getStartNode()).getFirstUpperMethodName();

		String className = ((CLGStartNode) this.targetCLG.getStartNode()).getClassName();
		String methodName = ((CLGStartNode) this.targetCLG.getStartNode()).getMethodName();
		String graphRetType = ((CLGStartNode) this.targetCLG.getStartNode()).getReturnType().getType();

		boolean graphIsConstructor = ((CLGStartNode) this.targetCLG.getStartNode()).isConstructor();

		CLGPath pathObj;
		CLGPath shortestInfeasiblePath; // for���|�Y��, �쪩CLGPath�O�d
		List<CLGNode> SIPNodes = null; // for���|�Y��, ��X��infeasible�̵u�`�I
		Boolean isFeasible = false; // for���|�Y��, �P�O�ӱ����|�O�_����
		String graphMethodRetType = "";
		String saveTDPathDUP = "";//
		BoundaryEnumerator boundaryEnumerator = new BoundaryEnumerator();
		int pathNo = 1;
		int boundsolution = 0;
		int totalbound = 0;
		boolean hasInitPathCount = false;
		String TestTypeSign = "";
		boolean printInfeasiblePath = true; // print infeasible path
		String iteratePath = "";

//		�P�O���պ������½c�Υսc��
		if (Main.TestType == "BlackBox") {
			TestTypeSign = "B";
		} else if (Main.TestType == "WhiteBox") {
			TestTypeSign = "W";
		} else {
			TestTypeSign = "C";
		}

//		clgPathEnumerator.next() ��clg����|�A�C���u�^�Ǥ@��������|
//		pathObj�N��䤤�@�����|�A�]�N�O�H�Uwhile�j��@���u��䤤�@�����|
//		meetCriterion() �ˬdcoverageCheckingTable���O�_�٦��S����쪺��AcoverageCheckingTable�u����ӱ����|���Ѯɤ~�|��
//		�j����פW�ɮɽнհ�meetCriterion����
//		���|�W����Ь����X�L

		while (!this.meetCriterion() && (pathObj = clgPathEnumerator.next()) != null) {
			CLGPath boundaryPath = new CLGPath();

			isFeasible = false;
			skipSIPPart = false;
			// 20200622 ���mpathID��count
			if (!hasInitPathCount) {
				pathObj.initPathCount();
				pathObj.initPathID();
				hasInitPathCount = true;
			}

			// �ثeDUP���ϥ�
			ArrayList<DUP> nowDUP = new ArrayList<DUP>();
			if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
					|| Main.criterion.equals(Criterion.mccdup)) {
				System.out.println("control part 2 ");
				List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(pathObj.getPathNodes());
				ArrayList<DUP> regDUP = new ArrayList<DUP>();
				CLGPath clgp = new CLGPath(new_path1);
				regDUP = clgRDA.parsePathDUP(clgp, oriDUP);
				System.out.println(" regdup " + regDUP.size());
				for (int i = 0; i < regDUP.size(); i++) {
					if (this.coverageDUPCheckingTable.containsKey(regDUP.get(i))) {
						nowDUP.add(regDUP.get(i));
					}
				}
				System.out.println("Path = " + clgp.toGetPathInfo());
				for (DUP p : nowDUP) {
					System.out.println(p.DUP2Str());
				}
			}
			// -------

//			�NpathObj���C������X�A�[�J��intersectionalEdge��
//			�åBcoverageCheckingTable��������զX��+1
			Set<CLGEdge> intersectionalEdge = new HashSet<CLGEdge>();
			for (CLGEdge b : pathObj.getEdges()) {
				if (this.coverageCheckingTable.containsKey(b)) {
					intersectionalEdge.add(b);
					coverageCheckingTable.put(b, ((Integer) coverageCheckingTable.get(b)) + 1);
				}
			}

			// �ˬd�����|�O�_�����л\�סA�|���֥ܲ[���ո��20200519
			// �Y�b��ѫ���edge�A������ɭȤ��R�����p�U�N���|�[�J��L��ɪ���
//			if(isIncreaseCoverage(intersectionalEdge)) {
//				testDatas.add(clpSolver.getTestData());
//			}

//			�R���Ҧ�pathObj�����s���`�I
			pathObj.removeAllConnectionNode();

			boundaryEnumerator.init(pathObj);

			if (intersectionalEdge.size() > 0 || nowDUP.size() > 0) {
				System.out.println("control intersectionalEdge ");
				boolean flag = false;

				if (Main.boundary_analysis) {
					int originalPathNo = pathNo;
					boundaryPath = boundaryEnumerator.next();
					// if there is no boundary path, test the original path
					if (boundaryPath == null) {
						boundaryPath = pathObj;
					}
					/*
					 * �C����ɸ��|�A�ܤַ|���]�@���A�A�M��O�_����L��ɸ��|
					 */
					do {
						String clpContent = "";
						clpContent = clpTranslator.genPathCLP(boundaryPath, className, methodName, graphIsConstructor);
						DataWriter.writeInfo(clpContent, graphClassName + graphMethodName + TestTypeSign + pathNo,
								"ecl", DataWriter.testCons_output_path);
						if (clpSolver.solving(graphClassName, graphMethodName, pathNo, 1, graphIsConstructor,
								graphRetType, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal")) {
							TestData tempdata = clpSolver.getTestData();
							testDatas.add(tempdata);
							isFeasible = true;
							this.removeCoveredBranches(intersectionalEdge);
						}
						if (printFail)
							pathNo++;
					} while ((boundaryPath = boundaryEnumerator.next()) != null);

					// path info, iterator info
					if (isFeasible) {
						pathObjInfo += pathObj.getPathObjConsInfo();
						pathObjInfo += "feasible\n";
						iteratePath += this.getPathIterator(originalPathNo, pathObj);
					} else if (printInfeasiblePath) {
						pathObjInfo += pathObj.getPathObjConsInfo();
						iteratePath += this.getPathIterator(originalPathNo, pathObj);
					}

//						for���|�Y��  �̵u���i����|
//						�p�G�ӱ����|���Ҧ���ɸ��|����infeasible�A�h��ӱ����|��M�̵u���i����|
					String SIP = ""; // SIP -> shortestInfeasiblePath
					if (!isFeasible && skipSIPPart == false) {
						pathObjInfo += "This path is infeasible!\n";

						// 20200616����
//							preRemovalInfeasiblePathManager(pathObj, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);

						preRemovalInfeasiblePathManager(pathObj, className, methodName, pathNo, graphIsConstructor);

//							binary search:�ɶ������׬�O(log n)
//							infeasibleTilStartNode = false;
//							CLGNode endNode = pathObj.getLastNode();
//							String clpContent = clpTranslator.genPathCLP(pathObj);
////							pathObj = SIPBinarySearch(pathObj, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//							pathObj = SIPSearch(clpContent, pathObj, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//							
////							���SIP���䰵����B�z
////							test, �p�G������|����startNode���䤣��Ѫ���
//							if(infeasibleTilStartNode) {
////								���N��SIP��JSIPNodesList��
//								SIP = pathObj.toGetPathInfo() + "\n";
//								pathObjInfo += SIP + "This path will not enter SIP check table!\n";
//							}else {
//								SIPNodes = pathObj.getPathNodes();
//								
////								test
//								String temp2 = "";
//								List<String> tempCon = new ArrayList<>();
//								List<String> tempList = new ArrayList<>();
//								
////								���Ĭ𭭨��
////								tempList = findConflictConstraint(pathObj, endNode, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//								tempList = CCSSearch(clpContent, pathObj, endNode, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//								
//								if(tempList.size() != 0) {
//									for(String conflict : tempList) {
//										tempCon.add(conflict);
//									}
//									
////									SIP�̫�@�Ӹ`�I�A�����[�J
//									CLGConstraintNode cons2 = (CLGConstraintNode) pathObj.getPathNodes().get(pathObj.getPathNodes().size()-1);
//									temp2 += cons2.toCLPInfo() + ",";
////									temp2 += clgPathEnumerator.getCLGht().get(cons2) +",";
//									tempCon.add(temp2);
//									
//									conflictConstraints.add(tempCon);
//									
//									pathObjInfo += "conflict constraint set: ";
//									for(String s : tempCon) {
//										pathObjInfo += s + " ";
//									}
//									pathObjInfo += "\n";
//								}
//
//								SIPNodesList.add(SIPNodes);
//								SIP = pathObj.toGetPathInfo() + "\n";
//								pathObjInfo += SIP ;
//							}
					}
//						clpTranslator.setShortestInfeai(false);
				} else {
//						�H�U�O������ɭȤ��R�ASIP&CCS�٨S����

					pathObjInfo += this.getPathObjConsInfo(pathObj);

					String clpContent = "";
					clpContent = clpTranslator.genPathCLP(pathObj, className, methodName, graphIsConstructor);
					DataWriter.writeInfo(clpContent, graphClassName + graphMethodName + TestTypeSign + pathNo, "ecl",
							DataWriter.testCons_output_path);
					if (clpSolver.solving(graphClassName, graphMethodName, pathNo, 1, graphIsConstructor, graphRetType,
							"Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal")) {
						TestData tempdata = clpSolver.getTestData();
						testDatas.add(tempdata);
						this.removeCoveredBranches(intersectionalEdge);
					}
					pathNo++;
				}
//					�ثeDUP�������ϥΡA
				if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
						|| Main.criterion.equals(Criterion.mccdup)) {
					System.out.println("part 3 ");
					if (clpSolver.getTestDataclass() == null) {
						for (int dupi = 0; dupi < nowDUP.size(); dupi++) {
							int count = (int) this.coverageDUPCheckingTable.get(nowDUP.get(dupi));
							this.coverageDUPCheckingTable.put(nowDUP.get(dupi), (count + 1));
						}
						this.infeasiblePath.add(pathObj);
						this.removeCoveredBranches(intersectionalEdge);
					} else {// sol
						for (int dupi = 0; dupi < nowDUP.size(); dupi++) {
							this.feasible_path.add(pathObj);
							this.feasibledup.add(nowDUP.get(dupi));
							this.coverageDUPCheckingTable.remove(nowDUP.get(dupi));
							this.removeCoveredBranches(intersectionalEdge);
						}
						List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(pathObj.getPathNodes());
						CLGPath clgp = new CLGPath(new_path1);
						saveTDPathDUP += "Path = " + clgp.toGetPathInfo();
						for (DUP p : nowDUP) {
							saveTDPathDUP += "\n" + p.DUP2Str();
						}
						saveTDPathDUP += "\n" + clpSolver.getTestData() + "\n\n";
					}
				}
				System.out.println("path:" + pathObj.toGetPathInfo());

			}
		}

//		matchConflictConstraints
//		}

//		�g�X���|���
		pathObjInfo = pathObjInfo.replaceAll("[\\(\\)]", "");
		pathObjInfo = pathObjInfo.replaceAll("CLGStartNode", "[0]");
		pathObjInfo = pathObjInfo.replaceAll("CLGEndNode", "[-1]");
		if (Main.TestType == "WhiteBox") {
			DataWriter.testPath_output_path = DataWriter.output_folder_path + "\\test paths\\" + graphClassName
					+ "_WhiteBox_" + Main.criterion + "\\";
		}
		DataWriter.writeInfo(pathObjInfo, graphClassName + graphMethodName + "_pathInfo", "txt",
				DataWriter.testPath_output_path);
		DataWriter.writeInfo(iteratePath, graphClassName + graphMethodName + "_ItInfo", "txt",
				DataWriter.testPath_output_path);
		saveTDPathDUP += "\n\nfeasiblepathsize = " + feasible_path.size() + "\n"; //
		System.out.println("feasiblepathsize = " + feasible_path.size());
		for (CLGPath p : feasible_path) {
			System.out.println(p.toGetPathInfo());
			List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(p.getPathNodes());
			CLGPath clgp = new CLGPath(new_path1);
			saveTDPathDUP += clgp.toGetPathInfo() + "\n";
		}

//		�ثeDUP�������ϥ�
		if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
				|| Main.criterion.equals(Criterion.mccdup)) {
			System.out.println("part 4 ");
			ArrayList<DUP> indup = new ArrayList<DUP>();
			for (Object dup : this.coverageDUPCheckingTable.keySet()) {
				if (dup instanceof DUP) {
					if (!indup.contains((DUP) dup))
						indup.add((DUP) dup);
				}
			}
			for (int i = 0; i < indup.size(); i++) {
				if (!this.infeasibledup.contains(indup.get(i)))
					this.infeasibledup.add(indup.get(i));
				if (this.coverageDUPCheckingTable != null) {
					if (this.coverageDUPCheckingTable.containsKey(indup.get(i)))
						this.coverageDUPCheckingTable.remove(indup.get(i));
				}
			}
			saveTDPathDUP += "\n\nfeasibledup size = " + feasibledup.size();//
			System.out.println("feasibledup size = " + feasibledup.size());
			for (int i = 0; i < feasibledup.size(); i++) {
				saveTDPathDUP += "\n" + feasibledup.get(i).DUP2Str();//
			}

			saveTDPathDUP += "\n\ninfeasibledup size = " + infeasibledup.size();//
			System.out.println("infeasibledup size = " + infeasibledup.size());
			for (int i = 0; i < infeasibledup.size(); i++) {
				saveTDPathDUP += "\n" + infeasibledup.get(i).DUP2Str();//
			}
		}

		String content = "";
		for (CLGPath p : feasible_path) {
			System.out.println(p.toGetPathInfo());
		}
		List<TestData> newDatas = new ArrayList<TestData>();
		List<TestData> tempDatas = new ArrayList<TestData>();
		for (TestData td : testDatas) {
			tempDatas.add(td);
		}
		int testcase_count = 0;
		for (TestData td : tempDatas) {
			String temp_content = "OBJ_PRE = " + td.getObjPre() + ", ARG_PRE = " + td.getArgPre() + ",OBJ_POST = "
					+ td.getObjPost() + ",ARG_POST = " + td.getArgPost() + ", RETVAL = " + td.getRetVal()
					+ ", EXCEPTION =" + td.getException() + "\n";
			if (!content.contains(temp_content))// �[
			{
				testcase_count++;
				content += testcase_count + ": " + temp_content;
				newDatas.add(td);
			}
		}
		this.testDatas = newDatas;

		DataWriter.writeInfo(content, graphClassName + graphMethodName, "txt", DataWriter.testData_output_path);

		TestDate2TestDataTree testDataTree = new TestDate2TestDataTree();
		testDataTree.makeAST(Main.typeTable.get(className, null), className, methodName,
				new File(DataWriter.testData_output_path + graphClassName + graphMethodName + ".txt"));
		this.testDataList = testDataTree.getTestDataList();
		return this.testDataList;
	}

	@SuppressWarnings("rawtypes")
	public List<MethodTestData> genTestSuite_VersionControl(String version, String compareVer, SymbolTable symbolTable) throws IOException, CloneNotSupportedException {
		String path = "";
		this.clpTranslator = new CLPTranslator();
		this.clpTranslator.setSymbolTable(symbolTable);
		String graphClassName = ((CLGStartNode) this.targetCLG.getStartNode()).getFirstUpperClassName();
		String graphMethodName = ((CLGStartNode) this.targetCLG.getStartNode()).getFirstUpperMethodName();

		String className = ((CLGStartNode) this.targetCLG.getStartNode()).getClassName();
		String methodName = ((CLGStartNode) this.targetCLG.getStartNode()).getMethodName();
		String graphRetType = ((CLGStartNode) this.targetCLG.getStartNode()).getReturnType().getType();

		boolean graphIsConstructor = ((CLGStartNode) this.targetCLG.getStartNode()).isConstructor();

		CLGPath pathObj;
		CLGPath shortestInfeasiblePath; // for���|�Y��, �쪩CLGPath�O�d
		List<CLGNode> SIPNodes = null; // for���|�Y��, ��X��infeasible�̵u�`�I
		Boolean isFeasible = false; // for���|�Y��, �P�O�ӱ����|�O�_����
		String graphMethodRetType = "";
		String saveTDPathDUP = "";//
		BoundaryEnumerator boundaryEnumerator = new BoundaryEnumerator();
		int pathNo = 1;
		int boundsolution = 0;
		int totalbound = 0;
		boolean hasInitPathCount = false;
		String TestTypeSign = "";
		boolean printInfeasiblePath = true; // print infeasible path
		String iteratePath = "";
		
		if(!compareVer.equals("0")) {
			this.TPD = new TestData_Path_Dependency();
			try {
				this.TPD.Build_Dependency(FileHandleToServer.readFile(graphClassName+"\\version "+compareVer+"\\dependency\\",graphClassName+graphMethodName+"_dependency.txt"));
			} catch(Exception ex) {
				this.TPD.setpathConstriant_zeroVersion();
			}
		} else {
			System.out.println("no compare version!");
			this.TPD = new TestData_Path_Dependency();
			this.TPD.setpathConstriant_zeroVersion();
		}

//		�P�O���պ������½c�Υսc��
		if (Main.TestType == "BlackBox") {
			TestTypeSign = "B";
		} else if (Main.TestType == "WhiteBox") {
			TestTypeSign = "W";
		} else {
			TestTypeSign = "C";
		}

//		clgPathEnumerator.next() ��clg����|�A�C���u�^�Ǥ@��������|
//		pathObj�N��䤤�@�����|�A�]�N�O�H�Uwhile�j��@���u��䤤�@�����|
//		meetCriterion() �ˬdcoverageCheckingTable���O�_�٦��S����쪺��AcoverageCheckingTable�u����ӱ����|���Ѯɤ~�|��
//		�j����פW�ɮɽнհ�meetCriterion����
//		���|�W����Ь����X�L

		while (!this.meetCriterion() && (pathObj = clgPathEnumerator.next()) != null) {
			CLGPath boundaryPath = new CLGPath();

			isFeasible = false;
			skipSIPPart = false;
			// 20200622 ���mpathID��count
			if (!hasInitPathCount) {
				pathObj.initPathCount();
				pathObj.initPathID();
				hasInitPathCount = true;
			}

			String tempConStr2 = "";
			for (int i = 0; i < pathObj.getPathNodes().size(); i++) {
				if (pathObj.getPathNodes().get(i) instanceof CLGConstraintNode) {
					CLGConstraintNode cons = (CLGConstraintNode) pathObj.getPathNodes().get(i);
					tempConStr2 += cons.toCLPInfo().replace("#", "") + ",";
				}
			}

			hasSamePath = TPD.hasSamePath(tempConStr);
			//�^�Ǥ��O"false"��ܤW��������L�F
			if(!hasSamePath.equals("false")) {
				tempConStr = ""; 
				String[] samePathNum = hasSamePath.split(",");
				for(int cnt = Integer.valueOf(samePathNum[0]); cnt < Integer.valueOf(samePathNum[1]); cnt++) {
					content += TPD.getTestData(cnt)+"\n";
					TD_path_dependency += TPD.getPathConstriant(cnt) +"\n"
										  + TPD.getTestData(cnt) + "\n";
					ignore_cnt++;
				}
			}
			//�S���ۦP���| ���
		else {

			// �ثeDUP���ϥ�
			ArrayList<DUP> nowDUP = new ArrayList<DUP>();
			if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
					|| Main.criterion.equals(Criterion.mccdup)) {
				System.out.println("control part 2 ");
				List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(pathObj.getPathNodes());
				ArrayList<DUP> regDUP = new ArrayList<DUP>();
				CLGPath clgp = new CLGPath(new_path1);
				regDUP = clgRDA.parsePathDUP(clgp, oriDUP);
				System.out.println(" regdup " + regDUP.size());
				for (int i = 0; i < regDUP.size(); i++) {
					if (this.coverageDUPCheckingTable.containsKey(regDUP.get(i))) {
						nowDUP.add(regDUP.get(i));
					}
				}
				System.out.println("Path = " + clgp.toGetPathInfo());
				for (DUP p : nowDUP) {
					System.out.println(p.DUP2Str());
				}
			}
			// -------

//			�NpathObj���C������X�A�[�J��intersectionalEdge��
//			�åBcoverageCheckingTable��������զX��+1
			Set<CLGEdge> intersectionalEdge = new HashSet<CLGEdge>();
			for (CLGEdge b : pathObj.getEdges()) {
				if (this.coverageCheckingTable.containsKey(b)) {
					intersectionalEdge.add(b);
					coverageCheckingTable.put(b, ((Integer) coverageCheckingTable.get(b)) + 1);
				}
			}

			// �ˬd�����|�O�_�����л\�סA�|���֥ܲ[���ո��20200519
			// �Y�b��ѫ���edge�A������ɭȤ��R�����p�U�N���|�[�J��L��ɪ���
//			if(isIncreaseCoverage(intersectionalEdge)) {
//				testDatas.add(clpSolver.getTestData());
//			}

//			�R���Ҧ�pathObj�����s���`�I
			pathObj.removeAllConnectionNode();

			boundaryEnumerator.init(pathObj);

			if (intersectionalEdge.size() > 0 || nowDUP.size() > 0) {
				System.out.println("control intersectionalEdge ");
				boolean flag = false;

				if (Main.boundary_analysis) {
					int originalPathNo = pathNo;
					boundaryPath = boundaryEnumerator.next();
					// if there is no boundary path, test the original path
					if (boundaryPath == null) {
						boundaryPath = pathObj;
					}
					/*
					 * �C����ɸ��|�A�ܤַ|���]�@���A�A�M��O�_����L��ɸ��|
					 */
					do {
						String clpContent = "";
						clpContent = clpTranslator.genPathCLP(boundaryPath, className, methodName, graphIsConstructor);
						DataWriter.writeInfo(clpContent, graphClassName + graphMethodName + TestTypeSign + pathNo,
								"ecl", DataWriter.testCons_output_path);
						if (clpSolver.solving(graphClassName, graphMethodName, pathNo, 1, graphIsConstructor,
								graphRetType, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal")) {
							TestData tempdata = clpSolver.getTestData();
							testDatas.add(tempdata);
							isFeasible = true;
							this.removeCoveredBranches(intersectionalEdge);
						}
						if (printFail)
							pathNo++;
					} while ((boundaryPath = boundaryEnumerator.next()) != null);

					// path info, iterator info
					if (isFeasible) {
						pathObjInfo += pathObj.getPathObjConsInfo();
						pathObjInfo += "feasible\n";
						iteratePath += this.getPathIterator(originalPathNo, pathObj);
					} else if (printInfeasiblePath) {
						pathObjInfo += pathObj.getPathObjConsInfo();
						iteratePath += this.getPathIterator(originalPathNo, pathObj);
					}

//						for���|�Y��  �̵u���i����|
//						�p�G�ӱ����|���Ҧ���ɸ��|����infeasible�A�h��ӱ����|��M�̵u���i����|
					String SIP = ""; // SIP -> shortestInfeasiblePath
					if (!isFeasible && skipSIPPart == false) {
						pathObjInfo += "This path is infeasible!\n";

						// 20200616����
//							preRemovalInfeasiblePathManager(pathObj, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);

						preRemovalInfeasiblePathManager(pathObj, className, methodName, pathNo, graphIsConstructor);

//							binary search:�ɶ������׬�O(log n)
//							infeasibleTilStartNode = false;
//							CLGNode endNode = pathObj.getLastNode();
//							String clpContent = clpTranslator.genPathCLP(pathObj);
////							pathObj = SIPBinarySearch(pathObj, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//							pathObj = SIPSearch(clpContent, pathObj, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//							
////							���SIP���䰵����B�z
////							test, �p�G������|����startNode���䤣��Ѫ���
//							if(infeasibleTilStartNode) {
////								���N��SIP��JSIPNodesList��
//								SIP = pathObj.toGetPathInfo() + "\n";
//								pathObjInfo += SIP + "This path will not enter SIP check table!\n";
//							}else {
//								SIPNodes = pathObj.getPathNodes();
//								
////								test
//								String temp2 = "";
//								List<String> tempCon = new ArrayList<>();
//								List<String> tempList = new ArrayList<>();
//								
////								���Ĭ𭭨��
////								tempList = findConflictConstraint(pathObj, endNode, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//								tempList = CCSSearch(clpContent, pathObj, endNode, graphClassName, graphMethodName, pathNo, graphIsConstructor, graphRetType);
//								
//								if(tempList.size() != 0) {
//									for(String conflict : tempList) {
//										tempCon.add(conflict);
//									}
//									
////									SIP�̫�@�Ӹ`�I�A�����[�J
//									CLGConstraintNode cons2 = (CLGConstraintNode) pathObj.getPathNodes().get(pathObj.getPathNodes().size()-1);
//									temp2 += cons2.toCLPInfo() + ",";
////									temp2 += clgPathEnumerator.getCLGht().get(cons2) +",";
//									tempCon.add(temp2);
//									
//									conflictConstraints.add(tempCon);
//									
//									pathObjInfo += "conflict constraint set: ";
//									for(String s : tempCon) {
//										pathObjInfo += s + " ";
//									}
//									pathObjInfo += "\n";
//								}
//
//								SIPNodesList.add(SIPNodes);
//								SIP = pathObj.toGetPathInfo() + "\n";
//								pathObjInfo += SIP ;
//							}
					}
//						clpTranslator.setShortestInfeai(false);
				} else {
//						�H�U�O������ɭȤ��R�ASIP&CCS�٨S����

					pathObjInfo += this.getPathObjConsInfo(pathObj);

					String clpContent = "";
					clpContent = clpTranslator.genPathCLP(pathObj, className, methodName, graphIsConstructor);
					DataWriter.writeInfo(clpContent, graphClassName + graphMethodName + TestTypeSign + pathNo, "ecl",
							DataWriter.testCons_output_path);
					if (clpSolver.solving(graphClassName, graphMethodName, pathNo, 1, graphIsConstructor, graphRetType,
							"Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal")) {
						TestData tempdata = clpSolver.getTestData();
						testDatas.add(tempdata);
						this.removeCoveredBranches(intersectionalEdge);
					}
					pathNo++;
				}
//					�ثeDUP�������ϥΡA
				if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
						|| Main.criterion.equals(Criterion.mccdup)) {
					System.out.println("part 3 ");
					if (clpSolver.getTestDataclass() == null) {
						for (int dupi = 0; dupi < nowDUP.size(); dupi++) {
							int count = (int) this.coverageDUPCheckingTable.get(nowDUP.get(dupi));
							this.coverageDUPCheckingTable.put(nowDUP.get(dupi), (count + 1));
						}
						this.infeasiblePath.add(pathObj);
						this.removeCoveredBranches(intersectionalEdge);
					} else {// sol
						for (int dupi = 0; dupi < nowDUP.size(); dupi++) {
							this.feasible_path.add(pathObj);
							this.feasibledup.add(nowDUP.get(dupi));
							this.coverageDUPCheckingTable.remove(nowDUP.get(dupi));
							this.removeCoveredBranches(intersectionalEdge);
						}
						List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(pathObj.getPathNodes());
						CLGPath clgp = new CLGPath(new_path1);
						saveTDPathDUP += "Path = " + clgp.toGetPathInfo();
						for (DUP p : nowDUP) {
							saveTDPathDUP += "\n" + p.DUP2Str();
						}
						saveTDPathDUP += "\n" + clpSolver.getTestData() + "\n\n";
					}
				}
				System.out.println("path:" + pathObj.toGetPathInfo());

			}
		}

//		matchConflictConstraints
//		}
		}
//		�g�X���|���
		pathObjInfo = pathObjInfo.replaceAll("[\\(\\)]", "");
		pathObjInfo = pathObjInfo.replaceAll("CLGStartNode", "[0]");
		pathObjInfo = pathObjInfo.replaceAll("CLGEndNode", "[-1]");
		if (Main.TestType == "WhiteBox") {
			DataWriter.testPath_output_path = DataWriter.output_folder_path + "\\test paths\\" + graphClassName
					+ "_WhiteBox_" + Main.criterion + "\\";
		}
		DataWriter.writeInfo(pathObjInfo, graphClassName + graphMethodName + "_pathInfo", "txt",
				DataWriter.testPath_output_path);
		DataWriter.writeInfo(iteratePath, graphClassName + graphMethodName + "_ItInfo", "txt",
				DataWriter.testPath_output_path);
		saveTDPathDUP += "\n\nfeasiblepathsize = " + feasible_path.size() + "\n"; //
		System.out.println("feasiblepathsize = " + feasible_path.size());
		for (CLGPath p : feasible_path) {
			System.out.println(p.toGetPathInfo());
			List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(p.getPathNodes());
			CLGPath clgp = new CLGPath(new_path1);
			saveTDPathDUP += clgp.toGetPathInfo() + "\n";
		}

//		�ثeDUP�������ϥ�
		if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
				|| Main.criterion.equals(Criterion.mccdup)) {
			System.out.println("part 4 ");
			ArrayList<DUP> indup = new ArrayList<DUP>();
			for (Object dup : this.coverageDUPCheckingTable.keySet()) {
				if (dup instanceof DUP) {
					if (!indup.contains((DUP) dup))
						indup.add((DUP) dup);
				}
			}
			for (int i = 0; i < indup.size(); i++) {
				if (!this.infeasibledup.contains(indup.get(i)))
					this.infeasibledup.add(indup.get(i));
				if (this.coverageDUPCheckingTable != null) {
					if (this.coverageDUPCheckingTable.containsKey(indup.get(i)))
						this.coverageDUPCheckingTable.remove(indup.get(i));
				}
			}
			saveTDPathDUP += "\n\nfeasibledup size = " + feasibledup.size();//
			System.out.println("feasibledup size = " + feasibledup.size());
			for (int i = 0; i < feasibledup.size(); i++) {
				saveTDPathDUP += "\n" + feasibledup.get(i).DUP2Str();//
			}

			saveTDPathDUP += "\n\ninfeasibledup size = " + infeasibledup.size();//
			System.out.println("infeasibledup size = " + infeasibledup.size());
			for (int i = 0; i < infeasibledup.size(); i++) {
				saveTDPathDUP += "\n" + infeasibledup.get(i).DUP2Str();//
			}
		}

		String content = "";
		for (CLGPath p : feasible_path) {
			System.out.println(p.toGetPathInfo());
		}
		List<TestData> newDatas = new ArrayList<TestData>();
		List<TestData> tempDatas = new ArrayList<TestData>();
		for (TestData td : testDatas) {
			tempDatas.add(td);
		}
		int testcase_count = 0;
		for (TestData td : tempDatas) {
			String temp_content = "OBJ_PRE = " + td.getObjPre() + ", ARG_PRE = " + td.getArgPre() + ",OBJ_POST = "
					+ td.getObjPost() + ",ARG_POST = " + td.getArgPost() + ", RETVAL = " + td.getRetVal()
					+ ", EXCEPTION =" + td.getException() + "\n";
			if (!content.contains(temp_content))// �[
			{
				testcase_count++;
				content += testcase_count + ": " + temp_content;
				newDatas.add(td);
			}
		}
		this.testDatas = newDatas;

		DataWriter.writeInfo(content, graphClassName + graphMethodName, "txt", DataWriter.testData_output_path);

		TestDate2TestDataTree testDataTree = new TestDate2TestDataTree();
		testDataTree.makeAST(Main.typeTable.get(className, null), className, methodName,
				new File(DataWriter.testData_output_path + graphClassName + graphMethodName + ".txt"));
		this.testDataList = testDataTree.getTestDataList();
		return this.testDataList;
	}


	// --------DUP analysis
	@SuppressWarnings("rawtypes")
	public List<TestDataClassLevel> genClassLevelTestSuite(TypeTable typeTab) throws IOException {
		String graphClassName = ((CLGStartNode) this.targetCLG.getStartNode()).getFirstUpperClassName();
		String graphMethodName = ((CLGStartNode) this.targetCLG.getStartNode()).getFirstUpperMethodName();
		boolean graphMethodIsConstructor = ((CLGStartNode) this.targetCLG.getStartNode()).isConstructor();
		String graphMethodRetType = " ";
		CLGPath pathObj;
		int pathnum = clpTranslator.getPathNumber() + 1;
		String saveTDPathDUP = "";// --816
		while (!this.meetCriterion() && (pathObj = clgPathEnumerator.next()) != null) {
			// ---dup 816
			ArrayList<DUP> nowDUP = new ArrayList<DUP>();
			if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
					|| Main.criterion.equals(Criterion.mccdup)) {
				System.out.println("part 2 ");
				List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(pathObj.getPathNodes());
				ArrayList<DUP> regDUP = new ArrayList<DUP>();
				CLGPath clgp = new CLGPath(new_path1, pathObj.toGetPathId());
				regDUP = clgRDA.parsePathDUP(clgp, oriDUP);
				System.out.println(" regdup " + regDUP.size());
				for (int i = 0; i < regDUP.size(); i++) {
					if (this.coverageDUPCheckingTable.containsKey(regDUP.get(i))) {
						nowDUP.add(regDUP.get(i));
					}
				}
				System.out.println("Path = " + clgp.toGetPathInfo());
				for (DUP p : nowDUP) {
					System.out.println(p.DUP2Str());
				}
			}
			// -------

			Set<CLGEdge> intersectionalEdge = new HashSet<CLGEdge>();
			for (CLGEdge b : pathObj.getEdges()) {
				if (this.coverageCheckingTable.containsKey(b)) {
					intersectionalEdge.add(b);
				}
			}

			if (nowDUP.size() > 0
					|| (!Main.criterion.equals(Criterion.dcdup) && !Main.criterion.equals(Criterion.dccdup)
							&& !Main.criterion.equals(Criterion.mccdup) && intersectionalEdge.size() > 0)) {
				// 20200916 dai
				// --

				pathObjInfo += "\n" + pathObj.toGetPathInfo() + "\n";

				// 20200916 dai �q�½c���ӹL�Ӫ�
				// �bpathinfo��󤤦L�X�C�����|�bCLG�������
				pathObjInfo += pathObj.getPathObjConsInfo(); // pathObjInfo

				boolean flag = false; // �禡
				ArrayList<String> methodarr = new ArrayList<String>();
				for (int pathi = 0; pathi < pathObj.getPathNodes().size(); pathi++) {
					if (pathObj.getPathNodes().get(pathi) instanceof CLGConstraintNode) {
						CLGConstraintNode clgconsn = (CLGConstraintNode) pathObj.getPathNodes().get(pathi);
						if (clgconsn.getConstraint() instanceof CLGMethodInvocationNode) {
							flag = true;
							CLGMethodInvocationNode methodpath = (CLGMethodInvocationNode) clgconsn.getConstraint();
							methodarr.add(methodpath.getMethodName());
						} else
							continue;
					}
				}
				String clpContent = "";
				if (flag) { // method
					clpContent = clpTranslator.genMethodPathCLP(pathObj, pathnum, className, Main.typeTable);// 20200922
																												// dai
					// DataWriter.writeFile(clpContent, graphClassName, "ECL",
					// pathnum);
					// 715
					DataWriter.writeInfo(clpContent, graphClassName + "_" + pathnum, "ecl",
							DataWriter.testCons_output_path);
					System.out.println(" method 620 ++" + methodarr);
					if (false) {
						/* black-box: clp solves twice. */
						if (clpSolver.solving(graphClassName, pathnum, methodarr)) {
							testClassDatas.add(clpSolver.getTestDataclass());
							pathnum += 1;
						}
						// clpSolver.solving(graphClassName, pathnum,
						// methodarr);
						// if(clpSolver.getTestData()!=null){
						// testDatas.add(clpSolver.getTestData());
						// pathnum+=1;
						// }
					} else {
						/* white-box: clp solves once. */
						// clpSolver.solving((graphClassName), pathnum,
						// methodarr);
						// if(clpSolver.getTestDataclass()!=null){
						// testDatas.add(clpSolver.getTestDataclass());
						// //System.out.println("Obj_pre="+clpSolver.getTestData().getObjPre());
						// //System.out.println("Arg_pre="+clpSolver.getTestData().getArgPre());
						// //System.out.println("Obj_post="+clpSolver.getTestData().getObjPost());
						// //System.out.println("Arg_post="+clpSolver.getTestData().getArgPost());
						// //System.out.println("Reu_val="+clpSolver.getTestData().getRetVal());
						// pathnum+=1;
						// }
						if (clpSolver.solving(graphClassName, pathnum, methodarr)
								&& clpSolver.getTestDataclass() != null) {
							testClassDatas.add(clpSolver.getTestDataclass());
							pathnum += 1;
						}
					}
					System.out.println(pathObj.toGetPathInfo());
				} // ---
				else {
					clpContent = clpTranslator.genPathCLP(pathObj, graphClassName, graphMethodName,
							graphMethodIsConstructor);
					DataWriter.writeInfo(clpContent,
							graphClassName + graphMethodName + "_" + clpTranslator.getPathNumber(), "ecl",
							DataWriter.testCons_output_path);
					/*
					 * if (false) { /* black-box: clp solves twice.
					 * clpSolver.solving(graphClassName, graphMethodName,
					 * clpTranslator.getPathNumber(), 1, "Obj_pre", "Arg_pre", "Obj_post",
					 * "Arg_post", "RetVal"); testDatas.add(clpSolver.getTestData()); } else { /*
					 * white-box: clp solves once. clpSolver.solving(graphClassName,
					 * graphMethodName, clpTranslator.getPathNumber(), 1, "Obj_pre", "Arg_pre",
					 * "Obj_post", "Arg_post", "RetVal"); testDatas.add(clpSolver.getTestData());
					 * System.out.println("clpSolver.getTestData(): " +
					 * clpSolver.getTestData().toString()); }
					 * System.out.println(pathObj.toGetPathInfo());
					 */
					// this.removeCoveredBranches(intersectionalEdge);
				}
				// check testdata
				if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
						|| Main.criterion.equals(Criterion.mccdup)) {
					System.out.println("part 3 ");
					if (clpSolver.getTestDataclass() == null) {
						for (int dupi = 0; dupi < nowDUP.size(); dupi++) {
							int count = (int) this.coverageDUPCheckingTable.get(nowDUP.get(dupi));
							this.coverageDUPCheckingTable.put(nowDUP.get(dupi), (count + 1));
						}
						this.infeasiblePath.add(pathObj);
						this.removeCoveredBranches(intersectionalEdge);
						pathObjInfo += "infeasible\n";
					} else {// sol
						for (int dupi = 0; dupi < nowDUP.size(); dupi++) {
							this.feasible_path.add(pathObj);
							this.feasibledup.add(nowDUP.get(dupi));
							this.coverageDUPCheckingTable.remove(nowDUP.get(dupi));
							this.removeCoveredBranches(intersectionalEdge);
						}
						List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(pathObj.getPathNodes());
						CLGPath clgp = new CLGPath(new_path1, pathObj.toGetPathId());
						saveTDPathDUP += "Path = " + clgp.toGetPathInfo();
						for (DUP p : nowDUP) {
							saveTDPathDUP += "\n" + p.DUP2Str();
						}
						saveTDPathDUP += "\n" + clpSolver.getTestData() + "\n\n";
						pathObjInfo += "feasible\n";
					}
				} else {// control
					if (clpSolver.getTestDataclass() == null) {

						this.infeasiblePath.add(pathObj);
						// this.removeCoveredBranches(intersectionalEdge);
					} else {// sol
						for (int dupi = 0; dupi < intersectionalEdge.size(); dupi++) {
							this.feasible_path.add(pathObj);
							// this.feasibledup.add(nowDUP.get(dupi));
							// this.coverageDUPCheckingTable.remove(nowDUP.get(dupi));
							this.removeCoveredBranches(intersectionalEdge);
						}
					}
					// this.removeCoveredBranches(intersectionalEdge);
					// this.feasible_path.add(pathObj);
					pathObjInfo += "feasible\n";
				}
			}
		}

		String content = "";
		System.out.println("\n");
		saveTDPathDUP += "\n\nfeasiblepathsize = " + feasible_path.size() + "\n"; //
		System.out.println("feasiblepathsize = " + feasible_path.size());
		for (CLGPath p : feasible_path) {
			System.out.println(p.toGetPathInfo());
			List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(p.getPathNodes());
			CLGPath clgp = new CLGPath(new_path1, p.toGetPathId());
			saveTDPathDUP += clgp.toGetPathInfo() + "\n";
		}

		if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
				|| Main.criterion.equals(Criterion.mccdup)) {
			System.out.println("part 4 ");
			ArrayList<DUP> indup = new ArrayList<DUP>();
			for (Object dup : this.coverageDUPCheckingTable.keySet()) {
				if (dup instanceof DUP) {
					if (!indup.contains((DUP) dup))
						indup.add((DUP) dup);
				}
			}
			for (int i = 0; i < indup.size(); i++) {
				if (!this.infeasibledup.contains(indup.get(i)))
					this.infeasibledup.add(indup.get(i));
				if (this.coverageDUPCheckingTable != null) {
					if (this.coverageDUPCheckingTable.containsKey(indup.get(i)))
						this.coverageDUPCheckingTable.remove(indup.get(i));
				}
			}
			saveTDPathDUP += "\n\nfeasibledup size = " + feasibledup.size();//
			System.out.println("feasibledup size = " + feasibledup.size());
			for (int i = 0; i < feasibledup.size(); i++) {
				saveTDPathDUP += "\n" + feasibledup.get(i).DUP2Str();//
			}

			saveTDPathDUP += "\n\ninfeasibledup size = " + infeasibledup.size();//
			System.out.println("infeasibledup size = " + infeasibledup.size());
			for (int i = 0; i < infeasibledup.size(); i++) {
				saveTDPathDUP += "\n" + infeasibledup.get(i).DUP2Str();//
			}
		}

		saveTDPathDUP += "\n\ninfeasiblepath size = " + infeasiblePath.size() + "\n";//
		System.out.println("infeasiblepath size = " + infeasiblePath.size());
		for (int i = 0; i < infeasiblePath.size(); i++) {
			System.out.println(infeasiblePath.get(i).toGetPathInfo());
			List<CLGNode> new_path1 = clgPathEnumerator.filterConstraintNode(infeasiblePath.get(i).getPathNodes());
			CLGPath clgp = new CLGPath(new_path1, infeasiblePath.get(i).toGetPathId());
			saveTDPathDUP += clgp.toGetPathInfo() + "\n";
		}

		// 20210325 dai �s�W
		ArrayList<ArrayList<ArrayList<Object>>> tdSDSplit = getTestDataClassLevelSplit(testClassDatas);

		for (int tdIndex = 0; tdIndex < testClassDatas.size(); tdIndex++) {
			content += "OBJ_PRE = " + tdSDSplit.get(0).get(tdIndex) + ", ARG_PRE = " + tdSDSplit.get(1).get(tdIndex)
					+ ",OBJ_POST = " + tdSDSplit.get(2).get(tdIndex) + ",ARG_POST = " + tdSDSplit.get(3).get(tdIndex)
					+ ", RETVAL = " + tdSDSplit.get(4).get(tdIndex) + "\n\n";
		}

//		for (TestDataClassLevel td : testClassDatas) {
//			content += "OBJ_PRE = " + td.getObjPre() + ", ARG_PRE = " + td.getArgPre() + ",OBJ_POST = "
//					+ td.getObjPost() + ",ARG_POST = " + td.getArgPost() + ", RETVAL = " + td.getRetVal() + "\n\n";
//		}

		DataWriter.writeInfo(content, graphClassName + graphMethodName, "txt", DataWriter.testData_output_path);

		DataWriter.writeInfo(saveTDPathDUP, graphClassName + graphMethodName + "TryTypeTable", "java",
				DataWriter.output_folder_path, "TDPathDUP");
		// saveTDPathDUP

		pathObjInfo = pathObjInfo.replaceAll("[\\(\\)]", "");
		pathObjInfo = pathObjInfo.replaceAll("CLGStartNode", "[0]");
		pathObjInfo = pathObjInfo.replaceAll("CLGEndNode", "[-1]");
		DataWriter.writeInfo(pathObjInfo, graphClassName + graphMethodName + "_pathInfo", "txt",
				DataWriter.testPath_output_path);

		return this.testClassDatas;
	}

	private ArrayList<ArrayList<ArrayList<Object>>> getTestDataClassLevelSplit(List<TestDataClassLevel> testDataSD) {

		ArrayList<ArrayList<Object>> returnObjPre = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> returnArgPre = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> returnObjPost = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> returnArgPost = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> returnResult = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> returnException = new ArrayList<ArrayList<Object>>();

		for (TestDataClassLevel tdSD : testDataSD) {
			ArrayList<TestData_New> methodCallStep = tdSD.getMethodCallTestData();

			ArrayList<Object> objPre = new ArrayList<Object>();
			ArrayList<Object> argPre = new ArrayList<Object>();
			ArrayList<Object> objPost = new ArrayList<Object>();
			ArrayList<Object> argPost = new ArrayList<Object>();
			ArrayList<Object> result = new ArrayList<Object>();
			ArrayList<Object> exception = new ArrayList<Object>();

			for (TestData_New td : methodCallStep) {
				objPre.add(td.getObjPre());
				argPre.add(td.getArgPre());
				objPost.add(td.getObjPost());
				argPost.add(td.getArgPost());
				result.add(td.getRetVal());
				exception.add(td.getException());
			}

			returnObjPre.add(objPre);
			returnArgPre.add(argPre);
			returnObjPost.add(argPost);
			returnArgPost.add(objPost);
			returnResult.add(result);
			returnException.add(exception);

		}

		ArrayList<ArrayList<ArrayList<Object>>> returnList = new ArrayList<ArrayList<ArrayList<Object>>>();

		returnList.add(returnObjPre);
		returnList.add(returnArgPre);
		returnList.add(returnArgPre);
		returnList.add(returnArgPost);
		returnList.add(returnResult);
		returnList.add(returnException);

		return returnList;
	}

	public void selectCLGGraphCriteria(CLGGraph graph, Criterion criterionState) {
		/* designed */
		CLGCriterionTransformer transformer = new CLGCriterionTransformer();
		Criterion criterion = null;
		switch (criterionState) {
		case dc:
		case dcdup:
			this.targetCLG = graph;
			break;
		case dcc:
		case dccdup:
			this.targetCLG = transformer.CriterionTransformer(this.targetCLG, criterion.dcc);
			break;
		case mcc:
		case mccdup:
			this.targetCLG = transformer.CriterionTransformer(this.targetCLG, criterion.mcc);
			break;
		}

	}

	public boolean meetCriterion() {

		for (Object edgeTimes : this.coverageCheckingTable.values()) {
			if ((Integer) edgeTimes < 5000) { // edgeTimes �]�w�̾�
				return false;
			} else
				continue;
		}
		// ----
		if (Main.criterion.equals(Criterion.dcdup) || Main.criterion.equals(Criterion.dccdup)
				|| Main.criterion.equals(Criterion.mccdup))
			System.out.println("(Integer) DUPTimes");
		for (Object DUPTimes : this.coverageDUPCheckingTable.values()) {

			if ((Integer) DUPTimes < 5) {// ?
				return false;
			} else
				continue;
		}
		// --
		return true;
	}

	public boolean meetCriterion(String criterion) {

		for (Object edgeTimes : this.coverageCheckingTable.values()) {
			if ((Integer) edgeTimes < 6000) {// ??
				return false;
			} else
				continue;
		}
		// ----
		if (criterion.equals("dcdup") || criterion.equals("dccdup") || criterion.equals("mccdup"))
			System.out.println("(Integer) DUPTimes");
		for (Object DUPTimes : this.coverageDUPCheckingTable.values()) {

			if ((Integer) DUPTimes < 5) {// ?
				return false;
			} else
				continue;
		}
		// --
		return true;
	}

	public void removeCoveredBranches(Set<CLGEdge> edges) {
		for (CLGEdge e : edges) {
			this.coverageCheckingTable.remove(e);
			System.out.println("this.unCoveredBranches size " + this.coverageCheckingTable.size());
		}
	}

	public int factorial(int number) {
		if (number == 1)
			return number;
		else
			return number * factorial(number - 1);
	}

	public void quickSort(int array[], int low, int high) {
		int mid = (low + high) / 2;
		int left = low;
		int right = high;
		int pivot = array[mid];
		while (left <= right) {
			while (array[left] < pivot)
				left++;
			while (array[right] > pivot)
				right--;
			if (left <= right) {
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				left++;
				right--;
			}
		}
		// Recursion on left and right of the pivot
		if (low < right)
			quickSort(array, low, right);
		if (left < high)
			quickSort(array, left, high);
	}

	public CLGPath SIPBinarySearch(CLGPath fullPath, String graphClassName, String graphMethodName, int pathNo,
			Boolean graphIsConstructor) throws CloneNotSupportedException {
		// 20201025�NSIP�����ǤJ������|�אּ�ǤJ���arrayList pathCon
		int low = 0, mid;
		int high = fullPath.getPathNodes().size() - 1;
		Boolean solved;
		CLGPath halfPath = fullPath.clone();
		CLGNode endNode = fullPath.getLastNode();

		int SIPNo = 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (mid == 0) {
				infeasibleTilStartNode = true;
				break;
			}

			halfPath = fullPath.clone();
			halfPath.cutPathNode(mid);
			halfPath.addNode(endNode);

			// ���նȭק�clp���e�A���i�歫���ഫ
			String tempCLP = clpTranslator.genPathCLP(halfPath, className, methodName, graphIsConstructor);

			DataWriter.writeInfo(tempCLP, graphClassName + graphMethodName + "SIP" + pathNo + "_" + SIPNo, "ecl",
					DataWriter.testMethodCLP_output_path, "eclSIP");
			solved = clpSolver.solvingInfeasiblePath(graphClassName, graphMethodName, pathNo + "_" + SIPNo++, 1,
					graphIsConstructor, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal");
			halfPath.RemoveLastNode();

			if (solved) {
				low = mid + 1;
			} else if (low == mid) {
				if (mid == 0)
					infeasibleTilStartNode = true;
				return halfPath;
			} else {
				high = mid;
			}
		}
		return halfPath;
	}

	// 20200520 ���նȭק�clp���e�A���i�歫���ഫ
	public CLGPath SIPSearch(String clpContent, CLGPath fullPath, String graphClassName, String graphMethodName,
			int pathNo, Boolean graphIsConstructor, HashMap<Integer, Stack<String>> nodeCLPContent,
			HashMap<String, Integer> variableSet) throws CloneNotSupportedException {
		int low = 0, mid;
		int high = fullPath.getPathNodes().size() - 1;
		Boolean solved;
		CLGPath SIP = fullPath;

		int SIPNo = 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (mid == 0) {
				infeasibleTilStartNode = true;
				break;
			}

			HashMap<String, Integer> variableSetAfterDel = (HashMap) variableSet.clone();
//				�����ഫ�ﰵ�ק�
			String tempCLP = clpTranslator.getTempSIPCLP(fullPath, clpContent, nodeCLPContent, mid,
					variableSetAfterDel);

			String stateBridgingStr = stateBridging(Main.typeTable.get(graphClassName, null).getSymbolTable(),
					graphMethodName, variableSet, variableSetAfterDel);
			tempCLP = tempCLP.replace(CLPTranslator.SIPInsertStateBridgingFlag, stateBridgingStr);

			DataWriter.writeInfo(tempCLP,
					graphClassName + Utility.titleToUppercase(graphMethodName) + "SIP" + pathNo + "_" + SIPNo, "ecl",
					DataWriter.testMethodCLP_output_path + "/eclSIP",
					graphClassName + Utility.titleToUppercase(graphMethodName) + "SIP");
			solved = clpSolver.solvingInfeasiblePath(graphClassName, graphMethodName, "SIP" + pathNo + "_" + SIPNo++, 1,
					graphIsConstructor, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal");

			if (solved) {
				low = mid + 1;
			} else if (low == mid) {
				if (mid == 0)
					infeasibleTilStartNode = true;
				SIP.cutPathNode(mid);
				System.out.println(SIP.toGetPathInfo());
				return SIP;
			} else {
				high = mid;
			}
		}
		System.out.println();
		return SIP;
	}

	public List<String> findConflictConstraint(CLGPath pathObj, CLGNode endNode, String graphClassName,
			String graphMethodName, int pathNo, Boolean graphIsConstructor) throws CloneNotSupportedException {
		List<String> tempList = new ArrayList<>();
		Boolean solved;
		String temp;
		CLGPath tempPath = pathObj.clone();

		int SIPNo = 1;
		for (int i = 2; i < pathObj.getPathNodes().size(); i++) {
			temp = "";
			tempPath = pathObj.clone();
			tempPath.RemoveNodeByNum(i);
			tempPath.addNode(endNode);

			String tempCLP = clpTranslator.genPathCLP(tempPath, graphClassName, graphMethodName, graphIsConstructor);
			DataWriter.writeInfo(tempCLP, graphClassName + graphMethodName + "SIP" + pathNo + "_" + SIPNo, "ecl",
					DataWriter.testMethodCLP_output_path, "eclSIP");
			solved = clpSolver.solvingInfeasiblePath(graphClassName, graphMethodName, pathNo + "_" + SIPNo, 1,
					graphIsConstructor, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal");

			if (solved) {
				CLGConstraintNode cons = (CLGConstraintNode) pathObj.getPathNodes()
						.get(pathObj.getPathNodes().size() - i);
				temp += cons.toCLPInfo(new HashMap<>(),new HashSet<String>()).replace("#", "") + ",";
				tempList.add(temp);
			}
		}

		return tempList;
	}

	// 20200520 ���նȭק�clp���e�A���i�歫���ഫ
	public List<CLGConstraintNode> CCSSearch(String clpContent, CLGPath SIP, CLGNode endNode, String graphClassName,
			String graphMethodName, int pathNo, Boolean graphIsConstructor,
			HashMap<CLGConstraintNode, String> nodeCLPContent) throws CloneNotSupportedException {
		List<CLGConstraintNode> CCS = new ArrayList<>();
		Boolean solved;

//		for(int i=2; i<SIP.getPathNodes().size(); i++) {
//			temp = "";
//			
//			String tempCLP = clpTranslator.getTempCCSCLP(SIP, SIP.getPathNodes().size()-i);
//			
////			DataWriter.writeInfo(tempCLP, graphClassName + graphMethodName + "SIP" + pathNo,
////					"ecl", DataWriter.output_folder_path, "eclSIP");
//			DataWriter.writeInfo(tempCLP, graphClassName + graphMethodName + "SIP" + pathNo,
//					"ecl", "Examples/", "eclSIP");
//			solved = clpSolver.solvingInfeasiblePath(graphClassName, graphMethodName, pathNo, 1, graphIsConstructor,
//					graphRetType, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal");
//			
//			if(solved) {
//				CLGConstraintNode cons = (CLGConstraintNode) SIP.getPathNodes().get(SIP.getPathNodes().size()-i);
//				temp += cons.toCLPInfo() + ",";
////				temp += clgPathEnumerator.getCLGht().get(cons) +",";
//				CCS.add(temp);
//			}
//		}

//		20200808 CCS��k�ק�
		int SIPNo = 1;
		for (int i = 2; i < SIP.getPathNodes().size(); i++) {
			CLGNode n = SIP.getPathNodes().get(SIP.getPathNodes().size() - i);
			if (n instanceof CLGConstraintNode) {
				String tempCLP = clpTranslator.getTempCCSCLP(SIP, SIP.getPathNodes().size() - i, nodeCLPContent);

				DataWriter.writeInfo(tempCLP,
						graphClassName + Utility.titleToUppercase(graphMethodName) + "CCS" + pathNo + "_" + SIPNo,
						"ecl", DataWriter.testMethodCLP_output_path + "/eclSIP",
						graphClassName + Utility.titleToUppercase(graphMethodName) + "SIP");
				solved = clpSolver.solvingInfeasiblePath(graphClassName, graphMethodName, "CCS" + pathNo + "_" + SIPNo,
						1, graphIsConstructor, "Obj_pre", "Arg_pre", "Obj_post", "Arg_post", "RetVal");

				if (solved) {
					CCS.add((CLGConstraintNode) n);
				} else {
					clpTranslator.setTempCLPByCCS();
				}
			}
		}

		return CCS;
	}

	public void preRemovalInfeasiblePathManager(CLGPath pathObj, String graphClassName, String graphMethodName,
			int pathNo, Boolean graphIsConstructor) throws CloneNotSupportedException {
		int pathNoFix = pathNo - (printFail ? 1 : 0);
		infeasibleTilStartNode = false;
		clpTranslator.bodyCountFix_SIP();
		String clpContent = clpTranslator.genPathCLP(pathObj, graphClassName, graphMethodName, graphIsConstructor);
		HashMap<Integer, Stack<String>> nodeCLPContent = clpTranslator.getNodeCLPContent();// ����`�I������CLP

		// �̵u���i�����|�j�M
		System.out.println("\n" + pathObj.getPathNodes());
		pathObj = SIPSearch(clpContent, pathObj, graphClassName, graphMethodName, pathNoFix, graphIsConstructor,
				nodeCLPContent, clpTranslator.getVariableSet());
		System.out.println("\n" + pathObj.getPathNodes());

		String SIP = "";
		List<CLGNode> SIPNodes = null;

		if (infeasibleTilStartNode) {
//			���N��SIP��JSIPNodesList��
			SIP = pathObj.toGetPathInfo() + "\n";
			pathObjInfo += SIP + "This path will not enter SIP check table!\n";
		} else {
			SIPNodes = pathObj.getPathNodes();

//			test
			String temp2 = "";
			List<CLGConstraintNode> tempCon = new ArrayList<>();
			List<CLGConstraintNode> CCSConsList = new ArrayList<>();

//			���Ĭ𭭨��
//			CCSConsList = CCSSearch(clpContent, pathObj, endNode, graphClassName, graphMethodName, pathNoFix,
//					graphIsConstructor, graphRetType, nodeCLPContent);

			if (CCSConsList.size() != 0) {
				// �bpathinfo��󤤬���CCS�T��
				pathObjInfo += "CCS: ";
				for (CLGConstraintNode conflictNode : CCSConsList) {
					tempCon.add(conflictNode);
					if (nodeCLPContent.containsKey(conflictNode)) {
						pathObjInfo += nodeCLPContent.get(conflictNode) + ", ";
					}
				}

//				SIP�̫�@�Ӹ`�I�A�����[�J
				int tempI = 1;
				while (!(pathObj.getPathNodes()
						.get(pathObj.getPathNodes().size() - tempI) instanceof CLGConstraintNode))
					tempI++;
				CLGConstraintNode cons2 = (CLGConstraintNode) pathObj.getPathNodes()
						.get(pathObj.getPathNodes().size() - tempI);
				if (nodeCLPContent.containsKey(cons2)) {
					pathObjInfo += nodeCLPContent.get(cons2) + ", ";
				}
				tempCon.add(cons2);

				conflictConstraints.add(tempCon);

				pathObjInfo += "\n";
			}
			// �bpathinfo��󤤬���SIP�T��
			SIP = "SIP: " + pathObj.toGetPathInfo() + "\n";
			pathObjInfo += SIP;

//			pathQueueInfo += "CCS constraints:";
//			for(CLGNode n: CCSConsList)
//				SIPPathInfo += n.getOriginalConsName() + ", ";
//			SIPPathInfo += "\n";
//			SIPPathInfo += "*************\n";
		}

		// �w���h��
		Queue<LinkedList<CLGNode>> path_queue = clgPathEnumerator.getQueue();
		Queue<LinkedList<CLGNode>> removeQueue = new LinkedList<>();

		for (LinkedList<CLGNode> unfinishPath : path_queue) {

//			SIP
			if (SIPNodes != null && containsSIP(SIPNodes, unfinishPath)) {
				removeQueue.add(unfinishPath);
			}

			// 20210813 CCS�b�j��i�঳���D
			// 20200808 �קאּ�ȧP�_CCS
//			for (List<CLGConstraintNode> subset : conflictConstraints) {
//				int size = subset.size();
//				int count = 0;
//				for (CLGConstraintNode s : subset) {
//					for (CLGNode node : unfinishPath) {
//						if(node instanceof CLGConstraintNode) {
//							if (s.equals(node)) {
//								count++;
//							}
//						}
//					}
//				}
//				if (count == size) {
//					removeQueue.add(unfinishPath);
//				}
//			}
		}
		clgPathEnumerator.removePathQueue(removeQueue);
	}

//	public Boolean matchConflictConstraints(CLGPath pathObj) {
//		Map<String, Integer> map = new HashMap<>();
//		map = pathObj.getPathByMap(clgPathEnumerator.getCLGht());
//
//		for (List<String> subset : conflictConstraints) {
//			int size = subset.size();
//			int count = 0;
//			Integer check = 0;
//			for (String s : subset) {
//				check = map.get(s);
//				if (check != null) {
//					count++;
//				}
//			}
//			if (count == size) {
//				return true;
//			}
//		}
//
//		return false;
//	}

	// �T�{�Ӹ��|�_�����л\��
	public Boolean isIncreaseCoverage(Set<CLGEdge> edges) {
		Boolean flag = false;

		for (CLGEdge e : edges) {
			if (this.coverageCheckingTable.containsKey(e)) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	private String getPathObjConsInfo(CLGPath pathObj) {
		String pathConsraintStr = "";
		pathConsraintStr += "\n" + pathObj.toGetPathInfo() + "\n";
		// �bpathinfo��󤤦L�X�C�����|�bCLG�������
		pathConsraintStr += "CLG Constraints:\n"; // pathObjInfo �L�X���ɮת��r��

		// 2020/3/3 constrain info.
		for (int i = 0; i < pathObj.getPathNodes().size(); i++) {
			if (pathObj.getPathNodes().get(i) instanceof CLGConstraintNode) {
				CLGConstraintNode cons = (CLGConstraintNode) pathObj.getPathNodes().get(i);
				pathConsraintStr += cons.getConstraint().getOriginalConName() + "\n";
			}
		}
		return pathConsraintStr + "\n"; // pathObjInfo �L�X���ɮת��r��
	}

	private String getPathIterator(int originalPathNo, CLGPath pathObj) {
		String tmpItStr = "pathNo:" + originalPathNo + "\t" + pathObj.toGetItInfo();
		return tmpItStr + "\n";
	}

	private boolean containsSIP(List<CLGNode> SIP, List<CLGNode> path) {
		if (SIP.size() > path.size())
			return false;
		int connectNodeCount = 0;
		for (int i = 0; i < SIP.size(); i++) {
			while (path.get(i + connectNodeCount) instanceof CLGConnectionNode)
				connectNodeCount++;
			if (!path.get(i + connectNodeCount).equals(SIP.get(i)))
				return false;
		}
		return true;
	}

	private String stateBridging(SymbolTable sym, String methodName, HashMap<String, Integer> variableSet,
			HashMap<String, Integer> variableSetAfterDel) {
		String retStr = "";
		// att
		for (String attName : sym.getAttribute().keySet()) {
			int flag = 1, flagDel = 1;
			if (variableSet.containsKey("Self_pre_" + attName)
					&& variableSetAfterDel.containsKey("Self_pre_" + attName)) {
				flag = variableSet.get("Self_pre_" + attName);
				flagDel = variableSetAfterDel.get("Self_pre_" + attName);
			}
			if (flag != 1 && flag != flagDel) {
				retStr += "\nSelf_pre_" + attName + "_" + flag + "=";
				retStr += "Self_pre_" + attName + (flagDel == 1 ? "" : "_" + flagDel) + ",";
			}
		}
		// arg
		for (String argName : sym.getMethod().get(methodName).getArgument().keySet()) {
			int flag = 1, flagDel = 1;
			if (variableSet.containsKey(argName) && variableSetAfterDel.containsKey(argName)) {
				flag = variableSet.get(argName);
				flagDel = variableSetAfterDel.get(argName);
			}
			if (flag != 1 && flag != flagDel) {
				retStr += "\n" + argName + "_pre_" + flag + "=";
				retStr += argName + "_pre_" + (flagDel == 1 ? "" : flagDel) + ",";
			}
		}

		return retStr;
	}
}
