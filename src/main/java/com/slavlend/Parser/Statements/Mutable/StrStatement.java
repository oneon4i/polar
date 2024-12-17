package com.slavlend.Parser.Statements.Mutable;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;

/*
Строковой стэйтмент - преобразует в строку значение
 */
public class StrStatement implements Statement, Expression {
    public Expression expression;
    // адресс
    private Address address = App.parser.address();

    @Override
    public void execute() {

    }

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        expression = Optimizations.optimize(expression);
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new StrStatement(expression);
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
        // действуем
        PolarValue value = expression.evaluate();
        return new PolarValue(String.valueOf(value.data));
    }

    public StrStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PolarValue evaluate() {
        return call();
    }
}
