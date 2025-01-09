package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Трай стэйтмент - ловит исключения
 */
@Getter
public class TryStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // тело при пойманом throwable
    private final ArrayList<Statement> catchStatements = new ArrayList<>();
    // имя переменной
    @Setter
    private String variableName;
    // адресс
    private final Address address = App.parser.address();

    public void add(Statement statement) {
        statements.add(statement);
    }
    public void addCatchStatement(Statement statement) {
        catchStatements.add(statement);
    }

    // копирование
    @Override
    public Statement copy() {
        TryStatement _copy = new TryStatement(variableName);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        for (Statement statement : catchStatements) {
            _copy.addCatchStatement(statement.copy());
        }

        return _copy;
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }

    // конструктор
    public TryStatement(String variableName) {
        this.variableName = variableName;
    }
}
