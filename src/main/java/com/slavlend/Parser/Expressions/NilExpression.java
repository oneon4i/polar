package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrPush;
import lombok.Getter;
import lombok.Setter;

/*
Нил экспрешенн - возвращает нулл
 */
@Getter
public class NilExpression implements Expression {
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), null));
    }

    public NilExpression() {
    }
}
