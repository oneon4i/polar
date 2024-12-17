package com.slavlend.Polar.Stack;

import com.slavlend.Env.PolarEnv;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.ClassStatement;

import java.util.ArrayList;

/*
Список классов
 */
public class Classes {
    // синглтон
    public static Classes Instance;
    public static Classes getInstance() {
        return Instance;
    }

    // список классов
    public ArrayList<ClassStatement> classes = new ArrayList<>();

    // получение класса из списка по имени
    public ClassStatement getClass(String name) {
        for (ClassStatement clazz : classes) {
            if (clazz.name.equals(name)) {
                return clazz;
            }
        }

        PolarEnv.Crash("Class Not Found (" + name + ")", new Address(-1));
        return null;
    }

    // проверка на наличие класса по имени
    public boolean hasClass(String name) {
        for (ClassStatement clazz : classes) {
            if (clazz.name.equals(name)) {
                return true;
            }
        }

        return false;
    }

    // получение класса из списка по имени и адрессу
    public ClassStatement getClassByAddress(Address address, String name) {
        for (ClassStatement clazz : classes) {
            if (clazz.name.equals(name)) {
                return clazz;
            }
        }

        PolarEnv.Crash("Class Not Found (" + name + ")", address);
        return null;
    }

    // конструктор
    public Classes() {
        if (Instance == null) {
            Instance = this;
        }
    }
}
