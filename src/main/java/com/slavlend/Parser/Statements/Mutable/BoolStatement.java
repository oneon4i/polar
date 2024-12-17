package com.slavlend.Parser.Statements.Mutable;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Env.PolarEnv;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;

/*
Стэйтмент булл - конвертирует в булл число и строку
 */
public class BoolStatement implements Statement, Expression {
    public Expression expression;
    // адресс
    private Address address = App.parser.address();

    @Override
    public void execute() {

    }

    @Override
    public void interrupt() {

    }

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        expression = Optimizations.optimize(expression);
    }

    @Override
    public Statement copy() {
        return new BoolStatement(expression);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }

    public PolarValue call() {
        // оптимизируем
        optimize();

        // вычисления
        PolarValue value = expression.evaluate();

        if (value.isBool()) {
            return new PolarValue(value.asBool());
        }
        if (value.isString()) {
            return new PolarValue(Boolean.parseBoolean(value.asString()));
        }
        if (value.isNumber()) {
            if (value.asNumber() == 1) {
                return new PolarValue(true);
            }
            else {
                return new PolarValue(false);
            }
        }

        PolarEnv.Crash("Cannot Convert Value: " + value + " to bool!", address);
        return null;
    }

    public BoolStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PolarValue evaluate() {
        return call();
    }
}
