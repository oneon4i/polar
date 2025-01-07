package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Дублирует значение в стек VM
 */
public class VmInstrDup implements VmInstr {
    public VmInstrDup() {

    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        Object o = vm.pop();
        vm.push(o);
        vm.push(o);
    }

    @Override
    public String toString() {
        return "DUP(" + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
