package com.slavlend.Polar;

import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Parser.Address;
import lombok.Getter;

/*
Reflected - представляет собой
хранилище класса и инстанца этого объекта
 */
@SuppressWarnings("deprecation")
@Getter
public class Reflected {
    // класс
    private final Class<?> clazz;
    // объект
    private Object reflectedObject;

    // конструктор
    public Reflected(Address address, Class<?> clazz) {
        // присваиваем класс
        this.clazz = clazz;
        // пробуем создает объект по классу
        try {
            this.reflectedObject = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // кэтчим эксцепшены
            PolarLogger.exception("Reflection object creation error (Java):  " + e.getMessage(), address);
        }
    }

    // конструктор с созданием из готового объекта
    public Reflected(Address address, Class<?> clazz, Object object) {
        // присваиваем класс
        this.clazz = clazz;
        // объект
        this.reflectedObject = object;
    }
}
