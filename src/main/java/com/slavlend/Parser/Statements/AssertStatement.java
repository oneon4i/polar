package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Vm.Instructions.VmInstrIf;
import com.slavlend.Vm.Instructions.VmInstrPush;
import com.slavlend.Vm.Instructions.VmInstrThrow;
import com.slavlend.Vm.VmException;
import lombok.Getter;

/*
Стэйтмент для ассерта ( проверяет условие )
 */
@Getter
public class AssertStatement implements Statement {
    // экспрешен
    private final ConditionExpression expr;
    // аддресс
    private final Address address = App.parser.address();

    @Override
    public Statement copy() {
        return new AssertStatement(expr);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // условие
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.visitInstr(ifInstr);
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        expr.compile();
        ifInstr.setWritingConditions(false);
        Compiler.code.endWrite();
        // else
        VmInstrIf elseInstr = new VmInstrIf(address.convert());
        Compiler.code.startWrite(elseInstr);
        elseInstr.setWritingConditions(true);
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), true));
        elseInstr.setWritingConditions(false);
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), new VmException(address.convert(), "assertion error")));
        Compiler.code.visitInstr(
                new VmInstrThrow(address.convert())
        );
        Compiler.code.endWrite();
        ifInstr.setElse(elseInstr);
    }

    // конструктор
    public AssertStatement(ConditionExpression expr) {
        this.expr = expr;
    }
}
