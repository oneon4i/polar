package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Помещение переменной в VM
 */
public class VmInstrLoad implements VmInstr {
    private final String name;
    private final boolean hasPrevious;

    public VmInstrLoad(String name, boolean hasPrevious) {
        this.name = name; this.hasPrevious = hasPrevious;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasPrevious) {
            vm.load(frame, name);
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                vm.push(vmObj.getScope().lookup(name));
            } else {
                vm.push(((VmClass) last).getModValues().lookup(name));
            }
        }
    }
}
