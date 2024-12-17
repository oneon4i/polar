package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Expressions.Expression;

import java.util.ArrayList;

/*
Фор стэйтмент - цикл
 */
public class ForStatement implements Statement {
    // тело
    public ArrayList<Statement> statements = new ArrayList<Statement>();
    // кодишены
    public ArrayList<ConditionExpression> conditions = new ArrayList<>();
    // адресс
    private Address address = App.parser.address();
    // имя переменной
    private String variable;
    // значение
    private Expression valueExpr;

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        valueExpr = Optimizations.optimize(valueExpr);
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // создаём темповую переменную
        Storage.getInstance().put(variable, valueExpr.evaluate());

        // кондишены
        while (conditions() == true) {
            // стэйтменты
            for (Statement statement : statements) {
                try {
                    statement.execute();
                } catch (BreakStatement breakStatement) {
                    return;
                } catch (NextStatement nextStatement) {
                    // continue
                    break;
                }
            }
        }

        // удаляем темповую переменную
        Storage.getInstance().del(variable);
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
        ForStatement _copy = new ForStatement(conditions, variable, valueExpr);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public ForStatement(ArrayList<ConditionExpression> _conditions, String variable, Expression valueExpr) {
        this.conditions = _conditions;
        this.variable = variable;
        this.valueExpr = valueExpr;
    }

    // кондишены
    public boolean conditions() {
        for (ConditionExpression e : conditions) {
            PolarValue v = e.evaluate();
            if (!v.asBool()) {
                return false;
            }
        }

        return true;
    }
}
