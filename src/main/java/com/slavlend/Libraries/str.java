package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;

import java.util.ArrayList;

/*
Библиотека для углубленной работы со строками
 */
@SuppressWarnings("unused")
public class str {
    // конструктор
    public str() {
    }

    // замена
    public PolarValue replace(PolarValue str, PolarValue what, PolarValue to) {
        return new PolarValue(str.asString().replace(what.asString(), to.asString()));
    }

    // символ на индексе
    public PolarValue at(PolarValue str, PolarValue index) {
        return new PolarValue(String.valueOf(str.asString().charAt(index.asNumber().intValue())));
    }

    // срез выражение по делиметру
    public PolarValue split(PolarValue str, PolarValue delim) {
        if (delim.asString().equals(".")) {
            delim = new PolarValue("\\.");
        }
        String[] strings = str.asString().split(delim.asString());
        ArrayList<PolarValue> values = new ArrayList<>();

        for (String s : strings) {
            values.add(new PolarValue(s));
        }

        return PolarValue.toList(values);
    }

    // в верхний регистр
    public PolarValue upper(PolarValue str) {
        return new PolarValue(str.asString().toUpperCase());
    }

    // в нижний регистр
    public PolarValue lower(PolarValue str) {
        return new PolarValue(str.asString().toLowerCase());
    }
}
