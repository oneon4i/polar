package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Помещение переменной в VM
 */
public class VmInstrStore implements VmInstr {
    private final String name;
    private final boolean hasPrevious;

    public VmInstrStore(String name, boolean hasPrevious) {
        this.name = name;
        this.hasPrevious = hasPrevious;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasPrevious) {
            frame.set(name, vm.getStack().pop());
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                vmObj.getScope().set(name, vm.getStack().pop());
            } else {
                ((VmClass) last).getModValues().set(name, vm.getStack().pop());
            }
        }
    }
}
