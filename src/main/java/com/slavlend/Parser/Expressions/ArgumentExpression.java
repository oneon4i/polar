package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

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

    }

    public ArgumentExpression(String data) {
        this.data = data;
    }
}
