package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Сохраняет значение в переменную в VM
 */
public class VmInstrStore implements VmInstr {
    // имя переменной
    private final String name;
    // есть ли предыдущий аксесс
    private final boolean hasPrevious;

    public VmInstrStore(String name, boolean hasPrevious) {
        this.name = name;
        this.hasPrevious = hasPrevious;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasPrevious) {
            frame.set(name, vm.getStack().get().pop());
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                vmObj.getScope().set(name, vm.getStack().get().pop());
            } else {
                ((VmClass) last).getModValues().set(name, vm.getStack().get().pop());
            }
        }
    }

    @Override
    public String toString() {
        return "STORE(" + name + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
