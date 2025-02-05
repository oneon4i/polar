package com.slavlend.Compiler.Libs;


/*
Математическая библиотека
 */
public class Math {
    // синус
    public Object sin(Object value) {
        return Float.parseFloat(String.valueOf(java.lang.Math.sin((float) value)));
    }

    // косинус
    public Object cos(Object value) {
        return Float.parseFloat(String.valueOf(java.lang.Math.cos((float) value)));
    }

    // тангенс
    public Object tan(Object value) {
        return Float.parseFloat(String.valueOf(java.lang.Math.tan((float) value)));
    }

    // атангенс
    public Object atan(Object value) {
        return Float.parseFloat(String.valueOf(java.lang.Math.atan((float) value)));
    }
}
