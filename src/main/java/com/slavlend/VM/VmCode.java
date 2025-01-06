package com.slavlend.VM;

import com.slavlend.Compiler.Compiler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
Код поступающий на вход
 */
@Getter
public class VmCode {
    private final List<VmInstr> instructions = new ArrayList<>();
    private VmInstrContainer writeTo = null;

    public VmCode() {

    }

    public void visitInstr(VmInstr instr) {
        if (Compiler.iceVm.getWriteTo() == null) {
            this.instructions.add(instr);
        } else {
            Compiler.iceVm.getWriteTo().visitInstr(instr);
        }
    }
}
