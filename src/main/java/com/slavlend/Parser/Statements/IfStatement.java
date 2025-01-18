package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Polar.PolarValue;
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
    private final Expression logical;
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
        if (logical.evaluate().asBool()) {
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

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        IfStatement _copy = new IfStatement(logical);

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
    public IfStatement(Expression expression) {
        this.logical = expression;
    }
}
