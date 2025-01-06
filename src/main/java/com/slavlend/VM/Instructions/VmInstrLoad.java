package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;

/*
Помещение переменной в VM
 */
public class VmInstrLoad implements VmInstr {
    private final String name;
    private final boolean hasNext;

    public VmInstrLoad(String name, boolean hasNext) {
        this.name = name; this.hasNext = hasNext;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasNext) {
            if (vm.getStack().isEmpty()) {
                vm.push(frame.lookup(name));
            } else if (vm.getStack().pop() instanceof HasNext o) {
                if (o.getValue() instanceof VmClass) {
                    vm.push(((VmClass) o.getValue()).getModValues().lookup(name));
                } else if (o.getValue() instanceof VmObj) {
                    vm.push(((VmObj) o.getValue()).getScope().lookup(name));
                } else if (o.getValue() instanceof VmFunction) {
                    ((VmFunction) o.getValue()).exec(vm);
                }
                else {
                    throw new RuntimeException("invalid object: " + o);
                }
            }
        } else {
            if (vm.getStack().isEmpty()) {
                vm.push(new HasNext(frame.lookup(name)));
            } else if (vm.getStack().pop() instanceof HasNext) {
                Object o = ((HasNext)vm.getStack().pop()).getValue();
                if (o instanceof VmClass) {
                    vm.push(new HasNext(((VmClass) o).getModValues().lookup(name)));
                } else if (o instanceof VmObj) {
                    vm.push(new HasNext(((VmObj) o).getScope().lookup(name)));
                }
                else {
                    throw new RuntimeException("invalid object: " + o);
                }
            }
        }
    }
}
