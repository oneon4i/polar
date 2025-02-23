package com.slavlend.Compiler.Libs;

/*
Библиотека для работы со строками
 */
public class Strings {
    public String replace(String from, String what, String to) {
        return from.replace(what,to);
    }
    public Array split(String str, String delim) {
        String[] splitted = str.split(delim);
        Array arr = new Array();
        for (String s : splitted) {
            arr.add(s);
        }
        return arr;
    }
    public String at(String str, Float index) {
        return String.valueOf(str.charAt(index.intValue()));
    }
    public String upper(String str) {
        return str.toUpperCase();
    }
    public String lower(String str) {
        return str.toLowerCase();
    }
    public boolean is_letter(char str) {return Character.isLetter(str);}
    public boolean is_number(char str) {return Character.isDigit(str);}

}