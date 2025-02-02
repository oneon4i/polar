package com.slavlend.Polar;

import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Parser.Statements.Callable;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Polar.Stack.Storage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Функция Polar
 */
@Getter
public class PolarFunction implements Callable {
    // тело функции
    private final ArrayList<Statement> body = new ArrayList<>();
    // аргументы
    private final ArrayList<ArgumentExpression> arguments;
    // имя
    private final String name;
    // дефайнуто для
    @Setter
    private PolarObject definedFor;

    // конструктор
    public PolarFunction(String name, ArrayList<ArgumentExpression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    // добавление стейтмента
    public void add(Statement s) {
        body.add(s);
    }

    // вызов функции
    @Override
    public PolarValue call(PolarObject calledFor, ArrayList<PolarValue> params) {
        // дефайн переменной this
        if (calledFor != null) { Storage.getInstance().last().put("this", new PolarValue(calledFor)); }
        else if (definedFor != null) { Storage.getInstance().last().put("this", new PolarValue(definedFor)); }

        // дефайним переданные параметры
        for (int i = 0; i < arguments.size(); i++) {
            Storage.getInstance().put(arguments.get(i).evaluate().asString(), params.get(i));
        }

        // экзекьютим стэйтменты
        for (Statement statement : body) {
            // бэк -> возвращает значение
            try {
                statement.execute();
            } catch (PolarValue v) {
                return v;
            }
        }

        return new PolarValue(null);
    }

    public PolarFunction copy() {
        PolarFunction fn = new PolarFunction(name, arguments);
        for (Statement statement : body) {
            fn.add(statement.copy());
        }
        return fn;
    }
}
