package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class IfStatement implements Statement {
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
    // в ином случае
    @Setter
    private IfStatement elseCondition = null;
    // кодишены для ифа
    private final ArrayList<Expression> conditions;
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
            if (elseCondition != null) {
                elseCondition.execute();
            }
        }
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    // кодишены
    public boolean conditions() {
        for (Expression e : conditions) {
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

        if (elseCondition != null) {
            _copy.elseCondition = (IfStatement) elseCondition.copy();
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public IfStatement(ArrayList<Expression> expressions) {
        this.conditions = expressions;
    }
}
