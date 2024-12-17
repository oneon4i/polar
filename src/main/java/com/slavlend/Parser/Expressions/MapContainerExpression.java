package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Env.PolarEnv;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Address;

import java.util.ArrayList;
import java.util.HashMap;

public class MapContainerExpression implements Expression {
    public HashMap<Expression, Expression> container;
    // адресс
    private Address address = App.parser.address();

    public MapContainerExpression(HashMap<Expression, Expression> container) {
        this.container = container;
    }

    @Override
    public PolarValue evaluate() {
        // возвращаем мэпу, если нет класса - кидаем ошибку
        if (Classes.getInstance().getClass("Map") != null) {
            //return new EggValue(new EggContainer(container));
            PolarObject array = new ObjectExpression("Map", new ArrayList<>()).evaluate().asObject();
            // добавляем все элементы
            for (Expression e : container.keySet()) {
                // аргументы
                ArrayList<PolarValue> lst = new ArrayList<PolarValue>();
                // ключ и значение
                lst.add(e.evaluate());
                lst.add(container.get(e).evaluate());
                // пушим фрейм в стек
                Storage.getInstance().push();
                // вызываем функцию
                array.classValues.get("add").asFunc().call(array, lst);
                // удаляет фрейм из стека
                Storage.getInstance().pop();
            }
            // возвращение
            return new PolarValue(array);
        }
        else {
            PolarEnv.Crash("Cannot Find Map Class. Did You Forgot To Import 'lib.map'?", address);
            return new PolarValue(null);
        }
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }
}
