package com.slavlend.VM;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
Код поступающий на вход
 */
@Getter
public class VmCode {
    private final List<VmInstr> instructions = new ArrayList<>();

    public VmCode() {

    }

    public void visitInstr(VmInstr instr) {
        this.instructions.add(instr);
    }
}
