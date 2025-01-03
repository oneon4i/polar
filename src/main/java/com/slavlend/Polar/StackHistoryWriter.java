package com.slavlend.Polar;


import com.slavlend.Parser.Address;
import lombok.Getter;

import java.util.ArrayList;

/*
Стэк врайтер. Записывает стэк.
историю вызов.
 */
public class StackHistoryWriter {
    // элемент стэка
    @Getter
    private static class StackElement {
        // геттеры
        // адресс
        private final Address address;
        // вызов
        private final String call;

        // конструктор
        public StackElement(Address address, String call) {
            this.address = address;
            this.call = call;
        }

    }

    // история
    @Getter
    private final ThreadLocal<ArrayList<StackElement>> history = new ThreadLocal<>();

    // инстанс
    @Getter
    public static StackHistoryWriter instance;

    // инициализация
    public StackHistoryWriter() {
        instance = this;
    }

    // выводит стак трэйс
    public void printStackTrace() {
        // вывод
        for (int i = 0; i < getHistStackSize(); i++) {
            StackElement elem = history.get().get(i);
            System.out.println("| ➡️ " + elem.getCall() + " at: " + elem.getAddress().getLine());
        }
    }

    // получение размера истории стэка
    public int getHistStackSize() {
        return Math.min(history.get().size(), 7);
    }

    // пуш вызова в историю стека
    public void pushCall(Address address, String call) {
        history.get().add(new StackElement(address, call));
    }
}
