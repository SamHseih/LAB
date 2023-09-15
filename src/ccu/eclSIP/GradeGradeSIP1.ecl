:- lib(ic).
:- lib(timeout).
gradeGrade_4([],[],[],[],[],[]):-
.
testGradeGrade(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
gradeGrade_4(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Arg_pre),
labeling(Arg).

