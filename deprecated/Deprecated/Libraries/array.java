package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import lombok.Getter;

import java.util.ArrayList;

/*
Библиотека для списка
 */
@SuppressWarnings("unused")
@Getter
public class array {
    // список
    private final ArrayList<PolarValue> valueArrayList = new ArrayList<>();

    // конструктор
    public array() {
    }

    // добавление элемента
    public void add(PolarValue e) {
        valueArrayList.add(e);
    }

    // удаление элемента
    public void del(PolarValue e) {
        if (contains(e).asBool()) {
            for (PolarValue _obj : valueArrayList) {
                if (_obj.equal(e)) {
                    valueArrayList.remove(_obj);
                    return;
                }
            }
        }
    }

    // размер всего списка
    public PolarValue size() {
        return new PolarValue(valueArrayList.size());
    }

    // получение элемента по индексу
    public PolarValue get(int index) {
        return valueArrayList.get(index);
    }

    // переписывание элемента по индексу
    public void set(int index, PolarValue value) {
        valueArrayList.set(index, value);
    }

    // получение индекса элементов
    public PolarValue indexOf(PolarValue obj) {
        return new PolarValue(valueArrayList.indexOf(obj));
    }

    // проверка на содержание элемента
    public PolarValue contains(PolarValue obj) {
        for (PolarValue _obj : valueArrayList) {
            if (_obj.equal(obj)) {
                return new PolarValue(true);
            }
        }

        return new PolarValue(false);
    }

    // функция дампа списка в строку
    public PolarValue dumps() {
        StringBuilder str = new StringBuilder("[");

        for (PolarValue value : valueArrayList) {
            if (!value.isObject()) {
                str.append(value.asString()).append(", ");
            }
            else {
                if (value.asObject().getClazz().getName().equals("Map") ||
                        value.asObject().getClazz().getName().equals("Array")) {

                    Storage.getInstance().push();
                    String _value = value.asObject().getClassValues().get("dumps").asFunc().call(value.asObject(), new ArrayList<>()).asString();
                    Storage.getInstance().pop();

                    str.append(_value).append(", ");
                }
                else {
                    str.append("'").append(value.asString()).append("'").append(", ");
                }
            }
        }

        // удаляем последнию запятую
        if (str.toString().length() > 1) {
            str.delete(str.toString().length() - 2, str.toString().length() - 1);
        }

        str.append("]");

        return new PolarValue(str.toString().replace("'", "\""));
    }

    // функция преобразования в строку
    public PolarValue stringify() {
        StringBuilder str = new StringBuilder();

        for (PolarValue value : valueArrayList) {
            if (!value.isObject()) {
                str.append(value.asString());
            }
            else {
                if (value.asObject().getClazz().getName().equals("Map") ||
                        value.asObject().getClazz().getName().equals("Array")) {

                    Storage.getInstance().push();
                    String _value = value.asObject().getClassValues().get("dumps").asFunc().call(value.asObject(), new ArrayList<>()).asString();
                    Storage.getInstance().pop();

                    str.append(_value);
                }
                else {
                    str.append("'").append(value.asString()).append("'");
                }
            }
        }

        // удаляем последнию запятую
        if (str.toString().length() > 1) {
            str.delete(str.toString().length() - 2, str.toString().length() - 1);
        }

        return new PolarValue(str.toString().replace("'", "\""));
    }
}
