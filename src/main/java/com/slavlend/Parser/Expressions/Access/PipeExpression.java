package com.slavlend.Parser.Expressions.Access;

import com.slavlend.App;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Polar.PolarValue;

import java.util.ArrayList;

/*
Экспрешенн пайп-выражения
 */
public class PipeExpression implements Expression, Statement {
    // левое и правое выражение
    private final Expression l;
    private final AccessExpression r;
    // адресс
    private Address address = App.parser.address();

    // конструктор
    public PipeExpression(Expression l, AccessExpression r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public PolarValue evaluate() {
        if (r.GetLast() instanceof CallAccess callAccess) {
            ArrayList<Expression> params = new ArrayList<>();
            params.add(l);
            callAccess.setParams(params);
            return r.evaluate();
        }
        else {
            PolarLogger.Crash("Not A Call Access In Pipe Expr!", address());
            return null;
        }
    }

    @Override
    public void optimize() {

    }

    @Override
    public void execute() {
        if (r.GetLast() instanceof CallAccess callAccess) {
            ArrayList<Expression> params = new ArrayList<>();
            params.add(l);
            callAccess.setParams(params);
            r.execute();
        }
        else {
            PolarLogger.Crash("Not A Call Access In Pipe Stmt!", address());
        }
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return null;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }
}
