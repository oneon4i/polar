package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Помещение переменной в VM
 */
public class VmInstrReturn implements VmInstr {
    private final String name;

    public VmInstrReturn(String name) {
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        frame.set(name, vm.getStack().pop());
    }
}
