package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Polar.PolarValue;
import lombok.Getter;

/*
Намбер экспрешенн - возвращает число
 */
@Getter
public class NumberExpression implements Expression {
    // данные
    private final String data;

    // адресс
    private final Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(Float.parseFloat(data));
    }

    @Override
    public Address address() {
        return address;
    }

    public NumberExpression(String data) {
        this.data = data;
    }
}
