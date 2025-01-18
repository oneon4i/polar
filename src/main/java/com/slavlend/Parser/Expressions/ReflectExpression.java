package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Reflected;
import lombok.Getter;

/*
Экспрешенн рефлексии -> создание
Java класса с использованием рефлексии
 */
@Getter
public class ReflectExpression implements Expression {
    // имя класса для создания объекта
    private final String className;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        try {
            return new PolarValue(new Reflected(address, Class.forName(className)));
        }
        catch (ClassNotFoundException e) {
            PolarLogger.exception("Reflection Exception: " + e, address);
            return new PolarValue(null);
        }
    }

    @Override
    public Address address() {
        return address;
    }

    public ReflectExpression(String className) {
        this.className = className;
    }
}