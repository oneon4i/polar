package com.slavlend.Polar;

import com.slavlend.Parser.Address;
import com.slavlend.Polar.Logger.PolarLogger;
import lombok.Getter;

import java.util.ArrayList;

/*
Кастомные класс лоадеры для jvm в
рантайме
 */
@Getter
public class JvmClasses {
    // список
    private static final ArrayList<Class<?>> defined = new ArrayList<>();

    // получения класса
    public static Class<?> lookup(Address address, String name) {
        for (Class<?> clazz : defined) {
            if (clazz.getName().equals(name)) {
                return clazz;
            }
        }
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            PolarLogger.exception("Jvm Class: " + name + " Not Found!", address);
            return null;
        }
    }

    // дефайн класса
    public static void define(Class<?> clazz) {
        defined.add(clazz);
    }
}
