:- lib(ic).
:- lib(timeout).
gradeRating_4([],[N_pre],[],[N],[],[]):-
N_pre#>100,
N=N_pre.
testGradeRating(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[N_pre]:: -32768..32767,
[N]:: -32768..32767,
Arg_pre=[N_pre],
Arg=[N],
gradeRating_4(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Arg_pre),
labeling(Arg).

