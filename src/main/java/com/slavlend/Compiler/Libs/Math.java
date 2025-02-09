package com.slavlend.Compiler.Libs;


/*
Математическая библиотека
 */
public class Math {
    // синус
    public float sin(Float value) {
        return (float) java.lang.Math.sin(value);
    }

    // косинус
    public float cos(Float value) {
        return (float) java.lang.Math.cos(value);
    }

    // тангенс
    public float tan(Float value) {
        return (float) java.lang.Math.tan(value);
    }

    // атангенс
    public float atan(Float value) {
        return (float) java.lang.Math.atan(value);
    }

    // степень
    public float pow(Float value, Float value2) {
        return (float) java.lang.Math.pow(value, value2);
    }

    // квадратный корень
    public float sqrt(Float value) {
        return (float) java.lang.Math.sqrt(value);
    }
}
