package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.*;
import kotlin.NotImplementedError;

import java.util.ArrayList;

public class CaseStatement implements Statement {
    // экспрешенн для проверки
    private Expression equalExpr;
    // тело функции
    public ArrayList<Statement> statements = new ArrayList<Statement>();
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void execute() {
        throw new NotImplementedError();
    }

    public PolarValue executeBy(Expression expr) {
        // оптимизируем
        optimize();
        // если условие сработало
        if (expr.evaluate().equal(equalExpr.evaluate())) {
            // стэйтменты
            for (Statement statement : statements) {
                try {
                    statement.execute();
                } catch (BreakStatement breakStatement) {
                    return null;
                }
            }
        }

        return null;
    }

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        equalExpr = Optimizations.optimize(equalExpr);
    }

    // правильно ли
    public boolean isRight(Expression expr) {
        return expr.evaluate().equal(equalExpr.evaluate());
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        CaseStatement _copy = new CaseStatement(equalExpr);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public CaseStatement(Expression e) {
        this.equalExpr = e;
    }
}
