package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import lombok.Getter;

/*
Троу стейтмент - выкидывает throwable
 */
@Getter
public class ThrowStatement implements Statement {
    // выкидываемое
    private final Expression throwableExpr;
    // адресс
    private final  Address address = App.parser.address();

    // копирование
    @Override
    public Statement copy() {
        return new ThrowStatement(throwableExpr);
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
    public ThrowStatement(Expression throwableExpr) {
        this.throwableExpr = throwableExpr;
    }
}
