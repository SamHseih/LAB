package sqlschemaAnalyzer;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;

import java.io.*;
import java.util.ArrayList;

public class tempPath{
	
	public CLGGraph tempFkPath;
	
	public ArrayList<String> foreignTableName = new ArrayList<String>();
	public ArrayList<ArrayList<String>> foreignColumn = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> foreignColumnType = new ArrayList<ArrayList<String>>();
	
}