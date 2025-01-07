package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Удаляет локальную переменную в ВМ
 */
public class VmInstrDelL implements VmInstr {
    // имя переменной
    private final String name;

    public VmInstrDelL(String name) {
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        frame.getValues().remove(name);
    }

    @Override
    public String toString() {
        return "DELL(" + name + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
