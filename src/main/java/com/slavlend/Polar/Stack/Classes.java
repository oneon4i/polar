package com.slavlend.Polar.Stack;

import com.slavlend.Env.PolarEnv;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.ClassStatement;
import com.slavlend.Polar.PolarClass;

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
    public ArrayList<PolarClass> classes = new ArrayList<>();

    // получение класса из списка по имени
    public PolarClass getClass(String name) {
        for (PolarClass clazz : classes) {
            if (clazz.name.equals(name)) {
                return clazz;
            }
        }

        PolarEnv.Crash("Class Not Found (" + name + ")", new Address(-1));
        return null;
    }

    // проверка на наличие класса по имени
    public boolean hasClass(String name) {
        for (PolarClass clazz : classes) {
            if (clazz.name.equals(name)) {
                return true;
            }
        }

        return false;
    }

    // получение класса из списка по имени и адрессу
    public PolarClass getClassByAddress(Address address, String name) {
        for (PolarClass clazz : classes) {
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
