package com.slavlend.Libraries;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Env.PolarEnv;
import com.slavlend.Parser.Statements.ClassStatement;

/*
Библиотека ant, необходима для более глубокого
понимания типизации и рефлексии.
 */
public class polar {
    // получение имени класса
    public PolarValue name(PolarValue o) {
        if (o.isNumber()) { return new PolarValue("num"); }
        if (o.isBool()) { return new PolarValue("bool"); }
        if (o.isClass()) { return new PolarValue("class"); }
        if (o.isFunc()) { return new PolarValue("func"); }
        if (o.isObject()) { return new PolarValue(o.asObject().clazz.name); }
        if (o.isReflected()) { return new PolarValue("reflected"); }
        if (o.isString()) { return new PolarValue("string"); }

        return new PolarValue(o.asObject().clazz.name);
    }

    // создание класса из объекта
    public PolarValue from(PolarValue name, PolarValue args) {
        // класс
        ClassStatement _clazz = null;

        // ищем
        for (ClassStatement clazz : Classes.getInstance().classes) {
            if (clazz.name.equals(name.asString())) {
                _clazz = clazz;
            }
        }

        // проверяем класс на нулл
        if (_clazz == null) {
            PolarEnv.Crash("Class Not Found: " + name, name.instantiateAddress);
        }

        PolarObject obj = PolarObject.create(_clazz, args.asList());

        return new PolarValue(obj);
    }
}
