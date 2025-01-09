package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Parser.Expressions.ConditionExpression;
import lombok.Getter;

import java.util.ArrayList;

/*
Фор стэйтмент - цикл
 */
@SuppressWarnings("PointlessBooleanExpression")
@Getter
public class EachStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final ArrayList<ConditionExpression> conditions = new ArrayList<>();
    // адресс
    private final Address address = App.parser.address();
    // имя переменной
    private final String variableName;
    // имя переменной списка
    private final AccessExpression listVariable;

    // добавка стейтмента в блок
    public void add(Statement statement) {
        statements.add(statement);
    }

    // копирование

    @Override
    public Statement copy() {
        EachStatement _copy = new EachStatement(listVariable, variableName);

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

    @Override
    public void compile() {
        // not implemented
    }

    // конструктор
    public EachStatement(AccessExpression arr, String name) {
        this.listVariable = arr;
        this.variableName = name;
    }
}
