package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Помещение переменной по имени в стек VM
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
            try {
                vm.load(frame, name);
            } catch (RuntimeException e) {
                if (vm.getClasses().has(name)) {
                    vm.push(vm.getClasses().lookup(name));
                } else if (vm.getFunctions().has(name))  {
                    vm.push(vm.getFunctions().lookup(name));
                } else {
                    throw new RuntimeException("not found: " + name);
                }
            }
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                if (vmObj.getScope().has(name)) {
                    vm.push(vmObj.getScope().lookup(name));
                } else if (vmObj.getClazz().getFunctions().has(name)) {
                    vm.push(vmObj.getClazz().getFunctions().lookup(name));
                }
            } else {
                VmClass clazz = (VmClass) last;
                if (clazz.getModValues().has(name)) {
                    vm.push(clazz.getModValues().lookup(name));
                } else if (clazz.getModFunctions().has(name)){
                    vm.push(clazz.getModFunctions().lookup(name));
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "LOAD(" + name + ")";
    }
}
