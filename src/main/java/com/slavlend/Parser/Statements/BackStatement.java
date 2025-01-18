package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Polar.PolarValue;
import lombok.Getter;

/*
Стэйтмент бэк - возвращает значение переданное
 */
@Getter
public class BackStatement implements Statement, Expression {
    // выражение для возврата
    private Expression expression;
    // адресс
    private final Address address = App.parser.address();

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

    public BackStatement(Expression expression) {
        this.expression = expression;
    }
}
