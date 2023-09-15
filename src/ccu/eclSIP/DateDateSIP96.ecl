:- lib(ic).
:- lib(timeout).
dateDate_202([],[Y_pre,M_pre,D_pre],[Year,Month,Day],[Y,M,D],[],[]):-
M_pre#>=1,
M_pre#=<12,
D_pre#>=1,
M_pre#\=1,
M_pre#\=3,
M_pre#\=5,
M_pre#\=7,
M_pre#\=8,
M_pre#\=10,
M_pre#\=12,
M_pre#\=4,
M_pre#\=6,
M_pre#\=9,
M_pre#\=11,
o_mod(Y_pre,400,Remainder),Remainder#=0,
o_mod(Y_pre,4,Remainder_1),Remainder_1#\=0,
Y=Y_pre,
M=M_pre,
D=D_pre,Year#>=1,
Month#>=1,
Month#=<12,
Day#>=1.
testDateDate(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[Y_pre,M_pre,D_pre]:: -32768..32767,
[Year,Month,Day]:: -32768..32767,
[Y,M,D]:: -32768..32767,
Arg_pre=[Y_pre,M_pre,D_pre],
Obj=[Year,Month,Day],
Arg=[Y,M,D],
dateDate_202(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Arg_pre),
labeling(Arg).
delay o_mod(M,N,_) if nonground([M,N]).
o_mod(M,N,R):-
mod(M,N,R).

