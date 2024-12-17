package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

/*
Текстовый экспрешенн - возвращает строку
 */
public class TextExpression implements Expression {
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
    public void compile(/*MethodVisitor visitor*/) {
        /*CompilerTypes.visitString(data, visitor);*/
    }

    public TextExpression(String data) {
        this.data = data;
    }
}
