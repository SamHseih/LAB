:- lib(ic).
:- lib(timeout).
boundedQueueEnqueue_744([Data_pre,Bound_pre,Size_pre],[Value_pre],[Data,Bound,Size],[Value],[],[]):-
Size_pre#=4,
,
Value=Value_pre,Bound#>=0,Size#>=0,Size#=<Bound.
testBoundedQueueEnqueue(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[Bound_pre,Size_pre]:: -32768..32767,
[Value_pre]:: -32768..32767,
[Bound,Size]:: -32768..32767,
[Value]:: -32768..32767,
Size_pre#=4,
dcl_1dInt_array(Data_pre, Size_pre),
length(Data,Size_pre),
List1_pre=Data_pre,
Obj_pre=[Data_pre,Bound_pre,Size_pre],
Arg_pre=[Value_pre],
Obj=[Data,Bound,Size],
Arg=[Value],
boundedQueueEnqueue_744(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling_1dInt_array(Data_pre),
labeling(Arg_pre),
labeling(Arg).
labeling_1dInt_array([]).
labeling_1dInt_array([X|L]) :-
    indomain(X, random),
    labeling_1dInt_array(L).
delay dcl_1dInt_array(_, Size) if nonground([Size]).
dcl_1dInt_array([], 0).
dcl_1dInt_array([X|L], Size) :-
    Size > 0,
    X :: 0..32767,
    Size1 is Size - 1,
    dcl_1dInt_array(L, Size1).
