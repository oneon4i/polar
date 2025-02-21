package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.PolarLogger;
import com.slavlend.Vm.Instructions.VmInstrIf;
import com.slavlend.Vm.Instructions.VmInstrPush;
import com.slavlend.Vm.Instructions.VmInstrRet;
import com.slavlend.Vm.Instructions.VmInstrThrow;
import com.slavlend.Vm.VmException;
import com.slavlend.Vm.VmFunction;
import lombok.Getter;
import lombok.Setter;

/*
Стэйтмент require. Возвращает значение
если условие не выполняется
 */
@Getter
public class RequireStatement implements Statement {
    // экспрешен логики
    private final Expression logical;
    // экспрешен возвратный
    private final Expression retExpr;
    // аддресс
    @Setter
    private Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // проверяем, находимся ли мы в блоке функции
        if (Compiler.code.getWriteTo().empty()
                || !(Compiler.code.getWriteTo().lastElement() instanceof VmFunction)) {
            PolarLogger.exception("can't use 'require' outside of a func.", address);
        }
        // условие
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.visitInstr(ifInstr);
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        logical.compile();
        ifInstr.setWritingConditions(false);
        Compiler.code.endWrite();
        // else
        VmInstrIf elseInstr = new VmInstrIf(address.convert());
        Compiler.code.startWrite(elseInstr);
        elseInstr.setWritingConditions(true);
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), true));
        elseInstr.setWritingConditions(false);
        new ReturnStatement(retExpr).compile();
        Compiler.code.endWrite();
        ifInstr.setElse(elseInstr);
    }

    // конструктор
    public RequireStatement(Expression logical, Expression retExpr) {
        this.logical = logical;
        this.retExpr = retExpr;
    }
}
