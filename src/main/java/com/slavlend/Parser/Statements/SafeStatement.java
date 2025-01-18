package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Exceptions.PolarException;
import com.slavlend.Exceptions.PolarThrowable;
import com.slavlend.Parser.Address;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Сэйф стэйтмент - ловит исключения и другое.
Берёт на себя ответсвтвенность за безопасность кода
 */
@Getter
public class SafeStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // тело при пойманом throwable
    private final ArrayList<Statement> catchStatements = new ArrayList<>();
    // имя переменной
    @Setter
    private String variableName;
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
        // стэйтменты
        for (Statement statement : statements) {
            try {
                statement.execute();
            } catch (PolarThrowable value) {
                // создаём переменную
                Storage.getInstance().put(variableName, value.getValue());
                // стэйтменты кэтча
                for (Statement catchStatement : catchStatements) {
                    catchStatement.execute();
                }
                // удаляем переменную
                Storage.getInstance().del(variableName);
                // выходим из блока
                return;
            } catch (PolarException e) {
                // создаём переменную
                Storage.getInstance().put(variableName, new PolarValue(e.getError()));
                // стэйтменты кэтча
                for (Statement catchStatement : catchStatements) {
                    catchStatement.execute();
                }
                // удаляем переменную
                Storage.getInstance().del(variableName);
                // выходим из блока
                return;
            }
        }
    }

    public void add(Statement statement) {
        statements.add(statement);
    }
    public void addStatementToHandle(Statement statement) {
        catchStatements.add(statement);
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        SafeStatement _copy = new SafeStatement(variableName);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        for (Statement statement : catchStatements) {
            _copy.addStatementToHandle(statement.copy());
        }

        return _copy;
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public SafeStatement(String variableName) {
        this.variableName = variableName;
    }
}
