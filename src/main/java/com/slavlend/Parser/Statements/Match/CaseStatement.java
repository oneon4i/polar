package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Operator;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.*;
import com.slavlend.Vm.Instructions.VmInstrIf;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class CaseStatement implements Statement {
    // экспрешенн для проверки
    private final Expression checkExpr;
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
    // адресс
    @Setter
    private Address address = App.parser.address();

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
    }

    // конструктор
    public CaseStatement(Expression e) {
        this.checkExpr = e;
    }

    // компиляция
    public VmInstrIf getCompiled(Expression matchExpr) {
        IfStatement ifStatement = new IfStatement(compileCondition(matchExpr));
        for (Statement s : statements) {
            ifStatement.add(s);
        }
        return ifStatement.getCompiled();
    }

    public Expression compileCondition(Expression matchExpr) {
        return new ConditionExpression(checkExpr, new Operator("=="), matchExpr);
    }
}
