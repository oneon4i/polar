package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

/*
Булевый экспрешен
 */
public class BoolExpression implements Expression {
    // данные
    public String data;
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(Boolean.parseBoolean(data));
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }

    public BoolExpression(String data) {
        this.data = data;
    }

}
