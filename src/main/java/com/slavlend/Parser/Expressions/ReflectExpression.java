package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Polar.JvmClasses;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Reflected;
import lombok.Getter;

/*
Экспрешенн рефлексии -> создание
Java класса с использованием рефлексии
 */
@SuppressWarnings("DataFlowIssue")
@Getter
public class ReflectExpression implements Expression {
    // имя класса для создания объекта
    private final String className;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(new Reflected(address, JvmClasses.lookup(address, className)));
    }

    @Override
    public Address address() {
        return address;
    }

    public ReflectExpression(String className) {
        this.className = className;
    }
}