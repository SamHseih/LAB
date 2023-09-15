:- lib(ic).
:- lib(timeout).
triangleTriangle_14([],[Sa_pre,Sb_pre,Sc_pre],[SideA,SideB,SideC],[Sa,Sb,Sc],[],[]):-
(Sb_pre+Sc_pre)#=<Sa_pre,
(Sa_pre+Sc_pre)#>Sb_pre,
Sa_pre#>0,
Sb_pre#>0,
Sa=Sa_pre,
Sb=Sb_pre,
Sc=Sc_pre,(SideA+SideB)#>SideC,
(SideB+SideC)#>SideA,
(SideA+SideC)#>SideB,
SideA#>0,
SideB#>0,
SideC#>0.
testTriangleTriangle(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[Sa_pre,Sb_pre,Sc_pre]:: -32768..32767,
[SideA,SideB,SideC]:: -32768..32767,
[Sa,Sb,Sc]:: -32768..32767,
Arg_pre=[Sa_pre,Sb_pre,Sc_pre],
Obj=[SideA,SideB,SideC],
Arg=[Sa,Sb,Sc],
triangleTriangle_14(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Arg_pre),
labeling(Arg).

