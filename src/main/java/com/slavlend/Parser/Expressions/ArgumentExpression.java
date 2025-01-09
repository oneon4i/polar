package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrPush;

/*
Экспрешен аргумента в функции
 */
public class ArgumentExpression implements Expression {
    // данные
    public String data;
    // адресс
    private Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), data));
    }

    public ArgumentExpression(String data) {
        this.data = data;
    }
}
