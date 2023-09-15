:- lib(ic).
:- lib(timeout).
triangleEquals_5([SideA_pre,SideB_pre,SideC_pre],[T_SideA_pre,T_SideB_pre,T_SideC_pre],[SideA,SideB,SideC],[T_SideA,T_SideB,T_SideC],[],[]):-
,
SideA=SideA_pre,
SideB=SideB_pre,
SideC=SideC_pre,
T_SideA=T_SideA_pre,
T_SideB=T_SideB_pre,
T_SideC=T_SideC_pre,(SideA+SideB)#>SideC,
(SideB+SideC)#>SideA,
(SideA+SideC)#>SideB,
SideA#>0,
SideB#>0,
SideC#>0.
testTriangleEquals(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-
[SideA_pre,SideB_pre,SideC_pre]:: -32768..32767,
[SideA,SideB,SideC]:: -32768..32767,
Obj_pre=[SideA_pre,SideB_pre,SideC_pre],
Arg_pre=[T_SideA_pre,T_SideB_pre,T_SideC_pre],
Obj=[SideA,SideB,SideC],
Arg=[T_SideA,T_SideB,T_SideC],
triangleEquals_5(Obj_pre,Arg_pre,Obj,Arg,Result,Exception),
labeling(Obj_pre),
labeling(Obj),labeling(Arg_pre),
labeling(Arg).

