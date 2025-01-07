package com.slavlend.VM;

import com.slavlend.Compiler.Compiler;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Код поступающий на вход
 */
@Getter
public class VmCode {
    private final List<VmInstr> instructions = new ArrayList<>();
    private Stack<VmInstrContainer> writeTo = new Stack<>();

    public VmCode() {

    }

    public void visitInstr(VmInstr instr) {
        if (writeTo.isEmpty()) {
            this.instructions.add(instr);
        } else {
            writeTo.lastElement().visitInstr(instr);
        }
    }

    public void endWrite() {
        this.writeTo.pop();
    }

    public void startWrite(VmInstrContainer c) {
        this.writeTo.push(c);
    }
}
