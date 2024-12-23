package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Reflected;
import com.slavlend.Env.PolarEnv;
import com.slavlend.Parser.Address;

/*
Экспрешенн рефлексии -> создание
Java класса с использованием рефлексии
 */
public class ReflectExpression implements Expression {
    // имя класса для создания объекта
    public String className;
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        try {
            return new PolarValue(new Reflected(address, Class.forName(className)));
        }
        catch (ClassNotFoundException e) {
            PolarEnv.Crash("Reflection Exception: " + e.toString(), address);
            return null;
        }
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }

    public ReflectExpression(String className) {
        this.className = className;
    }
}