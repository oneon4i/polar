package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.VmInstrThrow;
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

    @Override
    public void compile() {
        throwableExpr.compile();
        Compiler.code.visitInstr(new VmInstrThrow(address.convert()));
    }

    // конструктор
    public RaiseStatement(Expression throwableExpr) {
        this.throwableExpr = throwableExpr;
    }
}
