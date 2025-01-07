package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;
import lombok.Getter;

/*
Вызов функции в VM
 */
@SuppressWarnings("ClassCanBeRecord")
@Getter
public class VmInstrCall implements VmInstr {
    // имя
    private final String name;
    // есть ли предыдущий аксесс
    private final boolean hasPrevious;
    // аргументы
    private final VmVarContainer args;

    // конструктор
    public VmInstrCall(String name, VmVarContainer args, boolean hasPrevious) {
        this.name = name; this.args = args; this.hasPrevious = hasPrevious;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasPrevious) {
            passArgs(vm, frame);
            vm.callGlobal(name);
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                passArgs(vm, frame);
                vmObj.call(name, vm);
            } else {
                passArgs(vm, frame);
                ((VmClass)last).getFunctions().lookup(name).exec(vm);
            }
        }
    }

    // помещает аргументы в стек
    private void passArgs(IceVm vm, VmFrame<Object> frame) {
        for (VmInstr instr : args.getVarContainer()) {
            instr.run(vm, frame);
        }
    }

    @Override
    public String toString() {
        return "CALL[" + name  + "]" + "(" + args.getVarContainer().size() + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
