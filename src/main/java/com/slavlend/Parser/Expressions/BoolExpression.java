package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrPush;
import lombok.Getter;

/*
Булевый экспрешен
 */
@Getter
public class BoolExpression implements Expression {
    // данные
    private final String data;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), Boolean.parseBoolean(data)));
    }

    public BoolExpression(String data) {
        this.data = data;
    }

}
