package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Сохраняет значение в ТОЛЬКО ЛОКАЛЬНУЮ переменную в VM
 */
public class VmInstrStoreL implements VmInstr {
    // имя переменной
    private final String name;

    public VmInstrStoreL(String name) {
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        frame.getValues().put(name, vm.pop());
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
