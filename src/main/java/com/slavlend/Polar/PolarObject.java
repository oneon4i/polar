package com.slavlend.Polar;

import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Expressions.Expression;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

/*
Класс представляет собой экземпляр
класса из ClassStatement.
 */
@SuppressWarnings("ThrowableNotThrown")
@Getter
public class PolarObject {
    // класс, чей мы инстанс
    private final PolarClass clazz;
    // конструктор/аргументы
    private final ArrayList<Expression> constructorArgs;
    // переменные (поля) объекта
    private final HashMap<String, PolarValue> classValues = new HashMap<>();

    // конструктор
    public PolarObject(PolarClass clazz, ArrayList<Expression> constructorArgs) {
        this.clazz = clazz;
        this.constructorArgs = constructorArgs;
    }

    // создаём объект сразу с передачей
    // значений в виде переменных, а не экспрешеннов
    public static PolarObject create(PolarClass clazz, ArrayList<PolarValue> values) {
        // объект
        PolarObject o = new PolarObject(clazz, new ArrayList<>());

        // инициализация конструктор
        for (int i = 0; i < clazz.getConstructor().size(); i++) {
            o.classValues.put(clazz.getConstructor().get(i).evaluate().asString(), values.get(i));
        }

        // инициализация функций (копирование их в объект, т.е сюда)
        for (String funcName : clazz.copyFunctions().keySet()) {
            PolarFunction func = clazz.copyFunctions().get(funcName);
            func.setDefinedFor(o);
            o.classValues.put(funcName, new PolarValue(func));
        }

        // вызов метода init если он найден
        if (o.classValues.containsKey("init")) {
            // пушим фрейм
            Storage.getInstance().push();
            // вызываем
            o.classValues.get("init").asFunc().call(o, new ArrayList<>());
            // удаляем фрейм
            Storage.getInstance().pop();
        }

        // возвращаем объект
        return o;
    }

    /*
    Метод инициализурет класс:
        * инициализирует переменные конструктора
        * инициализирует функции
        * вызывает функцию init если она найдена
     */
    public void init() {
        // инициализация конструктор
        for (int i = 0; i < clazz.getConstructor().size(); i++) {
            classValues.put(clazz.getConstructor().get(i).evaluate().asString(), constructorArgs.get(i).evaluate());
        }

        // инициализация функций (копирование их в объект, т.е сюда)
        for (String funcName : clazz.copyFunctions().keySet()) {
            PolarFunction func = clazz.copyFunctions().get(funcName);
            func.setDefinedFor(this);
            classValues.put(funcName, new PolarValue(func));
        }

        // вызов метода init если он найден
        if (classValues.containsKey("init")) {
            // пушим фрейм
            Storage.getInstance().push();
            // вызываем
            classValues.get("init").asFunc().call(this, new ArrayList<>());
            // удаляем фрейм
            Storage.getInstance().pop();
        }
    }

    // как строка
    public String asString() {
        return "PolarObject{class=" + clazz.getFullName() + "}";
    }
}
