package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrLoopEnd;
import lombok.Getter;
import lombok.Setter;

/*
Брэйк стэйтмент - останавливает цикл
 */
@Setter
@Getter
public class BreakStatement extends RuntimeException implements Statement {
    // адресс
    private Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrLoopEnd(address.convert(),false));
    }
}
