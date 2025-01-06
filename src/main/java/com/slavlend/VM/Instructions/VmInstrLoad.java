package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Помещение переменной в VM
 */
public class VmInstrLoad implements VmInstr {
    private final String name;

    public VmInstrLoad(String name) {
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        vm.push(frame.lookup(name));
    }
}
