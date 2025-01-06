package com.slavlend.VM.Instructions;

import com.slavlend.Parser.Operator;
import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmFunction;
import com.slavlend.VM.VmInstr;

import java.util.ArrayList;
import java.util.List;

/*
Помещение значения в стек VM
 */
public class VmInstrCond implements VmInstr {
    private List<VmInstr> instructions = new ArrayList<>();

    public VmInstrCond() {

    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        Boolean bool = (Boolean) vm.pop();
        if (bool) {
        } else {

        }
    }

    public void visitInstr(VmInstr instr) {
        this.instructions.add(instr);
    }
}
