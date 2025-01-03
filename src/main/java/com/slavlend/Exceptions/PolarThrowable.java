package com.slavlend.Exceptions;

import com.slavlend.Polar.PolarValue;

import java.util.List;

/*
Выбрасываемое значение
 */
public class PolarThrowable extends RuntimeException {
    // значение
    private final PolarValue value;

    // конструктор
    public PolarThrowable(PolarValue value) {
        this.value = value;
    }

    // получение значения
    public PolarValue getValue() {
        return value;
    }
}
