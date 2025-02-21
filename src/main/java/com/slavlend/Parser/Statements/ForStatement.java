package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Фор стэйтмент - цикл
 */
@Getter
public class ForStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final Expression logic;
    // адресс
    @Setter
    private Address address = App.parser.address();
    // имя переменной
    private final String variable;
    // значение
    private final Expression valueExpr;

    // добавка стейтмента в блок
    public void add(Statement statement) {
        statements.add(statement);
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // запись переменной
        valueExpr.compile();
        Compiler.code.visitInstr(new VmInstrStoreL(address.convert(), variable));
        // цикл
        VmInstrLoop loop = new VmInstrLoop(address.convert());
        Compiler.code.visitInstr(loop);
        // начинаем писать в цикл
        Compiler.code.startWrite(loop);
        // начинаем писать условие
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.visitInstr(ifInstr);
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        logic.compile();
        ifInstr.setWritingConditions(false);
        for (Statement s : statements) {
            s.compile();
        }
        // заканчиваем писать условие
        Compiler.code.endWrite();
        VmInstrIf elseInstr = new VmInstrIf(address.convert());
        // начинаем писать else-условие
        Compiler.code.startWrite(elseInstr);
        elseInstr.setWritingConditions(true);
        elseInstr.visitInstr(new VmInstrPush(address.convert(),true));
        elseInstr.setWritingConditions(false);
        elseInstr.visitInstr(new VmInstrLoopEnd(address.convert(), false));
        ifInstr.setElse(elseInstr);
        // выходим
        Compiler.code.endWrite();
        Compiler.code.endWrite();
        // удаление переменной
        Compiler.code.visitInstr(new VmInstrDelL(address.convert(), variable));
    }

    // конструктор
    public ForStatement(Expression logic, String variable, Expression valueExpr) {
        this.logic = logic;
        this.variable = variable;
        this.valueExpr = valueExpr;
    }
}
