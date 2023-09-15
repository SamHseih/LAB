:- lib(ic).
:- lib(timeout).
timeTime_70([],[H_pre,M_pre,S_pre],[Hour,Minute,Second],[H,M,S],[],[]):-
H_pre#=<23,
M_pre#>=0,
M_pre#>59,
S_pre#<0,
S_pre#>59,
H=H_pre,
M=M_pre,
S=S_pre,Hour#>=0,
Hour#=<23,
Minute#>=0,
Minute#=<59,
Second#>=0,
Second#=<59.
testTimeTime(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[H_pre,M_pre,S_pre]:: -32768..32767,
[Hour,Minute,Second]:: -32768..32767,
[H,M,S]:: -32768..32767,
Arg_pre=[H_pre,M_pre,S_pre],
Obj=[Hour,Minute,Second],
Arg=[H,M,S],
timeTime_70(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Arg_pre),
labeling(Arg).

