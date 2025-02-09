package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Expressions.LogicExpression;
import com.slavlend.Parser.Expressions.NumberExpression;
import com.slavlend.Parser.Operator;
import com.slavlend.Vm.Instructions.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

/*
Репит (повторение) стэйтмент - цикл
 */
@Getter
public class RepeatStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // логическое выражение
    private final Expression times;
    // адресс
    @Setter
    private Address address = App.parser.address();

    public void add(Statement statement) {
        statements.add(statement);
    }

    // копирование
    @Override
    public Statement copy() {
        RepeatStatement _copy = new RepeatStatement(times);

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
        new NumberExpression("-1").compile();
        String varName = UUID.randomUUID().toString();
        Compiler.code.visitInstr(new VmInstrStoreL(address.convert(), varName));
        // цикл
        VmInstrLoop loop = new VmInstrLoop(address.convert());
        Compiler.code.visitInstr(loop);
        // начинаем писать в цикл
        Compiler.code.startWrite(loop);
        Compiler.code.visitInstr(new VmInstrLoad(address.convert(), varName, false));
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), 1f));
        Compiler.code.visitInstr(new VmInstrArith(address.convert(), "+"));
        Compiler.code.visitInstr(new VmInstrStoreL(address.convert(), varName));
        // начинаем писать условие
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.visitInstr(ifInstr);
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        Compiler.code.visitInstr(new VmInstrLoad(address.convert(), varName, false));
        times.compile();
        Compiler.code.visitInstr(new VmInstrCondOperator(address.convert(), new Operator("<")));
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
        Compiler.code.visitInstr(new VmInstrDelL(address.convert(), varName));
    }

    // конструктор
    public RepeatStatement(Expression times) {
        this.times = times;
    }
}
