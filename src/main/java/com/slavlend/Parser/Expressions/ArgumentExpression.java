package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.VM.Instructions.VmInstrPush;

/*
Экспрешен аргумента в функции
 */
public class ArgumentExpression implements Expression {
    // данные
    public String data;
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(data);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrPush(data));
    }

    public ArgumentExpression(String data) {
        this.data = data;
    }
}
