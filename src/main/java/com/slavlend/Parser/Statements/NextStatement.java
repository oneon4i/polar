package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrLoopEnd;
import lombok.Getter;

/*
Некст стэйтмент - итерирует forStatement
 */
@Getter
public class NextStatement extends RuntimeException implements Statement {
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Statement copy() {
        return new NextStatement();
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrLoopEnd(address.convert(), true));
    }
}
