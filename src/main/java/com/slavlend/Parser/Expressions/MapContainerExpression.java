package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Address;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ThrowableNotThrown")
@Getter
public class MapContainerExpression implements Expression {
    // контейнер
    private final HashMap<Expression, Expression> container;
    // адресс
    private final Address address = App.parser.address();

    public MapContainerExpression(HashMap<Expression, Expression> container) {
        this.container = container;
    }

    @Override
    public PolarValue evaluate() {
        // возвращаем мэпу, если нет класса - кидаем ошибку
        if (Classes.getInstance().lookupClass("Map") != null) {
            //return new EggValue(new EggContainer(container));
            PolarObject array = new ObjectExpression("Map", new ArrayList<>()).evaluate().asObject();
            // добавляем все элементы
            for (Expression e : container.keySet()) {
                // аргументы
                ArrayList<PolarValue> lst = new ArrayList<>();
                // ключ и значение
                lst.add(e.evaluate());
                lst.add(container.get(e).evaluate());
                // пушим фрейм в стек
                Storage.getInstance().push();
                // вызываем функцию
                array.getClassValues().get("add").asFunc().call(array, lst);
                // удаляет фрейм из стека
                Storage.getInstance().pop();
            }
            // возвращение
            return new PolarValue(array);
        }
        else {
            PolarLogger.exception("Cannot Find Map Class. Did You Forgot To Import 'lib.map'?", address);
            return new PolarValue(null);
        }
    }

    @Override
    public Address address() {
        return address;
    }
}
