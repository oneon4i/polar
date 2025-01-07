package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;
import lombok.Getter;

/*
Помещение инстанса класса в VM
 */
@SuppressWarnings("ClassCanBeRecord")
@Getter
public class VmInstrNewObj implements VmInstr {
    private final String className;
    private final VmVarContainer args;

    public VmInstrNewObj(String className, VmVarContainer args) {
        this.className = className;
        this.args = args;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        // конструктор
        passArgs(vm, frame);
        vm.push(new VmObj(vm, vm.getClasses().lookup(className)));
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "INST(" + className + "," + args.getVarContainer().size() + ")";
    }

    private void passArgs(IceVm vm, VmFrame<Object> frame) {
        for (VmInstr instr : args.getVarContainer()) {
            instr.run(vm, frame);
        }
    }
}
