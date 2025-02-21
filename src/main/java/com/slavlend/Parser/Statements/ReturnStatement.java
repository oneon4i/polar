package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Expressions.NilExpression;
import com.slavlend.Vm.Instructions.VmInstrRet;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;
import lombok.Setter;

/*
Стэйтмент return - возвращает значение из функции
 */
@Getter
public class ReturnStatement implements Statement, Expression {
    // выражение для возврата
    private final Expression expr;
    // адресс
    @Setter
    private Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmVarContainer retContainer = new VmVarContainer();
        Compiler.code.startWrite(retContainer);
        if (expr != null) {
            expr.compile();
        }
        else {
            NilExpression nil = new NilExpression();
            nil.compile();
        }
        Compiler.code.endWrite();
        Compiler.code.visitInstr(new VmInstrRet(retContainer, address.convert()));
    }

    // конструктор
    public ReturnStatement(Expression expr) {
        this.expr = expr;
    }
}
