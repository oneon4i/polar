package com.slavlend.Polar.Stack;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.StackHistoryWriter;
import com.slavlend.Parser.Address;
import com.slavlend.Logger.PolarLogger;

import java.util.ArrayList;

/*
Хранилище - набор функций
для управления хранением локальных,
объектовых и глобальных переменных.
 */
public class Storage {
    // стэк вызовов с фреймами под каждый вызов
    public ThreadLocal<ArrayList<Frame>> callStack = new ThreadLocal<>();
    // переменные глобального скоупа
    public Frame globalVariables = new Frame();

    // синглтон
    public static Storage Instance;
    public static Storage getInstance() {
        return Instance;
    }

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
            if (frame.values.containsKey(name)) {
                // возвращаем если есть
                return frame.values.get(name);
            }
        }

        // ищем глобальное значение
        if (globalVariables.values.containsKey(name)) {
            return globalVariables.values.get(name);
        }

        // крашим
        PolarLogger.exception("Cannot Find Variable: " + name, reqAddress);
        return null;
    }

    // показывает существует ли локальная/глобальная переменная с этим именем
    public boolean has(Address reqAddress, String name) {
        // если стэк не пуст
        if (!callStack.get().isEmpty()) {
            // получаем последний вызов
            Frame frame = last();
            // ищем локальное значение
            if (frame.values.containsKey(name)) {
                // возвращаем true если есть
                return true;
            }
        }

        // ищем глобальное значение
        return globalVariables.values.containsKey(name);
    }

    // инициализация хранилища в потоке
    public void threadInit() {
        // инициализация стека вызовов
        callStack.set(new ArrayList<>());
        // инициализация истории стека
        StackHistoryWriter.getInstance().hist.set(new ArrayList<>());
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
    public void put(Address reqAddress, String name, PolarValue value) {
        // если стэк пуст
        if (callStack.get().isEmpty()) {
            // помещаем в глобал значения
            globalVariables.values.put(name, value);
        }
        else {
            // в ином случае в стек
            last().put(name, value);
        }
    }

    // помещаем по имени (без адресса)
    public void put(String name, PolarValue value) {
        // если стэк пуст
        if (callStack.get().isEmpty()) {
            // помещаем в глобал значения
            globalVariables.values.put(name, value);
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
            globalVariables.values.remove(name);
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
