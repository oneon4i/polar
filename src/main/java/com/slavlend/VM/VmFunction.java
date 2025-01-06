package com.slavlend.VM;

import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.VM.Instructions.VmInstrRet;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
Функция вм
 */
@Getter
public class VmFunction implements VmInstrContainer {
    // имя функции
    private final String name;
    // инструкции
    private List<VmInstr> instructions = new ArrayList<>();
    // аргументы
    private ArrayList<ArgumentExpression> arguments;
    // скоуп
    private VmFrame<Object> scope = new VmFrame<Object>();

    public VmFunction(String name, ArrayList<ArgumentExpression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public void add(VmInstr instr) {
        instructions.add(instr);
    }

    public void exec(IceVm vm) {
        scope = new VmFrame<>();
        for (int i = arguments.size()-1; i >= 0; i--) {
            if (i <= 0) {
                return;
            }
            Object arg = vm.pop();
            System.out.println(arg);
            scope.set(arguments.get(i).data, arg);
        }
        for (VmInstr instr : instructions) {
            if (instr instanceof VmInstrRet instrReturn) {
                return;
            }
            instr.run(vm, scope);
        }
    }

    public VmFunction copy() {
        VmFunction fn = new VmFunction(name, arguments);
        fn.instructions = instructions;
        return fn;
    }

    @Override
    public void visitInstr(VmInstr instr) {
        this.instructions.add(instr);
    }
}
