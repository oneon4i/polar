package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Удаление верхнего значения из стека VM
 */
public class VmInstrPop implements VmInstr {
    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        vm.pop();
    }

    @Override
    public String toString() {
        return "POP()";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
