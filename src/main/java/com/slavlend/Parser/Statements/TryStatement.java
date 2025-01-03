package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Exceptions.PolarException;
import com.slavlend.Exceptions.PolarThrowable;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;

import java.util.ArrayList;

/*
Трай стэйтмент - ловит исключения
 */
public class TryStatement implements Statement {
    // тело
    private ArrayList<Statement> statements = new ArrayList<Statement>();
    // тело при пойманом throwable
    private ArrayList<Statement> catchStatements = new ArrayList<Statement>();
    // имя переменной
    private String variableName;
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
    public void addCatch(Statement statement) {
        catchStatements.add(statement);
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        TryStatement _copy = new TryStatement(variableName);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        for (Statement statement : catchStatements) {
            _copy.addCatch(statement.copy());
        }

        return _copy;
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public TryStatement(String variableName) {
        this.variableName = variableName;
    }

    // установка имени переменной
    public void setVariableName(String newVariableName) {
        this.variableName = newVariableName;
    }
}
