package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
Помещение значения в стек VM
 */
@Getter
public class VmInstrIf implements VmInstr, VmInstrContainer {
    private final List<VmInstr> condInstructions = new ArrayList<>();
    private final List<VmInstr> instructions = new ArrayList<>();
    private VmInstrIf elseCondition;
    @Setter
    private boolean writingConditions;

    public VmInstrIf() {

    }
    public void setElse(VmInstrIf _else) {
        this.elseCondition = _else;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        for (VmInstr instr : condInstructions) {
            instr.run(vm, frame);
        }
        Boolean bool = (Boolean) vm.pop();
        if (bool) {
            for (VmInstr instr : instructions) {
                instr.run(vm, frame);
            }
        } else {
            if (elseCondition != null) {
                elseCondition.run(vm, frame);
            }
        }
    }

    @Override
    public void visitInstr(VmInstr instr) {
        if (isWritingConditions()) {
            this.condInstructions.add(instr);
        }
        else {
            this.instructions.add(instr);
        }
    }

    @Override
    public String toString() {
        return "IF()";
    }

    @Override
    public void print() {
        System.out.println("╭────if/elif/else block────╮");
        System.out.println("[conds]");
        for (VmInstr instr : condInstructions) {
            instr.print();
        }
        System.out.println("[endconds]");
        for (VmInstr instr : instructions) {
            instr.print();
        }
        System.out.println("╰──────────────────────────╯");
        if (elseCondition != null) {
            elseCondition.print();
        }
    }
}
