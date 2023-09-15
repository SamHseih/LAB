package tcgenplugin_2.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.WorkbenchPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONException;

import com.parctechnologies.eclipse.EclipseException;

import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.exe.main.*;
import ccu.pllab.tcgen.launcher.BlackBoxLauncher;
import ccu.pllab.tcgen.pathCLP2data.CLP2Data;
import ccu.pllab.tcgen.pathCLP2data.CLP2DataFactory;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.transform.AST2CLG;
import ccu.pllab.tcgen.transform.CLG2Path;
import ccu.pllab.tcgen.transform.OCL2AST;
import sqlschemaAnalyzer.sqlTcgen;
import ccu.pllab.tcgen.AbstractSyntaxTree.SymbolTable;
import ccu.pllab.tcgen.DataWriter.DataWriter;


public class SqlHandler extends AbstractHandler {
	public static String CurrentEditorProjectPath; //�s����e���Editor���|
	public static String CurrentEditorName;
	public static String CurrentProjName;
	public static String ClassName_TestModel_Coverage;
	public static String coverageCriteria;
	public static AbstractSyntaxTreeNode oclAst;
	public static SymbolTable symbolTable;
	public static BlackBoxLauncher blackBoxTest;
	private File ocl,classUml;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
			
		CurrentEditorName = getCurrentFileRealPath().getName();
		CurrentEditorName = getFileNameNoEx(CurrentEditorName);
		String CurrentEditorPath = getCurrentFileRealPath().getLocation().toOSString();
		CurrentEditorProjectPath = CurrentEditorPath.substring(0, CurrentEditorPath.lastIndexOf("\\src\\"+CurrentEditorName));
		coverageCriteria = "";
		
		//��������л\�з�
		try {
			coverageCriteria = event.getCommand().getParameter("com.eclipse-tips.commands.CoverageCriteriaParameter").getName();
			ClassName_TestModel_Coverage = CurrentEditorName + "_BlackBox_"+coverageCriteria;
			System.out.println("Coverage "+coverageCriteria);
		} catch (NotDefinedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("SQL!");
		System.out.println(CurrentEditorName);
		System.out.println(CurrentEditorProjectPath);
		
		
		//�w�]�����޿�ϸ��|�Ѫk��DCC�з�
		try {
			//�ثe�u��AICC�ഫ�k
			//�Q�Ψ�L��Ʈw���ռзǪ��� �ݧ�ﭭ���޿�ϫغc��� �õ����p�ק� �sĶ��g4���
			if (coverageCriteria.equals("AICC")) {
				sqlTcgen sqltcgen = new sqlTcgen(); 
		    	sqltcgen.genSQLtc(CurrentEditorProjectPath, CurrentEditorName, "DCC");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static IFile getCurrentFileRealPath(){
        IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

        IWorkbenchPage page = win.getActivePage();
        if (page != null) {
            IEditorPart editor = page.getActiveEditor();
            if (editor != null) {
                IEditorInput input = editor.getEditorInput();
                if (input instanceof IFileEditorInput) {
//                    return ((IFileEditorInput)input).getFile().getLocation().toOSString();
                    return ((IFileEditorInput)input).getFile();
                }
            }
        }
        return null;
	}
	
	public static String getFileNameNoEx(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf(".");   
            if ((dot >-1) && (dot < (filename.length()))) {   
                return filename.substring(0, dot);   
            }   
        }   
        return filename;   
    } 
	
//	��ԣ�n���o�Ө禡��? �]�����ӾǪ���@���ܼƳq�q�Υ����ܼƶǭȡA�ϥΧ��F�S��reset�A��������U�@�ӴN�|�����D
//	���D�]�A��������A�Q����P�Q���쪺
//	�n���~���~�ݭn�ϥγ�!      ���w�Ԥh�d
	public void reset() {
		Main.invCLP = "";
		Main.ifCLP = "";
		Main.isArraylist=false;  
		Main.isConstructor=false;
		Main.symbolTable = null;
		Main.doArray = false;
		Main.issort=false;
		Main.twoD=false;
		//���եh�����ư���X����bug
		Main.output_folder_path="";
		Main.boundary_analysis=false;
		Main.criterion=null;
		Main.ast=null;
		Main.clg=null;
		Main.attribute=null;
		Main.className="";
		Main.msort=false;
		Main.issort=false;
		Main.arrayMap=new HashMap<String, Integer>();
		Main.indexMap=new HashMap<String, Integer>();
		Main.bodyExpBoundary= false;
		Main.iterateBoundary=null;
		Main.conNodeiterateBoundary=null;
		Main.TestType="";
		Main.changeBoundary=false;
		Main.doArray=false;
		Main.boundaryhavesolution=false;
		//CLP2Data.Destroy();
		//CLP2DataFactory.instance= null;
		//refresh project
		for(IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()){
		    try {
				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
