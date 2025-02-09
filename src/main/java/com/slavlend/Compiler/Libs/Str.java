package com.slavlend.Compiler.Libs;

/*
Библиотека строк для VM
 */
public class Str {
    public Object replace(String from, String what, String to) {
        return from.replace(what,to);
    }
    public Object split(String str, String delim) {
        String[] splitted = str.split(delim);
        Array arr = new Array();
        for (String s : splitted) {
            arr.add(s);
        }
        return arr;
    }
    public Object at(String str, Float index) {
        return String.valueOf(str.charAt(index.intValue()));
    }
    public Object upper(String str) {
        return str.toUpperCase();
    }
    public Object lower(String str) {
        return str.toLowerCase();
    }
}