package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import lombok.Getter;

/*
Нил экспрешенн - возвращает нулл
 */
@Getter
public class NilExpression implements Expression {
    // адресс
    private final Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(null);
    }

    @Override
    public Address address() {
        return address;
    }

    public NilExpression() {
    }
}
