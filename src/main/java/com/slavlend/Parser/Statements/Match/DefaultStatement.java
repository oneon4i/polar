package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.*;

import java.util.ArrayList;

public class DefaultStatement implements Statement {
    // тело функции
    public ArrayList<Statement> statements = new ArrayList<Statement>();
    // адресс
    private Address address = App.parser.address();

    @Override
    public void execute() {
        // оптимизируем
        optimize();
        // стэйтменты
        for (Statement statement : statements) {
            statement.execute();
        }
    }
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void interrupt() {

    }

    @Override
    public void optimize() {
        // ...
    }

    // копирование
    @Override
    public Statement copy() {
        DefaultStatement _copy = new DefaultStatement();

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
    public DefaultStatement() {
    }
}
