package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Exceptions.PolarThrowable;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import lombok.Getter;

/*
Рэйс стейтмент - выкидывает throwable
 */
@Getter
public class RaiseStatement implements Statement {
    // выкидываемое
    private final Expression throwableExpr;
    // адресс
    private final  Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // выкидываем
        throw new PolarThrowable(throwableExpr.evaluate());
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        return new RaiseStatement(throwableExpr);
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public RaiseStatement(Expression throwableExpr) {
        this.throwableExpr = throwableExpr;
    }
}
