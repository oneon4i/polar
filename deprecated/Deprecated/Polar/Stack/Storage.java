package com.slavlend.Polar.Stack;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.StackHistoryWriter;
import com.slavlend.Parser.Address;
import com.slavlend.PolarLogger;
import lombok.Getter;

import java.util.ArrayList;

/*
Хранилище - набор функций
для управления хранением локальных,
объектовых и глобальных переменных.
 */
@Getter
public class Storage {
    // стэк вызовов с фреймами под каждый вызов
    private final ThreadLocal<ArrayList<Frame>> callStack = new ThreadLocal<>();
    // переменные глобального скоупа
    private final Frame globalVariables = new Frame();

    // синглтон
    @Getter
    public static Storage Instance;

    // конструктор
    public Storage() {
        if (Instance == null) {
            Instance = this;
        }
    }

    // получение локальной/глобальной переменной по имени
    public PolarValue get(Address reqAddress, String name) {
        // если стэк не пуст
        if (!callStack.get().isEmpty()) {
            // получаем последний вызов
            Frame frame = last();
            // ищем локальное значение
            if (frame.getValues().containsKey(name)) {
                // возвращаем если есть
                return frame.getValues().get(name);
            }
        }

        // ищем глобальное значение
        if (globalVariables.getValues().containsKey(name)) {
            return globalVariables.getValues().get(name);
        }

        // крашим
        PolarLogger.exception("Cannot Find Variable: " + name, reqAddress);
        return new PolarValue(null);
    }

    // показывает существует ли локальная/глобальная переменная с этим именем
    public boolean has(String name) {
        // если стэк не пуст
        if (!callStack.get().isEmpty()) {
            // получаем последний вызов
            Frame frame = last();
            // ищем локальное значение
            if (frame.getValues().containsKey(name)) {
                // возвращаем true если есть
                return true;
            }
        }

        // ищем глобальное значение
        return globalVariables.getValues().containsKey(name);
    }

    // инициализация хранилища в потоке
    public void threadInit() {
        // инициализация стека вызовов
        callStack.set(new ArrayList<>());
        // инициализация истории стека
        StackHistoryWriter.getInstance().getHistory().set(new ArrayList<>());
    }

    // пушим фрейм в стэк
    public void push() {
        callStack.get().add(new Frame());
    }

    // удалем фрейм из стека
    public void pop() {
        callStack.get().remove(callStack.get().size() - 1);
    }


    // помещаем по имени
    public void put(String name, PolarValue value) {
        // если стэк пуст
        if (callStack.get().isEmpty()) {
            // помещаем в глобал значения
            globalVariables.getValues().put(name, value);
        }
        else {
            // в ином случае в стек
            last().put(name, value);
        }
    }

    // удаляем по имени
    public void del(String name) {
        // если стэк пуст
        if (callStack.get().isEmpty()) {
            // помещаем в глобал значения
            globalVariables.getValues().remove(name);
        }
        else {
            // в ином случае в стек
            last().delete(name);
        }
    }

    // получение последнего кол стока
    public Frame last() {
        return callStack.get().get(callStack.get().size() - 1);
    }
}
