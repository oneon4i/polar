package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Expressions.NumberExpression;
import com.slavlend.Parser.Operator;
import com.slavlend.Parser.Statements.*;
import com.slavlend.VM.Instructions.VmInstrIf;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DefaultStatement implements Statement {
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
    // адресс
    private final Address address = App.parser.address();

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

    @Override
    public void compile() {

    }

    // конструктор
    public DefaultStatement() {
    }

    // компиляция
    public VmInstrIf getCompiled() {
        IfStatement ifStatement = new IfStatement(compileCondition());
        for (Statement s : statements) {
            ifStatement.add(s);
        }
        return ifStatement.getCompiled();
    }

    public ArrayList<Expression> compileCondition() {
        ArrayList<Expression> expressions = new ArrayList<>();
        expressions.add(
                new ConditionExpression(
                        new NumberExpression("0"),
                        new Operator("=="),
                        new NumberExpression("0")
                )
        );
        return expressions;
    }
}
