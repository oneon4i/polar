package com.slavlend.Polar;

import com.slavlend.Env.PolarEnv;
import com.slavlend.Parser.Address;

/*
Reflected - представляет собой
хранилище класса и инстанца этого объекта
 */
public class Reflected {
    // класс
    public Class<?> clazz;
    // объект
    public Object o;

    // конструктор
    public Reflected(Address address, Class<?> clazz) {
        // присваиваем класс
        this.clazz = clazz;
        // пробуем создает объект по классу
        try {
            this.o = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // кэтчим эксцепшены
            PolarEnv.Crash("Reflection object creation error (Java):  " + e.getMessage(), address);
        }
    }

    // конструктор с созданием из готового объекта
    public Reflected(Address address, Class<?> clazz, Object object) {
        // присваиваем класс
        this.clazz = clazz;
        // объект
        this.o = object;
    }
}
