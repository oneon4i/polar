package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrIf;
import com.slavlend.Vm.Instructions.VmInstrPush;
import lombok.Getter;
import lombok.Setter;

/*
Намбер экспрешенн - возвращает число
 */
@Getter
public class TernaryExpression implements Expression {
    // условие
    private final Expression logical;
    // правое выражение
    private final Expression right;
    // левое выражение
    private final Expression left;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // if
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        logical.compile();
        ifInstr.setWritingConditions(false);
        left.compile();
        Compiler.code.endWrite();
        // else
        VmInstrIf elseInstr = new VmInstrIf(address.convert());
        Compiler.code.startWrite(elseInstr);
        elseInstr.setWritingConditions(true);
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), true));
        elseInstr.setWritingConditions(false);
        right.compile();
        Compiler.code.endWrite();
        // set else
        ifInstr.setElse(elseInstr);
        // визит инструкции
        Compiler.code.visitInstr(ifInstr);
    }

    public TernaryExpression(Expression logical, Expression left, Expression right) {
        this.logical = logical; this.left = left; this.right = right;
    }
}
