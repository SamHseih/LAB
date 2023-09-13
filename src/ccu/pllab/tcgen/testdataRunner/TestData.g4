grammar TestData;

@header {
	//這部分是就是java裏頭import的部分，因為.g4檔與XXXParser.java是連動關係，
	//所以不能直接在XXXParser.java增添import，一旦存檔就會被洗掉，
	//因此如果要import的時候，在@header裏頭加
	import ccu.pllab.tcgen.SymbolTable.*;
	import ccu.pllab.tcgen.AbstractType.*;
	import ccu.pllab.tcgen.typeTestData.*;
	import ccu.pllab.tcgen.exe.main.*;
	import java.util.ArrayList;
}

testDataList [VariableType type,String className,String methodName] returns
[List<MethodTestData> methodTestData]
:
	{$methodTestData = new ArrayList<MethodTestData>();}

	(
		INTEGER ': ' data = testData [ type, className, methodName]
		{
 			($methodTestData).add($data.data);
 			
 		}

	)*
;

testData [VariableType type,String className,String methodName] returns
[MethodTestData data]
:
	{ArrayList<ArrayList<TypeTestData>> dataList = new ArrayList<ArrayList<TypeTestData>>(); }

	(
		item = itemState [type,methodName]
		{
 			(dataList).add($item.item);
 		}

		','
	)+ exitem = exceptionState
	{
 	 	dataList.add($exitem.item);
 	 	$data = new MethodTestData(className,methodName,dataList);
 	 }

;

exceptionState returns [ArrayList<TypeTestData> item]
:
	'EXCEPTION' '='
	(
		'[]'
		| '[[]]'
		| '[' exStr = STRING ']'
	)
	{
 		ExceptionTestData exdata = null;
 		$item  = new ArrayList<TypeTestData>();
 		if($exStr != null){
 			exdata = new ExceptionTestData($exStr.text);
 			($item).add(exdata);
 		}
 	}

;

itemState [VariableType type, String method] returns
[ArrayList<TypeTestData> item]
:
	itemclass = itemName '=' ele = elms [ type, method, $itemclass.itemClass]
	{
 		if($itemclass.itemClass.equals("obj")){
 			TypeTestData userTestData =  new UserDefinedTypeTestData($ele.eleDataList, type);
 			$item = new ArrayList<TypeTestData>();
 			$item.add(userTestData);
 		}else{
 			$item = $ele.eleDataList;
 		}
 		
 	}

;

itemName returns [String itemClass]
:
	(
		objStr = 'OBJ'
		{$itemClass = "obj";}

		| argStr = 'ARG'
		{$itemClass = "arg";}

	) time
	| 'RETVAL'
	{ $itemClass = "ret";}

;

time
:
	'_PRE'
	| '_POST'
;

elms [VariableType type, String method,String inputType] returns
[ArrayList<TypeTestData> eleDataList]
:
{$eleDataList = new ArrayList<TypeTestData>();
	 ArrayList<VariableType> elemTypeList = new ArrayList<VariableType>(); 
		if(type instanceof UserDefinedType){
			if(inputType.equals("obj")){
				for( VariableToken var : type.getSymbolTable().getAttribute().values()){
					elemTypeList.add(var.getType());
				}
			}else if(inputType.equals("arg")){
				for( VariableToken var : type.getSymbolTable().getMethod().get(method).getArgument().values()){
					elemTypeList.add(var.getType());
				}
			}else if(inputType.equals("ret")){
				elemTypeList.add( type.getSymbolTable().getMethod().get(method).getReturnType());
			}
		}else{
			elemTypeList.add(type);
		}
		
	}

	(
		'[]'
		| '[' ele = objElmList [type,method,inputType,elemTypeList] ']'
		{
 		$eleDataList = $ele.testdata;
 	}

	)
;

objElmList
[VariableType type, String method, String inputType, ArrayList<VariableType> elemTypeList]
returns [ArrayList<TypeTestData> testdata]
:
{int eleCount = 0; 
	$testdata = new ArrayList<TypeTestData>();
	}

	ele = objElm [elemTypeList.get(eleCount)]
	{
 		($testdata).add($ele.objElmTestdata);
 		if(elemTypeList.size() > eleCount + 1)
 			eleCount++;
 	}

	(
		',' ele1 = objElm [elemTypeList.get(eleCount)]
		{
 			($testdata).add($ele1.objElmTestdata);
 			if(elemTypeList.size() > eleCount + 1)
 				eleCount++;
 		}

	)*
;

objElm [VariableType type] returns [TypeTestData objElmTestdata]
:
	typedata = typeData [type]
	{$objElmTestdata = $typedata.data;}

;

typeData [VariableType type] returns [TypeTestData data]
:
	'[]'
	{$data = new VoidTypeTestData();}

	| bool = BOOL
	{ $data = new BooleanTypeTestData($bool.text);}

	| intstr = INTEGER
	{$data = new IntTypeTestData($intstr.text);}

	| realBound = realBoundList
	{$data = new RealTypeTestData(($realBound.realBound).get(0),($realBound.realBound).get(1));}

	| str = STRING
	{ $data = new StringTypeTestData($str.text);}

	| arrayData = array [type]
	{$data = new ArrayTypeTestData($arrayData.arrayTestdata,type);}

	| listTestData = list [type]
	{
 		if(type instanceof ArrayListType){
 			$data = new ArrayListTypeTestData($listTestData.listdata,type);
 		}else{
 			$data = new UserDefinedTypeTestData($listTestData.listdata,type);
 		}
 	}

	|
	{$data = new VoidTypeTestData();}

;

list [VariableType type] returns [ArrayList<TypeTestData> listdata]
:
{
	VariableType elemType = (type instanceof ArrayListType)? ((ArrayListType) type).getElement(): type ;
	 ArrayList<VariableType> elemTypeList = new ArrayList<VariableType>(); 
		if(elemType instanceof UserDefinedType){
			
				for( VariableToken var : elemType.getSymbolTable().getAttribute().values()){
					elemTypeList.add(var.getType());
				}
		}else{
			elemTypeList.add(elemType);
		}
		
	}

	'[' objlist = objElmList [elemType, "", "obj", elemTypeList] ']'
	{$listdata = $objlist.testdata;}

;

array [VariableType type] returns [ArrayList<TypeTestData> arrayTestdata]
:
{
	VariableType elemType = (type instanceof ArrayType)? ((ArrayType) type).getElement(): type ;
	 ArrayList<VariableType> elemTypeList = new ArrayList<VariableType>(); 
		if(elemType instanceof UserDefinedType){
			
				for( VariableToken var : elemType.getSymbolTable().getAttribute().values()){
					elemTypeList.add(var.getType());
				}
		}else{
			elemTypeList.add(elemType);
		}
		
	}

	'[](' elmList = objElmList [elemType, "", "obj",elemTypeList] ')'
	{ $arrayTestdata = $elmList.testdata;}

;

realBoundList returns [ArrayList<String> realBound]
:
	'[' low = REAL ',' high = REAL ']'
	{
		$realBound = new ArrayList<String>();
		($realBound).add($low.text);
		($realBound).add($high.text);
	}

;

STRING:
	'\"' (PUNCT2|CNTRL|NUMBER|LETTER|PUNCT)*? '\"'
	;

REAL
:
	NUMBER+
	(
		'.' NUMBER+
	)
	(
		'E'
		(
			'+'
			| '-'
		)? NUMBER+
	)?
;

BOOL
:
	'true'
	| 'false'
;

INTEGER
:
	'-'? NUMBER+
;

NUMBER
:
	[0-9]
;

PUNCT:
[!"#$%&'()*+,./:;<=>?@\^_`{|}~-]
;


PUNCT2:
	'\\' '\"' | '\\'
;

CNTRL:

[\x00-\x1F\x7F]
;

LETTER
:
	[A-Za-z]
;

WS
:
	[ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines


