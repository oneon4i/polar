package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Polar.PolarValue;
import lombok.Getter;

/*
Текстовый экспрешенн - возвращает строку
 */
@Getter
public class TextExpression implements Expression {
    // данные
    private final String data;

    // адресс
    private final Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(data);
    }

    @Override
    public Address address() {
        return address;
    }

    public TextExpression(String data) {
        this.data = data;
    }
}
