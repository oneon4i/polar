package com.slavlend.VM;

import lombok.Getter;

import java.util.List;

/*
Класс
 */
@Getter
public class VmClass implements VmInstrContainer {
    // имя класса
    private final String name;
    // функции
    private final VmFrame<VmFunction> functions = new VmFrame<>();
    // модульные значения
    private final VmFrame<Object> modValues = new VmFrame<>();

    public VmClass(String name) {
        this.name = name;
    }

    @Override
    public void visitInstr(VmInstr instr) {
        // not implemented
    }
}
