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
}
