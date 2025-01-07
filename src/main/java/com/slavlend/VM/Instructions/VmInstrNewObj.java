package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;
import com.slavlend.VM.VmObj;
import lombok.Getter;

/*
Удаление значения из VM
 */
@Getter
public class VmInstrNewObj implements VmInstr {
    private final String className;

    public VmInstrNewObj(String className) {
        this.className = className;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        vm.push(new VmObj(vm, vm.getClasses().lookup(className)));
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "INST(" + className + ")";
    }
}
