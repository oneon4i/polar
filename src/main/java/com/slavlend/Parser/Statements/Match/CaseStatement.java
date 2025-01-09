package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Operator;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.*;
import com.slavlend.VM.Instructions.VmInstrIf;
import kotlin.NotImplementedError;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CaseStatement implements Statement {
    // экспрешенн для проверки
    private Expression checkExpr;
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
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
        if (expr.evaluate().equal(checkExpr.evaluate())) {
            // стэйтменты
            for (Statement statement : statements) {
                try {
                    statement.execute();
                } catch (BreakStatement breakStatement) {
                    return new PolarValue(null);
                }
            }
        }

        return new PolarValue(null);
    }

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        checkExpr = Optimizations.optimize(checkExpr);
    }

    // правильно ли
    public boolean isRight(Expression expr) {
        return expr.evaluate().equal(checkExpr.evaluate());
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
        CaseStatement _copy = new CaseStatement(checkExpr);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
    }

    // конструктор
    public CaseStatement(Expression e) {
        this.checkExpr = e;
    }

    // компиляция
    public VmInstrIf getCompiled(Expression matchExpr) {
        IfStatement ifStatement = new IfStatement(compileCondition(matchExpr));
        for (Statement s : statements) {
            ifStatement.add(s);
        }
        return ifStatement.getCompiled();
    }

    public ArrayList<Expression> compileCondition(Expression matchExpr) {
        ArrayList<Expression> expressions = new ArrayList<>();
        expressions.add(new ConditionExpression(checkExpr, new Operator("=="), matchExpr));
        return expressions;
    }
}
