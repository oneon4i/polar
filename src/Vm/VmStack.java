package com.slavlend.Vm;

import java.util.ArrayList;

/*
Стэк виртуальной машины
 */
public class VmStack {
    // стэк
    private ThreadLocal<ArrayList<VmFrame>> stack;

    // глобальнык переменные
    private VmFrame global;

    // инстанс
    public static VmStack instance;

    // конструктор
    public VmStack() {
        instance = this;
    }

    // инициализация
    public void init() {
        stack.set(new ArrayList<>());
    }

    // помещение в стек
    public void put(String name, VmVal val) {
        if (!stack.get().isEmpty()) {
            stack.get().get(stack.get().size()-1).put(name, val);
        } else {
            global.put(name, val);
        }
    }

    // получение из стека
    public VmVal get(String name) {
        if (!stack.get().isEmpty()) {
            return stack.get().get(stack.get().size()-1).get(name);
        } else {
            return global.get(name);
        }
    }

    // есть ли
    public Boolean has(String name) {
        if (!stack.get().isEmpty()) {
            return stack.get().get(stack.get().size()-1).has(name);
        } else {
            return global.has(name);
        }
    }
}
