package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;

import java.util.ArrayList;

/*
Стэйтмент функции - для дефенишена функции
 */
public class FunctionStatement implements Statement, Callable {
    // тело функции
    public ArrayList<Statement> statements = new ArrayList<>();
    // аргументы
    public ArrayList<ArgumentExpression> arguments;

    // имя
    public String name;

    // дефайнуто для
    public PolarObject definedFor;

    @Override
    public void optimize() {
        // оптимизируем стэйтменты
        /*
        ArrayList<Statement> newStatements = new ArrayList<Statement>();

        for (Statement statement : statements) {
            newStatements.add(Optimizations.);
        }
         */
    }

    // адресс
    private final Address address = App.parser.address();

    // ставим дефайн
    public void setDefinedFor(PolarObject _definedFor) {
        this.definedFor = _definedFor;
    }

    // конструктор
    public FunctionStatement(String name, ArrayList<ArgumentExpression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    // помещение в глоабальный скоуп
    public void putToFunction() {
        Storage.getInstance().put(name, new PolarValue(this));
    }

    // добавление в бади
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void execute() {

    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        FunctionStatement _copy = new FunctionStatement(name, arguments);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    // вызов функции
    @Override
    public PolarValue call(PolarObject calledFor, ArrayList<PolarValue> params) {
        // оптимизурем
        optimize();

        // дефайн переменной this
        if (calledFor != null) { Storage.getInstance().last().put("this", new PolarValue(calledFor)); }
        else if (definedFor != null) { Storage.getInstance().last().put("this", new PolarValue(definedFor)); }

        // дефайним переданные параметры
        for (int i = 0; i < arguments.size(); i++) {
            Storage.getInstance().put(arguments.get(i).evaluate().asString(), params.get(i));
        }

        // экзекьютим стэйтменты
        for (Statement statement : statements) {
            // бэк -> возвращает значение
            try {
                statement.execute();
            } catch (PolarValue v) {
                return v;
            }
        }

        return null;
    }
}
