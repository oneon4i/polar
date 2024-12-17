package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;

import java.util.ArrayList;

public class IfStatement implements Statement {
    // тело функции
    public ArrayList<Statement> statements = new ArrayList<Statement>();
    // в ином случае
    public IfStatement _else = null;
    // кодишены для ифа
    public ArrayList<ConditionExpression> conditions = new ArrayList<>();
    // адресс
    private Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();

        // если условие сработало
        if (conditions()) {
            // стэйтменты
            for (Statement statement : statements) {
                 statement.execute();
            }
        }
        else {
            // если условие не сработало
            // экзекьютим если есть что-то в ином случае
            if (_else != null) {
                _else.execute();
            }
        }
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    // кодишены
    public boolean conditions() {
        for (ConditionExpression e : conditions) {
            PolarValue v = e.evaluate();
            if (!v.asBool()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        IfStatement _copy = new IfStatement(conditions);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        if (_else != null) {
            _copy._else = (IfStatement) _else.copy();
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public IfStatement(ArrayList<ConditionExpression> expressions) {
        this.conditions = expressions;
    }
}
