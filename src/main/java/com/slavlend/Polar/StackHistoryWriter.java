package com.slavlend.Polar;


import com.slavlend.Parser.Address;
import java.util.ArrayList;

/*
Стэк врайтер. Записывает стэк.
историю вызов.
 */
public class StackHistoryWriter {
    // размер истории стека
    private final int historySize = 7;

    // элемент стэка
    public static class StackElement {
        // адресс
        private final Address address;
        // вызов
        private final String call;

        // конструктор
        public StackElement(Address address, String call) {
            this.address = address;
            this.call = call;
        }

        // геттеры
        public Address getAddress() {
            return address;
        }

        public String getCall() {
            return call;
        }
    }

    // история
    public ThreadLocal<ArrayList<StackElement>> hist = new ThreadLocal<>();

    // инстанс
    public static StackHistoryWriter instance;

    public static StackHistoryWriter getInstance() {
        return instance;
    }

    // инициализация
    public StackHistoryWriter() {
        instance = this;
    }

    // выводит стак трэйс
    public void printStackTrace() {
        // вывод
        for (int i = 0; i < getHistStackSize(); i++) {
            StackElement elem = hist.get().get(i);
            System.out.println("| ➡️ " + elem.getCall() + " at: " + elem.getAddress().line + ":" + elem.getAddress().ch);
        }
    }

    // получение размера истории стэка
    public int getHistStackSize() {
        // проверяем если размер истории стека больше ограничения для её вывода
        if (hist.get().size() > historySize) {
            return historySize;
        }
        // в ином случае возвращаем размер истории стека
        else {
            return hist.get().size();
        }
    }

    // пуш вызова
    public void pushCall(Address address, String call) {
        hist.get().add(new StackElement(address, call));
    }
}
