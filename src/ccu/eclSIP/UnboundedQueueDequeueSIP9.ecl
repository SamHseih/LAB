:- lib(ic).
:- lib(timeout).
unboundedQueueDequeue_19([Data_pre,Size_pre],[],[Data,Size],[],[],[]):-
Size_pre#=x,
,Size#>=0.
testUnboundedQueueDequeue(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[Size_pre]:: -32768..32767,
[Size]:: -32768..32767,
Size_pre#=x,
dcl_1dInt_array(Data_pre, Size_pre),
length(Data,Size_pre),
List1_pre=Data_pre,
Obj_pre=[Data_pre,Size_pre],
Obj=[Data,Size],
unboundedQueueDequeue_19(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
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

