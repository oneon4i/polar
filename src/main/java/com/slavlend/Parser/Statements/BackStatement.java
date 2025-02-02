package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.VmInstrPush;
import com.slavlend.Vm.Instructions.VmInstrRet;
import lombok.Getter;

/*
Стэйтмент бэк - возвращает значение переданное
 */
@Getter
public class BackStatement implements Statement, Expression {
    // выражение для возврата
    private final Expression expr;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Statement copy() {
        return new BackStatement(expr);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        if (expr != null) {
            expr.compile();
        }
        Compiler.code.visitInstr(new VmInstrRet(address.convert()));
    }

    // конструктор
    public BackStatement(Expression expr) {
        this.expr = expr;
    }
}
