package com.slavlend.Compiler;

import com.slavlend.Vm.IceVm;
import com.slavlend.Vm.VmCode;

/*
Компилятор в VM инструкции
 */
public class Compiler {
    public static VmCode code = new VmCode();
    public static IceVm iceVm = new IceVm();

    public static void exec() {
        iceVm.run(code);
    }
}
