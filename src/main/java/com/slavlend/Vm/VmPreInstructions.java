package com.slavlend.Vm;

import lombok.Getter;

import java.util.ArrayList;

/*
Пре инструкции, выполняются перед выполнением
основных. Используются, например для работы с классами,
до непосредственного их использования.
 */
@Getter
public class VmPreInstructions implements VmInstrContainer {
    // пре-инструкции
    private final ArrayList<VmInstr> instructions = new ArrayList<>();

    @Override
    public void visitInstr(VmInstr instr) {
        instructions.add(instr);
    }
}
