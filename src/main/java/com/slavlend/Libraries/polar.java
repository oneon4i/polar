package com.slavlend.Libraries;

import com.slavlend.Polar.PolarClass;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Logger.PolarLogger;

/*
Библиотека ant, необходима для более глубокого
понимания типизации и рефлексии.
 */
@SuppressWarnings("unused")
public class polar {
    // получение имени класса
    public PolarValue name(PolarValue o) {
        if (o.isNumber()) { return new PolarValue("num"); }
        if (o.isBool()) { return new PolarValue("bool"); }
        if (o.isClass()) { return new PolarValue("class"); }
        if (o.isFunc()) { return new PolarValue("func"); }
        if (o.isObject()) { return new PolarValue(o.asObject().getClazz().getName()); }
        if (o.isReflected()) { return new PolarValue("reflected"); }
        if (o.isString()) { return new PolarValue("string"); }

        return new PolarValue(o.asObject().getClazz().getName());
    }

    // создание класса из объекта
    public PolarValue from(PolarValue name, PolarValue args) {
        // класс
        PolarClass _clazz = null;

        // ищем
        for (PolarClass clazz : Classes.getInstance().getClasses()) {
            if (clazz.getName().equals(name.asString())) {
                _clazz = clazz;
            }
        }

        // проверяем класс на нулл
        if (_clazz == null) {
            PolarLogger.exception("Class Not Found: " + name, name.getInstantiateAddress());
        }

        PolarObject obj = PolarObject.create(_clazz, args.asList());

        return new PolarValue(obj);
    }
}
