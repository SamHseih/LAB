/**Define a grammar called Ocl   
 * Write: 王建瓏 
 * 參考:http://www.yancy.org/education/phd/links/ocl-grammar-01b.pdf
 * */
grammar Ocl;

@header {//這部分是就是java裏頭import的部分，因為.g4檔與OclParser.java是連動關係，所以不能直接在OclParser.java增添import，一旦存檔就會被洗掉，因此如果要import的時候，在@header裏頭加
	import ccu.pllab.tcgen.AbstractSyntaxTree.*;
	import ccu.pllab.tcgen.SymbolTable.*;
	import ccu.pllab.tcgen.AbstractType.*;
	import ccu.pllab.tcgen.CBTCGUtility.Utility;
	import ccu.pllab.tcgen.exe.main.*;
	import java.util.ArrayList;
	import java.util.Set;
	
}

@members {
	public SymbolTable symbolTab = Main.symbolTable;
	public String visitMethod;
	public LocalVarScope varScope = new LocalVarScope("", null);
}

/*code 寫法
 *仍是一條線讀取，只是為了美觀
 *某些語意規則底下搭配註解是特例，方便給初學者簡單觀念
 *困難的特例仍符合語法規則，但請自己推敲
*/
packageDeclarationCS returns [PackageExp astRoot]
:
	{Main.localSymbolTable.add(0,Main.symbolTable);}

	'package' paNa = packageName
	{$astRoot=new PackageExp($paNa.name);}

	oclExp = oclExpressions
	{//連接所有context
		for(AbstractSyntaxTreeNode node :$oclExp.contextNode)
		($astRoot).addTreeChildNode(node);
	}

	'endpackage'
	{Main.localSymbolTable.remove(0);}

;
/*packageDeclarationCS  
 *example:
 *  package tcgen
 *  (Content)
 *  endpackage
 */
packageName returns [String name]
: //name是package name
	phNa = pathName
	{$name=$phNa.name;}

; //ex: packageName=>tcgen

pathName returns [String name]
: //package name
	NAME
	{$name=$NAME.text;}

	(
		'::' NAME
		{$name+="::"+$NAME.text;}

	)?
;

oclExpressions returns [ArrayList<AbstractSyntaxTreeNode> contextNode]
: //所有context 
	cons = constraints
	{($contextNode)=($cons.contextNode);}

;
/*this ocl file includes many context
 * context 1....
 * context 2...
 * ...
 * context n...
 */
constraints returns [ArrayList<AbstractSyntaxTreeNode> contextNode]
: //單獨一個context
	con = constraint
	{
			$contextNode=new ArrayList<AbstractSyntaxTreeNode>();
			($contextNode).add($con.contextNode);
		}

	cons = constraints //這裡可以呼叫多個constrain	
	{($contextNode).addAll($cons.contextNode);}

	|
	{$contextNode=new ArrayList<AbstractSyntaxTreeNode>();}

; //或許可省略

constraint returns [AbstractSyntaxTreeNode contextNode]
:
	{Main.localSymbolTable.add(0,new SymbolTable());}

	coDe = contextDeclaration
	(
		sType = stereotype
		(
			NAME
			{($sType.stereo).setException($NAME.text);}

		)? ':' oclExp = oclExpression
		{
				($sType.stereo).addTreeChildNode($oclExp.node);
				if($coDe.node instanceof OperationContext)
				{
					((OperationContext)($coDe.node)).setStereoType($sType.stereo);
					//if($co.stereo!=null)
					//((OperationContext)($coDe.node)).setStereoType($co.stereo);
					
					
				}
				else{
					((ClassifierContext)($coDe.node)).setStereoType($sType.stereo);
				}
			}

	)+ co = content
	{
				
				if($coDe.node instanceof OperationContext)
				{
				
					if($co.stereo!=null)
					((OperationContext)($coDe.node)).setStereoType($co.stereo);
					
				}
				Main.localSymbolTable.remove(0);
				varScope = new LocalVarScope("", null);
				$contextNode=$coDe.node;
			}
//|('def' NAME? ':' letExpression*)

;
/*
 *context method1(...)
 * inv/pre/post  exception:
 * ..............
 */
content returns [StereoType stereo]
:
	sType = stereotype NAME? ':' oclExp = oclExpression
	{
		 	$stereo =$sType.stereo;
			($stereo).addTreeChildNode($oclExp.node);
		}
//|('def' NAME? ':' letExpression*) content

	|
;

contextDeclaration returns [AbstractSyntaxTreeNode node]
:
	'context'
	(
		opCo = operationContext
		{$node=$opCo.operation;} //是method

		| clCo = classifierContext
		{$node=$clCo.classifier;} //是classifier

	)
;

stereotype returns [StereoType stereo]
:
	'pre'
	{$stereo=new StereoType("precondition");}

	| 'post'
	{$stereo=new StereoType("postcondition");}

	| 'inv'
	{$stereo=new StereoType("invariant");}

;

operationContext returns [OperationContext operation]
:
	NAME '::' opNa = operationName
	{
			$operation=new OperationContext($NAME.text,$opNa.name);
			visitMethod = $opNa.name;					//目前走訪methodName
			varScope = new LocalVarScope(visitMethod, null);	//目前走訪的scope, 設定走訪的method
			
			for(VariableRefExp locVar : Main.localSymbolTable.get(0).getLocalVariable().values()){
				varScope.addVariable( new VariableToken( locVar.getVariable(), locVar.getType()));
			}
		}

	'(' fpl = formalParameterList [$opNa.name]
	{($operation).setParameters($fpl.list);}

	')'
	(
		':' rt = returnType
		{($operation).setReturnType($rt.reType);
			VariableRefExp var = new VariableRefExp("result");
			var.setType($rt.reType);
			varScope.addVariable( new VariableToken( var.getVariable(), var.getType()));
			Main.localSymbolTable.get(0).addLocalVariable(var);
		}

	)?
;

classifierContext returns [ClassifierContext classifier]
:
	(
		NAME ':' NAME
	)
	| NAME
	{$classifier=new ClassifierContext($NAME.text);}

;

operationName returns [String name]
:
	NAME
	{$name=$NAME.text;}

	| '='
	| '+'
	| '-'
	| '<'
	| '<='
	| '>='
	| '>'
	| '/'
	| '*'
	| '<>'
	| 'implies'
	| 'not'
	| 'or'
	| 'xor'
	| 'and'
;

returnType returns [String reType]
:
	ts = typeSpecifier
	{$reType=$ts.value;}

;
/*
 * Context Date:function(parameter):Type
 * pre/post/inv (Exception)? :
 * (content)
 */
formalParameterList [String opNa] returns [ArrayList<PropertyCallExp> list]
: //這裡描述operation裏頭的參數
	{$list=new ArrayList<PropertyCallExp> ();}

	(
		NAME ':' tySpec = typeSpecifier fpls = formalParameterLists [$opNa]
		{
				VariableRefExp var=new VariableRefExp($NAME.text);
				var.setType($tySpec.value);
				Main.localSymbolTable.get(0).addLocalVariable(var);
				($list).add(var);
				($list).addAll($fpls.list);
			}

	)?
;

formalParameterLists [String opNa] returns [ArrayList<PropertyCallExp> list]
: //這個list只容許variableExp，因為operation裏頭是variable
	',' NAME ':' tySpec = typeSpecifier fpls = formalParameterLists [$opNa]
	{
		$list=new ArrayList<PropertyCallExp> ();
		VariableRefExp var=new VariableRefExp($NAME.text);
		var.setType($tySpec.value);
		Main.localSymbolTable.get(0).addLocalVariable(var);
		($list).add(var);
		($list).addAll($fpls.list);
	}

	|
	{$list=new ArrayList<PropertyCallExp> ();}

;

typeSpecifier returns [String value , VariableType type]
: //這裡的value是指什麼type
	sts = simpleTypeSpecifier
	{$value=$sts.name; $type = $sts.type;}

	| col = collectionType
	{$value=$col.name;$type = $col.type;}

;

collectionType returns [String name, VariableType type]
:
	sts = simpleTypeSpecifier
	{
		$name = $sts.name;
		$type = $sts.type;
		
	}

	(
		'(' simpleType = typeSpecifier ')'
		{
		$name +=  "(" + $simpleType.value + ")";
		((CollectionType)$type).setElement($simpleType.type);
		((CollectionType)$type).setTypeNameAndID($name);
		Main.typeTable.add($type.clone());
	}

	)?
	| sts = simpleTypeSpecifier
	{$name=$sts.name;$type = $sts.type;int i = 0;}

	(
		'[]'
		{ 
		 i++;
		}

	)+
	{
		for(;i>=1;i--){
			$type = new ArrayType($type.clone());
			Main.typeTable.add($type.clone());
			
		}
		$name = $type.getType();
	}

//	| simpleTypeSpecifier '[][]'
	//	{$name=$simpleTypeSpecifier.name+"[][]"; $type = new ArrayType( new ArrayType($simpleTypeSpecifier.type));}

	| 'ArrayList<' simpleTypeSpecifier '>'
	{$name="ArrayList"+"<"+$simpleTypeSpecifier.name+">";
	 $type = new ArrayListType($simpleTypeSpecifier.type);
	} //未處理

	| 'IntegerRange'
	{$name="IntegerCollection";
	$type = new ArrayType(new IntType());
	Main.typeTable.add($type);
	}

; //未處理

oclExpression returns [AbstractSyntaxTreeNode  node]
:
	ex = expression
	{$node=$ex.node;}

;

localVariableList returns [AbstractSyntaxTreeNode node]
:
	NAME
	{VariableRefExp var=new VariableRefExp($NAME.text,varScope.getId());}

	':' tySpec = typeSpecifier
	{ var.setType($tySpec.type);
	  Main.localSymbolTable.get(0).addLocalVariable(var);
	  varScope.addVariable(new VariableToken( $NAME.text, $tySpec.type));
	}

	'=' ex = expression
	{
		OperatorExp eq=new OperatorExp("=",true);
		eq.setOperand(var,$ex.node);
		$node = eq;
	}

;

localVariableLists returns [ AbstractSyntaxTreeNode node]
:
	localVar = localVariableList
	{
		$node = $localVar.node;
	}

	| localVars = localVariableLists ',' localVar = localVariableList
	{
	 	OperatorExp and_eq=new OperatorExp("and",true);
		and_eq.setOperand($localVars.node,$localVar.node);
		$node = and_eq;
		
	}

;

letExpression returns [LetExp  letnode]
:
//	'let' NAME 		{VariableRefExp var=new VariableRefExp($NAME.text);}				
//	( '(' fpl= formalParameterList ')'	)? //暫時用不到
//	( ':' tySpec=typeSpecifier 		)? //暫時用不到			
//	'='	ex=expression	'in'
	{Main.localSymbolTable.add(0,new SymbolTable());
	 varScope = new LocalVarScope("", varScope);
	}

	'let' localVars = localVariableLists 'in' ex = expression
	{
	   $letnode=new LetExp( $localVars.node , $ex.node);
	   Main.localSymbolTable.remove(0);
	   varScope = varScope.getParent();
				
	}
//)
	//		|'let' NAME ':' 'Array(Integer)' '=' 'RandomIndexArray{1..size@pre}' // ?? 之後要改嗎 2021-01-31 dai
	//		 {MethodCallExp var=new MethodCallExp("randomIndexArray");  
	//		 	VariableRefExp size=new VariableRefExp("size");
	//		 	size.setTimeExpression();
	//		 	size.setType("int");
	//		 	VariableRefExp index= new VariableRefExp("arrayIndex");
	//		 	index.setType("int");
	//		 	var.addParameters(size,index);
	//		 }'in' ex=expression{
	//		 	OperatorExp and=new OperatorExp("and");
	//		 	and.setOperand(var,$ex.node);
	//		 	$operator=and;
	//		 }
	//		 |'let' NAME ':' 'Array(Integer)' '=' 'RandomIndexArray{1..'INTEGER'}'
	//		 {MethodCallExp var=new MethodCallExp("randomIndexArray");
	//		 	LiteralExp liter=new LiteralExp("Integer",$INTEGER.text);
	//		 	VariableRefExp index= new VariableRefExp("arrayIndex");
	//		 	index.setType("int");
	//		 	var.addParameters(liter,index);
	//		 }'in' ex=expression{
	//		 	OperatorExp and=new OperatorExp("and");
	//		 	and.setOperand(var,$ex.node);
	//		 	$operator=and;
	//		 }

;

expression returns [AbstractSyntaxTreeNode node]
: //rowExpression指的是一行運算式子，且是用來印呼叫method call的參數為來用的
	logicExp = logicalExpression
	{
			$node=$logicExp.node;
		}

;

logicalExpression returns [AbstractSyntaxTreeNode node]
:
	relExp = relationalExpressions
	{
		 	$node=$relExp.node;
		}

;

relationalExpressions returns [AbstractSyntaxTreeNode node]
:
	reExp1 = relationalExpressions logicOper = logicalOperator //	做and/or						
	reExp2 = relationalExpression
	{
			($logicOper.operator).setOperand($reExp1.node,$reExp2.node);
			$node=$logicOper.operator;
			}

	| relExp = relationalExpression
	{$node=$relExp.node;}

;

logicalOperator returns [String logic,OperatorExp operator]
:
	'and'
	{$logic="and";$operator=new OperatorExp("and");}

	| 'or'
	{$logic="or";$operator=new OperatorExp("or");}

	| 'xor'
	{$logic="xor";$operator=new OperatorExp("xor");}

	| 'implies'
	{$logic="implies";$operator=new OperatorExp("implies");}

;

relationalExpression returns [AbstractSyntaxTreeNode node]
:
	addEx1 = additiveExpression
	{$node=$addEx1.node;} //做不等式

	(
		relation = relationalOperator addEx2 = additiveExpression
		{
				($relation.operator).setOperand ($addEx1.node,$addEx2.node);
				$node=$relation.operator;
			}

	)?
;

relationalOperator returns [String relation,OperatorExp operator]
:
	'='
	{$relation="=";$operator=new OperatorExp ("=");}

	| '>'
	{$relation=">";$operator=new OperatorExp (">");}

	| '<'
	{$relation="<";$operator=new OperatorExp ("<");}

	| '>='
	{$relation=">=";$operator=new OperatorExp (">=");}

	| '<='
	{$relation="<=";$operator=new OperatorExp ("<=");}

	| '<>'
	{$relation="<>";$operator=new OperatorExp ("<>");}

	| '=='
	{$relation="==";$operator=new OperatorExp ("==");}

;

additiveExpression returns [AbstractSyntaxTreeNode node]
:
	mulExs = multiplicativeExpressions
	{
		$node= $mulExs.node;
	}

;

multiplicativeExpressions returns [AbstractSyntaxTreeNode node]
:
	mulEx1 = multiplicativeExpressions addOp = addOperator //後做加減						
	mulEx2 = multiplicativeExpression
	{
		($addOp.operator).setOperand($mulEx1.node,$mulEx2.node);
		$node=$addOp.operator;
	}

	| mulEx = multiplicativeExpression
	{$node=$mulEx.node;}

;

addOperator returns [String type,OperatorExp operator]
:
	'+'
	{$type="+";$operator=new OperatorExp ("+");}

	| '-'
	{$type="-";$operator=new OperatorExp ("-");}

;

multiplicativeExpression returns [AbstractSyntaxTreeNode node]
:
	unEx = unaryExpressions
	{
		$node=$unEx.node;
		}

;

unaryExpressions returns [AbstractSyntaxTreeNode node]
:
	unEx1 = unaryExpressions mulOp = multiplyOperator //先做乘除						 
	unEx2 = unaryExpression
	{
		($mulOp.operator).setOperand($unEx1.node,$unEx2.node);
		$node=$mulOp.operator;
	}

	| unEx = unaryExpression
	{$node=$unEx.node;}

;

multiplyOperator returns [String type,OperatorExp operator]
:
	'*'
	{$type="*";$operator=new OperatorExp ("*");}

	| '/'
	{$type="/";$operator=new OperatorExp ("/");}

;

unaryExpression returns [AbstractSyntaxTreeNode node]
:
	unOp = unaryOperator //做負號
	poEx = postfixExpression
	{
		($unOp.operator).setUnaryOperand($poEx.node);
		$node=$unOp.operator;
	}

	| poEx = postfixExpression
	{$node=$poEx.node;}

;

unaryOperator returns [String type,OperatorExp operator]
:
	'-'
	{$type="-";$operator=new OperatorExp("-");}

	| 'not'
	{$type="not";$operator=new OperatorExp("not");}

;

postfixExpression returns [AbstractSyntaxTreeNode node]
:
	priEx = primaryExpression //if或運算或變數自然數或做let或呼叫attribute
	pes = primaryExpressions [$priEx.node]
	(
	/*cal = calculator [$pes.node]
		{$node=$cal.node;}

		|*/
		{$node=$pes.node;}

	)
;

primaryExpressions [AbstractSyntaxTreeNode priEx] returns
[AbstractSyntaxTreeNode node]
:
	{OperatorExp op;}

	(
		'.'
		{op =new OperatorExp(".");}

		| '->'
		{op =new OperatorExp("->");}

	) prop = propertyCall [$priEx,false]
	{op.setOperand($priEx,$prop.property);}

	pes = primaryExpressions [op]
	{
		{$node=$pes.node;}
	}

	|
	{$node=$priEx;}

;

/*calculator [AbstractSyntaxTreeNode priEx] returns
[AbstractSyntaxTreeNode node]
:
	'.add('
	{$node=new OperatorExp("+");}

	ex = expression
	{((OperatorExp)$node).setOperand($priEx,$ex.node);}

	')' cal = calculator [$node]
	{$node=$cal.node;}

	| '.sub('
	{$node=new OperatorExp("-");}

	ex = expression
	{((OperatorExp)$node).setOperand($priEx,$ex.node);}

	')' cal = calculator [$node]
	{$node=$cal.node;}

	| '.mul('
	{$node=new OperatorExp("*");}

	ex = expression
	{((OperatorExp)$node).setOperand($priEx,$ex.node);}

	')' cal = calculator [$node]
	{$node=$cal.node;}

	| '.div('
	{$node=new OperatorExp("/");}

	ex = expression
	{((OperatorExp)$node).setOperand($priEx,$ex.node);}

	')' cal = calculator [$node]
	{$node=$cal.node;}

	|
	{$node=$priEx;}

;*/
primaryExpression returns [AbstractSyntaxTreeNode node]
:
	literCol = literalCollection
	{$node=$literCol.node;} //基本型態

	| prop = propertyCall [null,false]
	{$node=$prop.property;}

	index = literalIndex
	{((PropertyCallExp)$node).setQualifier($index.list);} //陣列

	| Literal = literal
	{
  		$node=$Literal.liter;
  		}

	| newObject = construtorCall type = typeSpecifier dim = literalIndex '(' ')'
	{
		MethodCallExp arrayDim = new MethodCallExp("dim");
		 arrayDim.setParameters($dim.list);
		 Main.typeTable.add(new ArrayListType(new IntType()));
		 arrayDim.setType(new ArrayListType(new IntType()));
		 arrayDim.setReturnType(new ArrayListType(new IntType()));
		 
		 
		ArrayType array = new ArrayType($type.type);
		for(int i =  ((MethodCallExp) arrayDim).getParameters().size()-1 ; i >= 0; i-- ){
			if(i == ((MethodCallExp) arrayDim).getParameters().size()-1){
				array = new ArrayType($type.type);
				Main.typeTable.add(array);
			}else
				array = new ArrayType(array);
				Main.typeTable.add(array);
		}
		
		 ArrayList<AbstractSyntaxTreeNode> parameters =  new ArrayList<AbstractSyntaxTreeNode>();
		 parameters.add(arrayDim);
		 
		 MethodCallExp arrayDel = new MethodCallExp("dcl_array");
		 arrayDel.setParameters(parameters);
		 arrayDel.setType(array.clone());
		 arrayDel.setReturnType(array.clone());
		 $node = arrayDel;
	}

	|
	{boolean isNew = false;}

	(
		newObject = construtorCall
		{isNew = true;}

	)? prop = propertyCall [null,isNew]
	{$node=$prop.property;}

	| '(' ex = expression
	{
			$node=$ex.node;
		}

	')'

	/* | 'dcl_array' '('  method =  propertyCall[null] ',' '\'' type =
	typeSpecifier '\'' ')'
	{ 
		ArrayType array = new ArrayType($type.type);
		for(int i =  ((MethodCallExp) $method.property).getParameters().size()-1 ; i >= 0; i-- ){
			if(i == ((MethodCallExp) $method.property).getParameters().size()-1){
				array = new ArrayType($type.type);
				Main.typeTable.add(array);
			}else
				array = new ArrayType(array);
				Main.typeTable.add(array);
		}
		 ArrayList<AbstractSyntaxTreeNode> parameters =  new ArrayList<AbstractSyntaxTreeNode>();
		 parameters.add($method.property);
		 MethodCallExp arrayDel = new MethodCallExp("dcl_array");
		 arrayDel.setParameters(parameters);
		 arrayDel.setType($type.type.clone());
		 arrayDel.setReturnType($type.type.clone());
		 $node = arrayDel;
	}

	| 'dim' '(' parmetersList = propertyCallParameters ')'
	{
		MethodCallExp arrayDim = new MethodCallExp("dim");
		 arrayDim.setParameters($parmetersList.list);
		 Main.typeTable.add(new ArrayListType(new IntType()));
		 arrayDim.setType(new ArrayListType(new IntType()));
		 arrayDim.setReturnType(new ArrayListType(new IntType()));
   }*/
	| '(' ex = expression
	{
			$node=$ex.node;
		}

	')'
	| ifExp = ifExpression
	{$node=$ifExp.node;}

	| letExp = letExpression
	{$node=$letExp.letnode;}

;

construtorCall
:
	'new'
;

literal returns [LiteralExp liter]
:
	STRING
	{$liter=new LiteralExp(new StringType(),$STRING.text);}

	|
	{boolean minus=false;}

	(
		'-'
		{minus=true;}

	)? INTEGER
	{if(minus) $liter=new LiteralExp(new IntType(),"-"+$INTEGER.text);else $liter=new LiteralExp(new IntType(),$INTEGER.text);}

	| INTEGER
	{String real=$INTEGER.text;}

	'.' INTEGER
	{real+="."+$INTEGER.text;$liter=new LiteralExp(new DoubleType(),real);}
	//|enumLiteral

	| boolExp = booleanExp
	{$liter=new LiteralExp( new BooleanType() ,$boolExp.value);}

;

booleanExp returns [String value]
:
	'true'
	{$value="true";}

	| 'false'
	{$value="false";}

;

propertyCall [AbstractSyntaxTreeNode priEx,boolean isNew] returns
[PropertyCallExp property]
:
	paNa = pathName
	{
			if($isNew){
				
				$property=new ClassCallExp($paNa.name,$priEx, varScope.getId());
				($property).setType($pathName.name);
				($property).setIsNewObj(true);
			}else{
				
				$property=new VariableRefExp($paNa.name,$priEx, varScope.getScopeIdVarIn($paNa.name));
			
				if($priEx == null){
					
					($property).findFromLocalVariableTable(Main.localSymbolTable);
					($property).addVariableType(Main.typeTable);
				
				}else{
					
					($property).setSourceExp($priEx);
					($property).addVariableType(Main.typeTable);
				}
			
			}
		}

	(
		timeExpression
		{($property).setTimeExpression();}

	)?
	(
		index = literalIndex
		{((PropertyCallExp)$property).setQualifier($index.list);} //陣列

	)?
	(
		pcps = propertyCallParameters
		{$property = new MethodCallExp($property); 
			((MethodCallExp)($property)).setParameters($pcps.list);
			if($property.getType() instanceof ArrayType){
				if (((ArrayType) $property.getType()).getDim() == ((PropertyCallExp)$property).getQualifier().size()){
					((MethodCallExp)($property)).setReturnType( ((ArrayType) ((PropertyCallExp)$property.getSourceExp()).getType()).getUnitType().getSymbolTable().getMethod().get($property.getVariable()).getReturnType());
				}else{
					((MethodCallExp)($property)).setReturnType(new VoidType());
				}
			}
			else if($property.getType() instanceof UserDefinedType)
			 	((MethodCallExp)($property)).setReturnType($property.getType().getSymbolTable().getMethod().get($property.getVariable()).getReturnType());
			else {
				((MethodCallExp)($property)).setReturnType($property.getType(),Main.typeTable);
			}
		}

	)?
;

timeExpression
:
	'@' 'pre'
;

propertyCallParameters returns [ArrayList<AbstractSyntaxTreeNode> list]
:
	'('
	(
		de = declarator
	)?
	(
		apl = actualParameterList
		{
			$list=$apl.list;
		}

	)? ')'
;

ifExpression returns [IfExp node]
:
	'if' condition = expression 'then' then = expression ifs = ifExps
	[$condition.node,$then.node]
	{$node=$ifs.node;}

;

ifExps [AbstractSyntaxTreeNode condition,AbstractSyntaxTreeNode then] returns
[IfExp node]
:
	'else' elseExp = expression 'endif'
	{$node=new IfExp("if",$condition,$then,$elseExp.node);}

	| 'endif'
	{$node=new IfExp("if",$condition,$then);}

;

enumLiteral
:
	NAME '::' NAME
	(
		'::' NAME
	)*
; //未處理

simpleTypeSpecifier returns [String name , VariableType type]
:
	pn = pathName
	{$name=$pn.name; 
		$type = Main.typeTable.get($pn.name,null);
		if($type == null){
			Main.typeTable.createUserDefinedType($pn.name);
			$type = Main.typeTable.get($pn.name,null).clone();
		}
	}

; //未處理

literalCollection returns [AbstractSyntaxTreeNode node]
:
	colKind = collectionType
	{CollectionExp node1=new CollectionExp($colKind.type);}

	'{'
	(
		colItem = collectionItem [node1]
		(
			',' collectionItem [node1]
		)*
	)? '}' '->' coMe = collectionMethod [node1]
	{if($coMe.node2 instanceof IterateExp) {$node=$coMe.node2;}else {((CollectionExp)$node).setMethodName(((PropertyCallExp)$coMe.node2).getVariable()); $node=node1;}}

; //未處理  

collectionItem [AbstractSyntaxTreeNode node]
:
	ex = expression
	{((CollectionExp)$node).setStart($ex.node);}

	(
		'..' ex2 = expression
		{((CollectionExp)$node).setEnd($ex2.node);}

	)?
;

//collectionKind returns [String name,VariableType type]
//:
//	simpleTypeSpecifier
//	{$name=$simpleTypeSpecifier.name; $type = $simpleTypeSpecifier.type;}
//	|simpleTypeSpecifier '[]'
//	{$name=$simpleTypeSpecifier.name+"[]";$type = new ArrayType($simpleTypeSpecifier.type)}
//
//	| simpleTypeSpecifier '[][]'
//	{$name=$simpleTypeSpecifier.name+"[][]"; $type = new ArrayType( new ArrayType($simpleTypeSpecifier.type));}
//
//	| 'ArrayList<' simpleTypeSpecifier '>'
//	{$name="ArrayList"+"<"+$simpleTypeSpecifier.name+">";
//	 $type = new ArrayList($simpleTypeSpecifier.type);
//	} //未處理
//
//	| 'IntegerRange'
//	{$name="IntegerCollection";
//	$type = new ArrayType(new IntType());
//	}
//
//;

collectionMethod [AbstractSyntaxTreeNode node] returns
[AbstractSyntaxTreeNode node2]
:
{	
		$node2=new IterateExp(((CollectionExp)$node).getType());
		Main.localSymbolTable.add(0,new SymbolTable());
		varScope = new LocalVarScope("", varScope);
	}

	'iterate(' name = NAME ':' elementType = typeSpecifier ';'
	{
		VariableRefExp elementVariable = new VariableRefExp($name.text,varScope.getId());
		elementVariable.setType($elementType.type);
		Main.localSymbolTable.get(0).addLocalVariable(elementVariable);
		varScope.addVariable( new VariableToken($name.text, $elementType.type));
		((IterateExp)$node2).setElement(elementVariable);
	}

	localVars = localVariableLists
	{
	((IterateExp)$node2).setStart(((CollectionExp)$node).getStart());
 	((IterateExp)$node2).setEnd(((CollectionExp)$node).getEnd());
 	
 	{
 	 ((IterateExp)$node2).setAcc($localVars.node);
 	 ((CollectionExp)$node).setAcc($localVars.node);
 	}
}
//
	//pN=pathName{((IterateExp)$node2).setAccVariable($pN.name);} ':'
	// {
	// 	((IterateExp)$node2).setStart(((CollectionExp)$node).getStart());
	// 	((IterateExp)$node2).setEnd(((CollectionExp)$node).getEnd());
	// }
	// (
	//	'Integer = ' lit=literal
	// 	{((IterateExp)$node2).setAccType("Integer");
	// 	 ((CollectionExp)$node).setAccType("Integer");
	// 	 ((IterateExp)$node2).setAcc(($lit.liter).getValue());
	// 	 ((CollectionExp)$node).setAcc(($lit.liter).getValue());
	// 	}
	//  |
	// 'Boolean = '{((IterateExp)$node2).setAccType("Boolean");((CollectionExp)$node).setAccType("Boolean");}'true'{((IterateExp)$node2).setAcc("true");((CollectionExp)$node).setAcc("true");}
	// ) 
	'|' ex = expression
	{((IterateExp)$node2).setBody($ex.node);((CollectionExp)$node).setBody($ex.node);
		Main.localSymbolTable.remove(0);
		varScope = varScope.getParent();
	}

	')'
	| 'prepend(' expression ')'
	| 'subsequence(' expression ',' literalCollection ')'
;

literalIndex returns [ArrayList<AbstractSyntaxTreeNode> list]
:
	{$list=new ArrayList<>();}

	(
		'[' ex = expression ']'
		{$list.add($ex.node);}

	)+
;

declarator
: //method call裏頭參數//未用到
	NAME
	(
		',' NAME
	)*
	(
		':' sts = simpleTypeSpecifier
	)?
	(
		';' NAME ':' typeSpecifier '=' expression
	)? '|'
;

actualParameterList returns [ArrayList<AbstractSyntaxTreeNode> list]
: //method call裏頭參數,list是有多少個參數
	ex1 = expression
	{
		$list=new ArrayList<AbstractSyntaxTreeNode>();
		($list).add($ex1.node);
		
	}

	apls = actualParameterLists [$ex1.node]
	{
		($list).addAll($apls.list);
		
	}

;

actualParameterLists [AbstractSyntaxTreeNode ex1] returns
[ArrayList<AbstractSyntaxTreeNode> list]
: //method call裏頭參數，當有很多個參數時,list是有多少個參數
	',' ex2 = expression
	{
		$list=new ArrayList<AbstractSyntaxTreeNode>();
		($list).add($ex2.node);
	}

	apls = actualParameterLists [$ex2.node]
	{
		($list).addAll($apls.list);
	}

	|
	{
			$list=new ArrayList<AbstractSyntaxTreeNode>();
		}

;

NAME
:
	[a-zA-Z_]
	(
		[a-zA-Z0-9_]
	)*
;

INTEGER
:
	[0-9]
	(
		[0-9]
	)*
;

STRING
:
	'\'' .*? '\''
;

WHITESPACE
:
	(
		[\t]
		| [\r]
		| [\n]
		| ' '
	)+ -> skip
;