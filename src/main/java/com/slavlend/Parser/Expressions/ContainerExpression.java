package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Address;

import java.util.ArrayList;

public class ContainerExpression implements Expression {
    public ArrayList<Expression> container = new ArrayList<>();
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        // возвращаем эррэй, если нет класса - кидаем ошибку
        if (Classes.getInstance().getClass("Array") != null) {
            //return new EggValue(new EggContainer(container));
            PolarObject array = new ObjectExpression("Array", new ArrayList<>()).evaluate().asObject();
            // добавляем все элементы
            for (Expression e : container) {
                // аргументы
                ArrayList<PolarValue> lst = new ArrayList<PolarValue>();
                lst.add(e.evaluate());
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
            PolarLogger.exception("Cannot Find Array Class. Did You Forgot To Import 'lib.array'?", address);
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

    public ContainerExpression(ArrayList<Expression> container) {
        this.container = container;
    }
}
