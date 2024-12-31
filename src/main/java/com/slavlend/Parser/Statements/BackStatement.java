package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;

/*
Стэйтмент бэк - возвращает значение переданное
 */
public class BackStatement implements Statement, Expression {
    public Expression expression;
    // адресс
    private Address address = App.parser.address();

    @Override
    public void execute() {
        // оптимизируем
        optimize();
        // кидаем значение
        throw expression.evaluate();
    }

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        expression = Optimizations.optimize(expression);
    }

    @Override
    public PolarValue evaluate() {
        return new PolarValue(expression.evaluate());
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new BackStatement(expression);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }

    public BackStatement(Expression expression) {
        this.expression = expression;
    }
}
