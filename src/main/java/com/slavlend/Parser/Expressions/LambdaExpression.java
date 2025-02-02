package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Polar.PolarValue;

import java.util.ArrayList;
import java.util.List;

/*
Лямбда-выражение или анонимное объявление
функции
 */
public class LambdaExpression implements Expression {
    // адресс
    private final Address address = App.parser.address();
    // тело
    private final List<Statement> body = new ArrayList<>();
    // аргументы
    private final ArrayList<ArgumentExpression> arguments;

    @Override
    public PolarValue evaluate() {
        FunctionStatement fn = new FunctionStatement("lambda", arguments);
        for (Statement s : body) {
            fn.add(s);
        }
        return new PolarValue(fn);
    }

    @Override
    public Address address() {
        return address;
    }


    public LambdaExpression(ArrayList<ArgumentExpression> args) {
        this.arguments = args;
    }

    public void add(Statement statement) {
        body.add(statement);
    }
}
