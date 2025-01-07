package com.slavlend.Polar.Stack;

import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Parser.Address;
import com.slavlend.Polar.PolarClass;
import lombok.Getter;

import java.util.ArrayList;

/*
Список классов
 */
@Getter
public class Classes {
    // синглтон
    @Getter
    public static Classes Instance;

    // список классов
    private final ArrayList<PolarClass> classes = new ArrayList<>();

    // получение класса из списка по имени
    public PolarClass lookupClass(String name) {
        for (PolarClass clazz : classes) {
            if (clazz.getName().equals(name) ||
                    clazz.getFullName().equals(name)) {
                return clazz;
            }
        }

        PolarLogger.exception("Class Not Found (" + name + ")", new Address(-1));
        return null;
    }

    // получение класса из списка по имени (с адрессом)
    public PolarClass lookupClass(Address address, String name) {
        for (PolarClass clazz : classes) {
            if (clazz.getName().equals(name) ||
                    clazz.getFullName().equals(name)) {
                return clazz;
            }
        }

        PolarLogger.exception("Class Not Found (" + name + ")", address);
        return null;
    }

    // конструктор
    public Classes() {
        if (Instance == null) {
            Instance = this;
        }
    }

    // есть ли класс
    public boolean hasClass(String name) {
        for (PolarClass clazz : classes) {
            if (clazz.getName().equals(name) |
                clazz.getFullName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
