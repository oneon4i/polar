package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmClass;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Сохраняет значение в МОДУЛЬНУЮ ПЕРЕМЕННУЮ класса в ВМ
 */
public class VmInstrStoreM implements VmInstr {
    // класс
    private final VmClass vmClass;
    // имя переменной
    private final String name;

    public VmInstrStoreM(VmClass vmClass, String name) {
        this.vmClass = vmClass;
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        vmClass.getModValues().set(name, vm.pop());
    }

    @Override
    public String toString() {
        return "STORE_M(" + name + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
