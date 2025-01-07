package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;
import com.slavlend.VM.VmInstrContainer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
Цикл в VM
 */
@Getter
public class VmInstrLoop implements VmInstr, VmInstrContainer {
    private final List<VmInstr> instructions = new ArrayList<>();

    public VmInstrLoop() {

    }
    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        while (true) {
            try {
                for (VmInstr instr : instructions) {
                    instr.run(vm, frame);
                }
            } catch (VmInstrLoopEnd e) {
                if (e.isOnlyCurrentIteration()) {
                    continue;
                }
                else {
                    break;
                }
            }
        }
    }

    @Override
    public void visitInstr(VmInstr instr) {
        this.instructions.add(instr);
    }

    @Override
    public String toString() {
        return "LOOP()";
    }

    @Override
    public void print() {
        System.out.println("╭────────loop block────────╮");
        for (VmInstr instr : instructions) {
            instr.print();
        }
        System.out.println("╰──────────────────────────╯");
    }
}
