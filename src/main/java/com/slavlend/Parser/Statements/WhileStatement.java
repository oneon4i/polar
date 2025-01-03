package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import lombok.Getter;

import java.util.ArrayList;

/*
Вайл стэйтмент - цикл
 */
@SuppressWarnings("PointlessBooleanExpression")
@Getter
public class WhileStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final ArrayList<ConditionExpression> conditions;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // кондишены
        while (conditions() == true) {
            // стэйтменты
            for (Statement statement : statements) {
                try {
                    statement.execute();
                } catch (BreakStatement breakStatement) {
                    return;
                }
            }
        }
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
        WhileStatement _copy = new WhileStatement(conditions);

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
    public WhileStatement(ArrayList<ConditionExpression> _conditions) {
        this.conditions = _conditions;
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
