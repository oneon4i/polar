package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

/*
Намбер экспрешенн - возвращает число
 */
public class NumberExpression implements Expression {
    // данные
    public String data;

    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(Float.parseFloat(data));
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile(/*MethodVisitor visitor*/) {
        /*CompilerTypes.visitFloat(Float.parseFloat(data), visitor)*/;
    }

    public NumberExpression(String data) {
        this.data = data;
    }
}
