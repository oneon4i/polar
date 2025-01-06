package com.slavlend.VM;

import lombok.Getter;

import java.util.List;

/*
Функция вм
 */
@Getter
public class VmFunction {
    // имя функции
    private final String name;
    // инструкции
    private List<VmInstr> instructions;
    // скоуп
    private final VmFrame<Object> scope = new VmFrame<Object>();

    public VmFunction(String name) {
        this.name = name;
    }

    public void add(VmInstr instr) {
        instructions.add(instr);
    }

    public void exec(IceVm vm) {
        for (VmInstr instr : instructions) {
            instr.run(vm, scope);
        }
    }

    public VmFunction copy() {
        VmFunction fn = new VmFunction(name);
        fn.instructions = instructions;
        return fn;
    }
}
