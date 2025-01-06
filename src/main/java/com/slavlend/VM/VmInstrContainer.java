package com.slavlend.VM;

/*
Контейнер для инструкций
 */
public interface VmInstrContainer {
    abstract void visitInstr(VmInstr instr);
}
