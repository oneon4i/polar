package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmFunction;
import com.slavlend.VM.VmInstr;

/*
Помещение переменной в VM
 */
public class VmInstrCall implements VmInstr {
    private final String name;

    public VmInstrCall(String name) {
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        ((VmFunction)frame.lookup(name)).exec(vm);
    }
}
