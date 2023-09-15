:- lib(ic).
:- lib(timeout).
boundedStackBoundedStack_5([],[B_pre],[Data,Bound,Size],[B],[],[]):-
,
B=B_pre,Bound#>=0,Size#>=0,Size#=<Bound.
testBoundedStackBoundedStack(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[B_pre]:: -32768..32767,
[Bound,Size]:: -32768..32767,
[B]:: -32768..32767,
Arg_pre=[B_pre],
Obj=[Data,Bound,Size],
Arg=[B],
boundedStackBoundedStack_5(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling_1dInt_array(Data_pre),
labeling(Arg_pre),
labeling(Arg).
delay dcl_1dInt_array(_, Size) if nonground([Size]).
dcl_1dInt_array([], 0).
dcl_1dInt_array([X|L], Size) :-
    Size > 0,
    X :: 0..32767,
    Size1 is Size - 1,
    dcl_1dInt_array(L, Size1).

