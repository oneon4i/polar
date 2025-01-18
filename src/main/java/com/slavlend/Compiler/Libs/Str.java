package com.slavlend.Compiler.Libs;

/*
Библиотека строк для VM
 */
public class Str {
    public Object replace(Object from, Object what, Object to) {
        return ((String)from).replace((String)what,(String)to);
    }
    public Object split(Object str, Object delim) {
        String[] splitted = ((String)str).split((String)delim);
        Array arr = new Array();
        for (String s : splitted) {
            arr.add(s);
        }
        return arr;
    }
    public Object at(Object str, Object index) {
        return ((Character)((String)str).charAt(((Float) index).intValue())).toString();
    }
    public Object upper(Object str) {
        return ((String)str).toUpperCase();
    }
    public Object lower(Object str) {
        return ((String)str).toLowerCase();
    }
}