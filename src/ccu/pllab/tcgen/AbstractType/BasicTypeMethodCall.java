package ccu.pllab.tcgen.AbstractType;

import java.util.HashMap;

import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;

public class BasicTypeMethodCall {

	private HashMap<String, SymbolTable> methodCall;

	public BasicTypeMethodCall() {
		methodCall = new HashMap<String, SymbolTable>();
		init();
	}

	private void init() {

		// String Method
		SymbolTable stringSymbolTable = new SymbolTable();

		MethodToken concat = new MethodToken("concat");
		concat.setReturnType(new StringType());
		concat.addArgument(new VariableToken("s", new StringType()));
		stringSymbolTable.addMethod(concat);

		MethodToken toInteger = new MethodToken("toInteger");
		toInteger.setReturnType(new IntType());
		stringSymbolTable.addMethod(toInteger);

		MethodToken equalsIgnoreCase = new MethodToken("equalsIgnoreCase");
		equalsIgnoreCase.setReturnType(new BooleanType());
		equalsIgnoreCase.addArgument(new VariableToken("s", new StringType()));
		stringSymbolTable.addMethod(equalsIgnoreCase);

		MethodToken size = new MethodToken("size");
		size.setReturnType(new IntType());
		stringSymbolTable.addMethod(size);

		MethodToken at = new MethodToken("at");
		at.setReturnType(new StringType());
		at.addArgument(new VariableToken("i", new IntType()));
		stringSymbolTable.addMethod(at);

		MethodToken indexOf = new MethodToken("indexOf");
		indexOf.addArgument(new VariableToken("s", new StringType()));
		indexOf.setReturnType(new IntType());
		stringSymbolTable.addMethod(indexOf);

		MethodToken substring = new MethodToken("substring");
		substring.addArgument(new VariableToken("lower", new StringType()));
		substring.addArgument(new VariableToken("upper", new StringType()));
		substring.setReturnType(new StringType());
		stringSymbolTable.addMethod(substring);

		MethodToken toLower = new MethodToken("toLowerCase");
		toLower.setReturnType(new StringType());
		stringSymbolTable.addMethod(toLower);

		MethodToken toUpper = new MethodToken("toUpperCase");
		toUpper.setReturnType(new StringType());
		stringSymbolTable.addMethod(toUpper);

		MethodToken characters = new MethodToken("characters");
		characters.setReturnType(new SequenceType(new StringType()));
		stringSymbolTable.addMethod(characters);

		MethodToken toReal = new MethodToken("toReal");
		toReal.setReturnType(new DoubleType());
		stringSymbolTable.addMethod(toReal);

		MethodToken toBoolean = new MethodToken("toBoolean");
		toBoolean.setReturnType(new BooleanType());
		stringSymbolTable.addMethod(toBoolean);

		methodCall.put("String", stringSymbolTable);

		// Integer Method
		SymbolTable integerSymbolTable = new SymbolTable();

		MethodToken int_abs = new MethodToken("abs");
		int_abs.setReturnType(new IntType());
		integerSymbolTable.addMethod(int_abs);

		MethodToken div = new MethodToken("div");
		div.addArgument(new VariableToken("i", new IntType()));
		div.setReturnType(new IntType());
		integerSymbolTable.addMethod(div);

		MethodToken mod = new MethodToken("mod");
		mod.addArgument(new VariableToken("value", new IntType()));
		mod.setReturnType(new IntType());
		integerSymbolTable.addMethod(mod);

		MethodToken int_max = new MethodToken("max");
		int_max.addArgument(new VariableToken("i", new IntType()));
		int_max.setReturnType(new IntType());
		integerSymbolTable.addMethod(int_max);

		MethodToken int_min = new MethodToken("min");
		int_min.addArgument(new VariableToken("i", new IntType()));
		int_min.setReturnType(new IntType());
		integerSymbolTable.addMethod(int_min);

		MethodToken toString = new MethodToken("toString");
		toString.setReturnType(new StringType());
		integerSymbolTable.addMethod(toString);

		MethodToken gcd = new MethodToken("gcd");
		gcd.addArgument(new VariableToken("i", new IntType()));
		gcd.addArgument(new VariableToken("i2", new IntType()));
		gcd.setReturnType(new IntType());
		integerSymbolTable.addMethod(gcd);

		methodCall.put("int", integerSymbolTable);

		// real Method
		SymbolTable realSymbolTable = new SymbolTable();

		MethodToken real_abs = new MethodToken("abs");
		real_abs.setReturnType(new DoubleType());
		realSymbolTable.addMethod(real_abs);

		MethodToken floor = new MethodToken("floor");
		floor.setReturnType(new IntType());
		realSymbolTable.addMethod(floor);

		MethodToken round = new MethodToken("round");
		round.setReturnType(new IntType());
		realSymbolTable.addMethod(round);

		MethodToken real_max = new MethodToken("max");
		real_max.addArgument(new VariableToken("r", new DoubleType()));
		real_max.setReturnType(new DoubleType());
		realSymbolTable.addMethod(real_max);

		MethodToken real_min = new MethodToken("min");
		real_min.addArgument(new VariableToken("r", new DoubleType()));
		real_min.setReturnType(new DoubleType());
		realSymbolTable.addMethod(real_min);

		methodCall.put("real", realSymbolTable);

		// OrderedCollection Method
		SymbolTable collectionSymbolTable = new SymbolTable();

		MethodToken collection_size = new MethodToken("size");
		collection_size.setReturnType(new IntType());
		collectionSymbolTable.addMethod(collection_size);

		MethodToken orderedCollection_at = new MethodToken("at");
		orderedCollection_at.addArgument(new VariableToken("index", new IntType()));
		collectionSymbolTable.addMethod(orderedCollection_at);

		methodCall.put("Collection", collectionSymbolTable);

		// boolean Method
		SymbolTable booleanSymbolTable = new SymbolTable();

		MethodToken boolean_toString = new MethodToken("toString");
		boolean_toString.setReturnType(new StringType());

		booleanSymbolTable.addMethod(boolean_toString);

		methodCall.put("boolean", booleanSymbolTable);
	}

	public MethodToken findMethodCall(VariableType type, String methodName) {

		String type_string = "";

		if (type instanceof CollectionType) {
			type_string = "Collection";
		} else if (type instanceof FloatType || type instanceof DoubleType) {
			type_string = "real";
		} else {// integer、String
			type_string = type.getType();
		}

		return this.methodCall.get(type_string).getMethod().get(methodName);
	}

	public SymbolTable getSomeTypeSymbolTable(VariableType type) {

		String type_string = "";

		if (type instanceof CollectionType) {
			type_string = "Collection";
		} else if (type instanceof FloatType || type instanceof DoubleType) {
			type_string = "real";
		} else if (type instanceof SequenceType) {
			type_string = "Collection";
		} else {// integer、String
			type_string = type.getType();
		}

		return this.methodCall.get(type_string);
	}

	public String realMethodCLP() {
		String returnstr = "";

		// abs():Real
		returnstr += "ocl_real_abs(X,R):-\r\n" + "	((X $< 0.0, R $= -X); (X $>= 0.0, R $= X)).\r\n";

		// floor():Integer
		returnstr += "delay ocl_real_floor(X,R) if var(X).\r\n" + "ocl_real_floor(X,R):-\r\n" + "	floor(X,R1),\r\n"
				+ "	integer(R1,R).\r\n";

		// round():Integer
		returnstr += "delay ocl_real_round(X,R) if var(X).\r\n" + "ocl_real_round(X,R):-\r\n"
				+ "	R1 :: -32768 .. 32767,\r\n" + "	T $= X - R1,\r\n"
				+ "	((T $< 0.0, T1 $= -T);(T $>= 0.0, T1 $= T)),\r\n" + "	((T1 $< 0.5); (T1 $= 0.5,R1 $> X)),\r\n"
				+ "	R #= R1.\r\n";

		// max(r:Real):Real
		returnstr += "ocl_real_max(X,Y,R):-\r\n" + "	((X $>= Y, R $= X );(X $< Y, R $= Y)).\r\n";

		// min(r:Real):Real
		returnstr += "ocl_real_min(X,Y,R):-\r\n" + "	((X $=< Y, R $= X );(X $> Y, R $= Y)).\r\n";

		// toString():String -> 待開發

		return returnstr;
	}

	public String intgMethodCLP() {
		String returnstr = "";

		// abs():Integer
		returnstr += "ocl_int_abs(X,R):-\r\n" + "	((X #< 0, R #= -X); (X #>= 0, R #= X)).\r\n";

		// div():Integer
		returnstr += "delay ocl_int_div(X,D,R) if nonground([X,D]).\r\n" + "ocl_int_div(X,D,R):-\r\n"
				+ "	D #\\=0,\r\n" + "	R #=  X // D.\r\n";

		// mod():Integer -> 待開發
		returnstr += "";

		// max(i:Integer):Integer
		returnstr += "ocl_int_max(X,Y,R):-\r\n" + "	((X #>= Y, R #= X );(X #< Y, R #= Y)).\r\n";

		// min(i:Integer):Integer
		returnstr += "ocl_int_min(X,Y,R):-\r\n" + "	((X #=< Y, R #= X );(X #> Y, R #= Y)).\r\n";

		// toString: string.toString()
		returnstr += "delay ocl_int_toString(N,_) if nonground(N).\r\n" + "ocl_int_toString(N,[XS|X]):- \r\n"
				+ "	number_string(N,S),\r\n" + "	string_list(S,X),\r\n" + "	length(X,XS).\r\n";

		return returnstr;
	}

	public String stringMethodCLP() {
		String returnstr = "";

		// domain
		returnstr += "delay dcl_1dChar_array(_, Bound) if nonground([Bound]).\r\n" + "dcl_1dChar_array([], 0).\r\n"
				+ "dcl_1dChar_array([X|L], Bound) :-\r\n" + "    Bound > 0,\r\n" + "    X :: 0..127,\r\n"
				+ "    Bound1 is Bound - 1,\r\n" + "    dcl_1dChar_array(L, Bound1).\n";

		// labeling -> 使用 labeling_1dInt_array

		// size():Integer
		returnstr += "ocl_string_size([XSize|XList],Result):-\r\n" + "	Result = XSize.\n";

		// concat(s:String):String
		returnstr += "ocl_string_concat([A|X],[B|Y],[C|Z]):-\r\n" + "	C#=A+B,\r\n"
				+ "	concat_B([A|X],[B|Y],[C|Z]).\r\n" + "	\r\n" + "delay concat_B([A|_],_,_) if var(A).\r\n"
				+ "	concat_B([A|X],[_|Y],[_|Z]):-\r\n" + "	length(X,A),\r\n" + "	append(X,Y,Z).\n";

		// "ocl_string_compareTo 中compareTo
		returnstr += "compareTo([A|X],[B|Y],Result):-\r\n" + "(Result#=0,A#=B,X=Y);\r\n"
				+ "(Result#>0,compare_larger_length([A|X],[B|Y],Result));\r\n"
				+ "(Result#<0,compare_small_length([A|X],[B|Y],Result)).\r\n" + "\r\n"
				+ "delay compare_equals_length([X|_],[Y|_],Result) if var(Y);var(X).\r\n"
				+ "compare_equals_length([A|X],[B|Y],Result):-\r\n" + "	(A#=B,compare_equals_word(X,Y,Result)).\r\n"
				+ "compare_equals_word([],[],0).\r\n" + "compare_equals_word([X|Xs],[Y|Ys],Result):-\r\n"
				+ "	(Y#=X,compare_equals_word(Xs,Ys,Result)).\r\n" + "\r\n"
				+ "delay compare_larger_length([X|_],[Y|_],Result) if var(Y);var(X).\r\n"
				+ "compare_larger_length([A|X],[B|Y],Result):-\r\n"
				+ "	(A#>B->Result#= A-B;compare_lager_word(X,Y,Result)).\r\n"
				+ "compare_lager_word([X|Xs],[Y|Ys],Result):-\r\n"
				+ "	(X#>Y,Result#=X-Y);(Y#=X,compare_lager_word(Xs,Ys,Result)).\r\n"
				+ "delay compare_small_length([X|_],[Y|_],Result) if var(X);var(Y).\r\n"
				+ "compare_small_length([A|X],[B|Y],Result):-\r\n"
				+ "	(B#>A->Result #=A-B; compare_small_word(X,Y,Result)).\r\n"
				+ "compare_small_word([X|Xs],[Y|Ys],Result):-\r\n"
				+ "	(Y#>X,Result#=X-Y);(Y=X,compare_small_word(Xs,Ys,Result)).\r\n";

		// [length|dataList] compareTo: =、/=、<、<=、>、>=
		returnstr += "ocl_string_equals(X,Y,Result):-\r\n" + "	(Result = 1 ;Result = 0),\r\n"
				+ "	(Result = 1,Z#=0;Result=0,Z#\\=0),\r\n" + "	compareTo(X,Y,Z).\r\n" + "\r\n"
				+ "ocl_string_not_equals(X,Y,Result):-\r\n" + "	(Result = 1 ;Result = 0),\r\n"
				+ "	(Result = 1 ->Z#\\=0;Z#=0),\r\n" + "	compareTo(X,Y,Z).\r\n" + "	\r\n"
				+ "ocl_string_greater_than(X,Y,Result):-\r\n" + "	(Result = 1 ;Result = 0),\r\n"
				+ "	(Result = 1 ->Z#>0;Z#=<0),\r\n" + "	compareTo(X,Y,Z).\r\n" + "\r\n"
				+ "ocl_string_greater_equals(X,Y,Result):-\r\n" + "	(Result = 1 ;Result = 0),\r\n"
				+ "	(Result = 1 ->Z#>=0;Z#<0),\r\n" + "	compareTo(X,Y,Z).\r\n" + "	\r\n"
				+ "ocl_string_less_than(X,Y,Result):-\r\n" + "	(Result = 1 ;Result = 0),\r\n"
				+ "	(Result = 1 ->Z#<0;Z#>=0),\r\n" + "	compareTo(X,Y,Z).\r\n" + "	\r\n"
				+ "ocl_string_less_equals(X,Y,Result):-\r\n" + "	(Result = 1 ;Result = 0),\r\n"
				+ "	(Result = 1->Z#=<0;Z#>0),\r\n" + "	compareTo(X,Y,Z).\n";

		// toInteger
		returnstr += "delay ocl_string_toInteger([XSize|XList],N) if nonground(XSize).\r\n"
				+ "ocl_string_toInteger([XSize|XList],N):-\r\n" + "	isNeg([XSize|XList],[NXSize|NXList],S),\r\n"
				+ "	reverse(NXList,TList),\r\n" + "	toInteger(TList,N1),\r\n" + "	(S > 0 -> N #= N1; N #= -N1).\r\n";

		// toInteger 中的 isNeg
		returnstr += "\r\n" + "isNeg([0|[]],[0|[]],0).\r\n" + "isNeg([XSize|[XList|XList1]],[NXSize|XList1],1):-\r\n"
				+ "	XList #=43,\r\n" + "	NXSize #= XSize - 1.\r\n"
				+ "isNeg([XSize|[XList|XList1]],[NXSize|XList1],0):-\r\n" + "	XList #=45,\r\n"
				+ "	NXSize #= XSize - 1.	\r\n" + "isNeg([XSize|[XList|XList1]],[XSize|[XList|XList1]],1):-\r\n"
				+ "	XList #>=48,\r\n" + "	XList #=<57.\r\n";

		// toInteger 中的toInteger
		returnstr += "toInteger([],0).\r\n" + "toInteger([X|X1],N):-\r\n" + "	X#>=48,\r\n" + "	X#=<59,\r\n"
				+ "	N #=  N1 * 10 + (X - 48),\r\n" + "	toInteger(X1,N1).\r\n";

		// eaualsIgnoreCase
		returnstr += "ocl_string_equalsIgnoreCase([A|X],[B|Y],Result):-\r\n" + "	(Result=0;Result=1),\r\n"
				+ "	(Result==0 -> (A#\\=B;equalsIgnoreCase_B(X,Y,0));A#=B,equalsIgnoreCase_B(X,Y,1)).\r\n" + "\r\n"
				+ "delay equalsIgnoreCase_B(X,Y,_) if var(X),var(Y).\r\n" + "equalsIgnoreCase_B([],[],1).\r\n"
				+ "equalsIgnoreCase_B([A|X],[B|Y],1):-\r\n" + "	(A #>65,A #<90,B #>65,B #<90,A#=B);\r\n"
				+ "	(A #>65,A #<90,B #>97,B #<122,A#=B-32);\r\n" + "	(A #>97,A #<122,B #>97,B #<122,A#=B);\r\n"
				+ "	(A #>97,A #<122,B #>65,B #<90,A#=B+32);\r\n" + "	(A#=B).\r\n"
				+ "	equalsIgnoreCase_B(X,Y,1).\r\n" + "equalsIgnoreCase_B([A|X],[B|Y],0):-\r\n"
				+ "	(A #>65,A #<90,B #>65,B #<90,A#\\=B);\r\n" + "	(A #>65,A #<90,B #>97,B #<122,A#\\=B-32);\r\n"
				+ "	(A #>97,A #<122,B #>97,B #<122,A#\\=B);\r\n" + "	(A #>97,A #<122,B #>65,B #<90,A#\\=B+32);\r\n"
				+ "	(A#\\=B).\r\n";

		// substring
		returnstr += "delay ocl_string_substring([LengthS|StringS],Lower,Upper,_) if var(LengthS).\r\n"
				+ "ocl_string_substring([LengthS|StringS],Lower,Upper,[LengthResult|StringResult]):-\r\n"
				+ "	Lower #>=1,\r\n" + "	LengthResult #= Upper -Lower +1,\r\n" + "	LengthS #>= Upper,\r\n"
				+ "	Upper #>= Lower,\r\n" + "	LengthS #>= LengthResult,\r\n"
				+ "	test_sub_B(StringS,StringResult,Lower,LengthResult).\r\n"
				+ "test_sub_B([A|X],[B|Y],Position,LengthResult):-\r\n"
				+ "(( Position #> 1,N#=Position-1,test_sub_B(X,[B|Y],N,LengthResult));(Position #=< 1,((LengthResult #> 1,W #= LengthResult -1,A=B,test_sub_B(X,Y,Position,W));(LengthResult #=< 1,Y=[],A=B)))).\r\n";

		// indexOf
		returnstr += "delay ocl_string_indexOf([LengthX|StringX],[LengthY|StringY],ResultIndex) if var(LengthX).\r\n"
				+ "ocl_string_indexOf([LengthX|StringX],[LengthY|StringY],ResultIndex):-\r\n"
				+ "	(LengthX #= 0,ResultIndex#=0;LenghtX#\\=0,\r\n" + "	(LengthY #= 0,ResultIndex#=1;LengthY#\\=0,\r\n"
				+ "	(LengthX #<LengthY,ResultIndex#=0;LengthX #>=LengthY,\r\n"
				+ "	(findSubString([LengthX|StringX],1,[LengthY|StringY],LengthX,ResultIndex))))).\r\n"
				+ "findSubString([LengthX|StringX],Result,[LengthY|StringY],LengthX_Ori,ResultIndex):-\r\n"
				+ "	Result #> LengthX,\r\n" + "	ResultIndex #= 0.\r\n"
				+ "findSubString([LengthX|[StringX|StringX1]],Result,[LengthY|StringY],LengthX_Ori,ResultIndex):-\r\n"
				+ "	Result #=< LengthX,\r\n" + "	Z #= Result + LengthY -1,\r\n"
				+ "	ocl_string_substring([LengthX|[StringX|StringX1]],Result,Z,[LengthYT|StringYT]),\r\n"
				+ "	compareTo([LengthY|StringY],[LengthYT|StringYT],CR),\r\n"
				+ "	((CR #= 0,ResultIndex #= LengthX_Ori - LengthX + 1);(CR #\\= 0, LengthX1 #= LengthX -1,findSubString([LengthX1|StringX1],1,[LengthY|StringY],LengthX_Ori,ResultIndex))).\r\n";

		// toLower
		returnstr += "ocl_string_toLowerCase([LengthX|StringX],[LengthResult|StringResult]):-\r\n"
				+ "	LengthX#=LengthResult,\r\n" + "	toLowerCase_B(StringX,StringResult).\r\n" + "	\r\n"
				+ "delay toLowerCase_B(X,Y) if var(X),var(Y).\r\n" + "toLowerCase_B([],[]).\r\n"
				+ "toLowerCase_B([A|X],[B|Y]):-\r\n" + "	(A::65..90,B#=A+32;B#=A),toLowerCase_B(X,Y).\r\n";
		// toUpper
		returnstr += "ocl_string_toUpperCase([LengthX|StringX],[LengthResult|StringResult]):-\r\n"
				+ "	LengthX#=LengthResult,\r\n" + "	toUpperCase_B(StringX,StringResult).\r\n" + "	\r\n"
				+ "delay toUpperCase_B(X,Y) if var(X),var(Y).\r\n" + "toUpperCase_B([],[]).\r\n"
				+ "toUpperCase_B([A|X],[B|Y]):-\r\n" + "	(A::97..122,B#=A-32;B#=A),toUpperCase_B(X,Y).\r\n";

		// toBoolean，沒確定可用
		returnstr += "delay ocl_string_toBoolean([XSize|XList],N) if nonground(XSize).\r\n"
				+ "ocl_string_toBoolean([XSize|XList],N):-\r\n" + "	N :: 0 .. 1,\r\n"
				+ "	((string_list(\"true\",TList),length(TList,TSize),ocl_string_equals([XSize|XList],[TSize|TList],1),N #= 1);\r\n"
				+ "	(string_list(\"false\",TList),length(FList,FSize),ocl_string_equals([XSize|XList],[FSize|FList],1),N #= 0)).\r\n";

		// characters
		returnstr += "delay ocl_string_characters([XSize|XList],_) if nonground(XSize).\r\n"
				+ "ocl_string_characters([XSize|XList],[XSize|XSList]):-\r\n" + "	list_split(XList,XSList).\r\n"
				+ "list_split([],[]).\r\n" + "list_split([A|X],[[1,B]|Y]):-\r\n" + "	A = B,\r\n"
				+ "	list_split(X,Y).\r\n";

		// toReal
		returnstr += "delay ocl_string_toReal([XSize|XList],N) if nonground(XSize).\r\n"
				+ "ocl_string_toReal([XSize|XList],N):-\r\n"
				+ "	searchPoint([XSize|XList],[NLowerBeforePointSize|NLowerBeforePointList],[NLowerBehindPointSize|NLowerBehindPointList]),\r\n"
				+ "	ocl_string_toInteger([NLowerBeforePointSize|NLowerBeforePointList],LowerBeforePointN1),\r\n"
				+ "	((LowerBeforePointN1 #>= 0,ocl_string_toFloat([NLowerBehindPointSize|NLowerBehindPointList],NLowerBehindPointN1));(LowerBeforePointN1 #< 0,NLowerBehindPointSize1 #= NLowerBehindPointSize +1,\r\n"
				+ "	ocl_string_toFloat([NLowerBehindPointSize1|[45|NLowerBehindPointList]],NLowerBehindPointN1))),\r\n"
				+ "	N $=  (LowerBeforePointN1 + NLowerBehindPointN1).\r\n";

		// toReal 中用到的 searchPoint
		returnstr += "delay searchPoint([XSize|XList],_,_) if nonground(XSize).\r\n"
				+ "searchPoint([XSize|XList],[XSize|XList],[0|[]]):-\r\n"
				+ "	ocl_string_indexOf([XSize|XList],[1,46],Index),\r\n" + "	Index #= 0.\r\n"
				+ "searchPoint([XSize|XList],[0|[]],[XSize1|XList1]):-\r\n"
				+ "	ocl_string_indexOf([XSize|XList],[1,46],Index),\r\n" + "	Index #= 1,\r\n"
				+ "	ocl_string_substring([XSize|XList],2,XSize,[XSize1|XList1]).\r\n"
				+ "searchPoint([XSize|XList],[XSize1|XList1],[0|[]]):-\r\n"
				+ "	ocl_string_indexOf([XSize|XList],[1,46],Index),\r\n" + "	Index0 #= Index - 1,\r\n"
				+ "	ocl_string_substring([XSize|XList],1,Index0,[XSize1|XList1]),\r\n" + "	Index1 #= Index + 1,\r\n"
				+ "	Index1 #> XSize.	\r\n" + "searchPoint([XSize|XList],[XSize1|XList1],[XSize2|XList2]):-\r\n"
				+ "	ocl_string_indexOf([XSize|XList],[1,46],Index),\r\n" + "	Index0 #= Index - 1,\r\n"
				+ "	ocl_string_substring([XSize|XList],1,Index0,[XSize1|XList1]),\r\n" + "	Index1 #= Index + 1,\r\n"
				+ "	ocl_string_substring([XSize|XList],Index1,XSize,[XSize2|XList2]).\r\n";

		// toReal 中用到的 ocl_string_toFloat
		returnstr += "delay ocl_string_toFloat([XSize|XList],N) if nonground(XSize).\r\n"
				+ "ocl_string_toFloat([XSize|XList],N):-\r\n" + "	isNeg([XSize|XList],[NXSize|NXList],S),\r\n"
				+ "	toFloat(NXList,N1),\r\n" + "	(S > 0 -> N $= N1 * 0.1; N $= -N1 * 0.1).\r\n"
				+ "toFloat([],0).\r\n" + "toFloat([X|X1],N):-\r\n" + "	X #>= 48,\r\n" + "	X #=< 57,\r\n"
				+ "	N $= N1 *0.1 +  (X - 48),\r\n" + "	toFloat(X1,N1).\r\n";

		// toReal 中用到的 splitTwoPart
		returnstr += "splitTwoPart([XSize|XList],0,[XSize|XList],[0|[]]).\r\n"
				+ "splitTwoPart([XSize|XList],1,[0|[]],[XSize1|XList1]):-\r\n"
				+ "	ocl_string_substring([XSize|XList],2,XSize,[XSize1|XList1]).\r\n"
				+ "/*splitTwoPart([XSize|XList],splitIndex,[XSize1|XSize1],[0|[]]):-\r\n"
				+ "	Index0 #= splitIndex - 1,\r\n" + "	Index1 #= splitIndex + 1,\r\n" + "	Index1 #> XSize,\r\n"
				+ "	ocl_string_substring([XSize|XList],1,Index0,[XSize1|XList1]).\r\n"
				+ "splitTwoPart([XSize|XList],splitIndex,[XSize1|XSize1],[XSize2|XList2]):-\r\n"
				+ "	Index0 #= splitIndex - 1,\r\n" + "	Index1 #= splitIndex + 1,\r\n" + "	Index1 #<= XSize,\r\n"
				+ "	ocl_string_substring([XSize|XList],1,Index0,[XSize1|XList1]),\r\n"
				+ "	ocl_string_substring([XSize|XList],Index1,XSize,[XSize2|XList2]).*/\r\n";

		return returnstr;

	}

	public String booleanMethodCLP() {
		String returnstr = "delay ocl_boolean_toString(B,[RSize|RList]) if nonground(B).\r\n"
				+ "ocl_boolean_toString(B,[RSize|RList]):-\r\n"
				+ "	((B #= 1,string_list(\"true\",RList),length(RList,RSize));\r\n"
				+ "	(B #= 0,string_list(\"false\",RList),length(RList,RSize))).";
		return returnstr;

	}

	public String collectionMethodCLP() {
		String returnstr = "";

		// at、indexOf 會用到，會牽連到 string type 的 test_sub_B
		returnstr += "delay ocl_collection_substring([LengthC|OrderedCollectionC],Lower,Upper,_) if var(LengthC).\r\n"
				+ "ocl_collection_substring([LengthC|OrderedCollectionC],Lower,Upper,[LengthResult|OrderedCollectionResult]):-\r\n"
				+ "	Lower #>=1,\r\n" + "	LengthResult #= Upper -Lower +1,\r\n" + "	LengthC #>= Upper,\r\n"
				+ "	Upper #>= Lower,\r\n" + "	LengthC #>= LengthResult,\r\n"
				+ "	test_sub_B(OrderedCollectionC,OrderedCollectionResult,Lower,LengthResult).\r\n";

		// at
		returnstr += "ocl_collection_at([X|XList],Position,OrderedCollectionResult):-\r\n" + "	LengthResult #=1,\r\n"
				+ "	ocl_collection_substring([X|XList],Position,Position,[LengthResult|[OrderedCollectionResult]]).\r\n";

		// size
		returnstr += "ocl_collection_size([XSize|XList],Result):-\r\n" + "	Result = XSize.\r\n";

		return returnstr;
	}
}
