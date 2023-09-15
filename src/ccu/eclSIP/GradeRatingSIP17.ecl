:- lib(ic).
:- lib(timeout).
gradeRating_81([],[N_pre],[],[N],[],[]):-
N_pre#=<100,
N_pre#>59,
N_pre#>69,
N_pre#>=60,
N_pre#>=70,
N_pre#>79,
N_pre#<80,
N=N_pre.
testGradeRating(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[N_pre]:: -32768..32767,
[N]:: -32768..32767,
Arg_pre=[N_pre],
Arg=[N],
gradeRating_81(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Arg_pre),
labeling(Arg).

