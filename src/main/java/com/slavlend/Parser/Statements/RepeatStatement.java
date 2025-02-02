package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import lombok.Getter;

import java.util.ArrayList;

/*
Вайл стэйтмент - цикл
 */
@Getter
public class RepeatStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final Expression times;
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
        float amount = times.evaluate().asNumber();
        while (amount > 0) {
            // стэйтменты
            for (Statement statement : statements) {
                try {
                    statement.execute();
                } catch (BreakStatement breakStatement) {
                    return;
                }
            }
            // добавляем еденичку
            amount -= 1;
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
        RepeatStatement _copy = new RepeatStatement(times);

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
    public RepeatStatement(Expression times) {
        this.times = times;
    }
}
