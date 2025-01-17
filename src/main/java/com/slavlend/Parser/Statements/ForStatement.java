package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Operator;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.*;
import lombok.Getter;

import java.util.ArrayList;

/*
Фор стэйтмент - цикл
 */
@Getter
public class ForStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final ArrayList<ConditionExpression> conditions;
    // адресс
    private final Address address = App.parser.address();
    // имя переменной
    private final String variable;
    // значение
    private final Expression valueExpr;

    // добавка стейтмента в блок
    public void add(Statement statement) {
        statements.add(statement);
    }

    // копирование
    @Override
    public Statement copy() {
        ForStatement _copy = new ForStatement(conditions, variable, valueExpr);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
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
        compileConditions();
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
    public ForStatement(ArrayList<ConditionExpression> _conditions, String variable, Expression valueExpr) {
        this.conditions = _conditions;
        this.variable = variable;
        this.valueExpr = valueExpr;
    }

    private void compileConditions() {
        int conditionsAmount = 0;
        for (Expression cond : conditions) {
            cond.compile();
            if (conditionsAmount+1 == 2) {
                Compiler.code.visitInstr(new VmInstrComputeConds(address.convert()));
            }
            else {
                conditionsAmount += 1;
            }
        }
    }
}
