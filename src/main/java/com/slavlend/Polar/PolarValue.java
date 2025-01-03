package com.slavlend.Polar;

import com.slavlend.App;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Libraries.array;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.BreakStatement;
import com.slavlend.Parser.Statements.ClassStatement;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Parser.Statements.NextStatement;

import java.util.ArrayList;

/*
Значение - часть переменной, хранит
внутри себя объект.
 */
public class PolarValue extends RuntimeException {
    // хранимый объект
    public Object data;
    // адресс из кода
    public Address instantiateAddress = App.parser.address();

    // конструктор
    public PolarValue(Object data) {
        this.data = data;
    }

    // возвращает объект как список
    public ArrayList<PolarValue> asList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        Reflected reflected = asObject().classValues.get("arr").asObject().classValues.get("javaArr").asReflected();
        array arr = (array) reflected.o;

        return arr.valueArrayList;
    }

    // создаёт список из джава списка
    public static PolarValue toList(ArrayList<PolarValue> list) {
        // аррэй
        PolarObject array = new PolarObject(Classes.Instance.getClass("Array"), new ArrayList<>());
        array.init();

        // добавляем в список
        Reflected reflected = array.classValues.get("arr").asObject().classValues.get("javaArr").asReflected();
        array arr = (array) reflected.o;

        arr.valueArrayList.addAll(list);

        // возвращаем
        return new PolarValue(array);
    }

    // вовзращает объект как строку
    public String asString() {
        try {
            // если объект - строка, возвращаем
            return (String) data;
        } catch (Exception e) {
            if (data instanceof PolarObject polarObject) {
                return polarObject.asString();
            }
            return data.toString();
        }
    }

    // возвращает объект как число
    public Float asNumber() {
        try {
            // если объект - число, возвращаем
            return (Float) data;
        } catch (Exception e) {
            // в ином случае вызываем
            // ошибку
            PolarLogger.exception("Cannot Parse " + data + " To Num", instantiateAddress);
            return null;
        }

    }

    // возвращает объект как бул
    public Boolean asBool() {
        try {
            // если объект - бул, возвращаем
            return (Boolean) data;
        } catch (Exception e) {
            // в ином случае вызываем
            // ошибку
            PolarLogger.exception("Cannot Parse " + data + " To Bool", instantiateAddress);
            return null;
        }
    }

    // возвращает объект как рефлектед (java-like)
    public Reflected asReflected() {
        try {
            // если объект - рефлектед (java-like), возвращаем
            return (Reflected) data;
        } catch (Exception e) {
            // в ином случае вызываем
            // ошибку
            PolarLogger.exception("Cannot Parse " + data + " To Reflected", instantiateAddress);
            return null;
        }
    }

    // возвращает объект как объект языка
    public PolarObject asObject() {
        try {
            // если объект - объект языка, возвращаем
            return (PolarObject) data;
        } catch (Exception e) {
            // в ином случае вызываем
            // ошибку
            PolarLogger.exception("Cannot Parse " + data + " To Egg Object", instantiateAddress);
            return null;
        }
    }

    // возвращает объект как функцию языка
    public FunctionStatement asFunc() {
        try {
            // если объект - объект функция, возвращаем
            return (FunctionStatement) data;
        } catch (Exception e) {
            // в ином случае вызываем
            // ошибку
            PolarLogger.exception("Cannot Parse " + data + " To Function", instantiateAddress);
            return null;
        }
    }

    // возвращает объект как класс
    public PolarClass asClass() {
        try {
            // если объект - объект класса, возвращаем
            return (PolarClass) data;
        } catch (Exception e) {
            // в ином случае вызываем
            // ошибку
            PolarLogger.exception("Cannot Parse " + data + " To Class", instantiateAddress);
            return null;
        }
    }

    // это строка -> бул
    public boolean isString() {
        return data instanceof String;
    }

    // это число -> бул
    public boolean isNumber() {
        return data instanceof Float;
    }

    // это бул -> бул
    public boolean isBool() {
        return data instanceof Boolean;
    }

    // это рефлектед (java-like) -> бул
    public boolean isReflected() {
        return data instanceof Reflected;
    }

    // это объект языка -> бул
    public boolean isObject() {
        return data instanceof PolarObject;
    }

    // это функция языка -> бул
    public boolean isFunc() {
        return data instanceof FunctionStatement;
    }

    // это брэйк цикла -> бул
    public boolean isBreak() {
        return data instanceof BreakStatement;
    }

    // это неекст цикла -> бул
    public boolean isNext() {
        return data instanceof NextStatement;
    }

    // это класс -> бул
    public boolean isClass() {
        return data instanceof ClassStatement;
    }

    // сравнивает значения по значению объекта
    public boolean equal(PolarValue obj) {
        if (obj.isString() && isString()) {
            return asString().equals(obj.asString());
        }
        else if (obj.isObject() && isObject()) {
            return asObject().equals(obj.asObject());
        }
        else if (obj.isReflected() && isReflected()) {
            return asReflected().equals(obj.asReflected());
        }
        else if (obj.isBool() && isBool()) {
            return asBool().equals(obj.asBool());
        }
        else if (obj.isNumber() && isNumber()) {
            return asNumber().equals(obj.asNumber());
        }
        else if (obj.isClass() && isClass()) {
            return asClass().equals(obj.asClass());
        }
        else {
            PolarLogger.exception("Cannot Compare: "
                    + obj.data.getClass().getSimpleName()
                    + " and "
                    + this.data.getClass().getSimpleName(),
                    obj.instantiateAddress
            );
            return false;
        }
    }


    // сравнивает значения по значению объекта с адрессом сравнения
    public boolean equal(Address address, PolarValue obj) {
        if (obj.isString() && isString()) {
            return asString().equals(obj.asString());
        }
        else if (obj.data == null && data != null) {
            return false;
        }
        else if (obj.data != null && data == null) {
            return false;
        }
        else if (obj.isString() && data == null) {
            return false;
        }
        else if (obj.data == null && isString()) {
            return false;
        }
        else if (obj.isObject() && isObject()) {
            return asObject().equals(obj.asObject());
        }
        else if (obj.isReflected() && isReflected()) {
            return asReflected().equals(obj.asReflected());
        }
        else if (obj.isBool() && isBool()) {
            return asBool().equals(obj.asBool());
        }
        else if (obj.isNumber() && isNumber()) {
            return asNumber().equals(obj.asNumber());
        }
        else if (obj.isClass() && isClass()) {
            return asClass().equals(obj.asClass());
        }
        else {
            String first = obj.data != null ? obj.data.getClass().getSimpleName() : null;
            String second = this.data != null ? this.data.getClass().getSimpleName() : null;
            PolarLogger.exception("Cannot Compare: "
                            + first
                            + " and "
                            + second,
                    address
            );
            return false;
        }
    }
}
