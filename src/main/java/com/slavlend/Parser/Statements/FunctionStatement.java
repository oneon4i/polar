package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Polar.PolarFunction;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Стэйтмент функции - для дефенишена функции
 */
@Getter
public class FunctionStatement implements Statement {
    // функция
    @Setter
    private PolarFunction function;

    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    // конструктор
    public FunctionStatement(String name, ArrayList<ArgumentExpression> arguments) {
        function = new PolarFunction(name, arguments);
    }

    // помещение в глоабальный скоуп
    public void putToFunctions() {
        Storage.getInstance().put(function.getName(), new PolarValue(function));
    }

    // добавление в бади
    public void add(Statement statement) {
        function.add(statement);
    }

    @Override
    public void execute() {

    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        FunctionStatement _copy = new FunctionStatement(function.getName(), function.getArguments());
        _copy.setFunction(function);

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }
}
