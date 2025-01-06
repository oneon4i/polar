package com.slavlend.VM.Instructions;

import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.VM.*;
import lombok.Getter;

import java.util.ArrayList;

/*
Помещение переменной в VM
 */
@Getter
public class VmInstrCall implements VmInstr {
    private final String name;
    private final boolean hasPrevious;
    private final ArrayList<Expression> params;

    public VmInstrCall(String name, ArrayList<Expression> params, boolean hasPrevious) {
        this.name = name; this.params = params; this.hasPrevious = hasPrevious;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasPrevious) {
            vm.call(name);
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                vmObj.call(name, vm);
            } else {
                ((VmClass)last).getFunctions().lookup(name).exec(vm);
            }
        }
    }
}
