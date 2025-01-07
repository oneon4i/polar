package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Ломает текущее исполнение
 */
public class VmInstrRet extends RuntimeException implements VmInstr {
    @Override
    public void run(IceVm vm, VmFrame<Object> scope) {
        throw this;
    }

    @Override
    public String toString() {
        return "RET()";
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
}
