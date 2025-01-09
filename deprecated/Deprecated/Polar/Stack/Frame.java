package com.slavlend.Polar.Stack;

import com.slavlend.Polar.PolarValue;
import lombok.Getter;

import java.util.HashMap;

/*
Фрейм данных (создается при вызове функции)
для контроллирования локальных переменных функции
 */
@Getter
public class Frame {
    // значения
    private final HashMap<String, PolarValue> values = new HashMap<>();

    // функция для помещения значения
    public void put(String name, PolarValue values) {
        this.values.put(name, values);
    }

    // функция для удаления значения
    public void delete(String name) { this.values.remove(name); }

    // пустой конструктор
    public Frame() {

    }
}
