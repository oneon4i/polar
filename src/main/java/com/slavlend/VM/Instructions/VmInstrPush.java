package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Помещение значения в стек VM
 */
public class VmInstrPush implements VmInstr {
    private final Object data;

    public VmInstrPush(Object data) {
        this.data = data;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        vm.push(data);
    }

    @Override
    public String toString() {
        return "PUSH(" + data + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
