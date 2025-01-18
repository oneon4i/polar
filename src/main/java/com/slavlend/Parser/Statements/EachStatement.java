package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Parser.Expressions.ConditionExpression;
import com.slavlend.Parser.Operator;
import com.slavlend.Vm.Instructions.*;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.UUID;

/*
Фор стэйтмент - цикл
 */
@SuppressWarnings("PointlessBooleanExpression")
@Getter
public class EachStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final ArrayList<ConditionExpression> conditions = new ArrayList<>();
    // адресс
    private final Address address = App.parser.address();
    // имя переменной
    private final String variableName;
    // имя переменной списка
    private final AccessExpression listVariable;

    // добавка стейтмента в блок
    public void add(Statement statement) {
        statements.add(statement);
    }

    // копирование

    @Override
    public Statement copy() {
        EachStatement _copy = new EachStatement(listVariable, variableName);

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
        // ЮИД
        String uuid = UUID.randomUUID().toString();
        // сохраняем переменную
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), 0f));
        Compiler.code.visitInstr(new VmInstrStoreL(address.convert(), uuid));
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
        Compiler.code.visitInstr(new VmInstrLoad(address.convert(), uuid, false));
        listVariable.compile();
        Compiler.code.visitInstr(new VmInstrCall(address.convert(), "size", new VmVarContainer(), true));
        Compiler.code.visitInstr(new VmInstrCondOperator(address.convert(), new Operator("<")));
        ifInstr.setWritingConditions(false);
        VmVarContainer args = new VmVarContainer();
        Compiler.code.startWrite(args);
        listVariable.compile();
        VmVarContainer fnArgs = new VmVarContainer();
        Compiler.code.startWrite(fnArgs);
        Compiler.code.visitInstr(new VmInstrLoad(address.convert(), uuid, false));
        Compiler.code.endWrite();
        listVariable.compile();
        Compiler.code.visitInstr(new VmInstrCall(address.convert(), "get", fnArgs, true));
        Compiler.code.endWrite();
        Compiler.code.visitInstr(new VmInstrStore(address.convert(), variableName, false, args));
        for (Statement s : statements) {
            s.compile();
        }
        Compiler.code.visitInstr(new VmInstrLoad(address.convert(), uuid, false));
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), 1f));
        Compiler.code.visitInstr(new VmInstrArith(address.convert(), "+"));
        Compiler.code.visitInstr(new VmInstrStoreL(address.convert(), uuid));
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
        Compiler.code.visitInstr(new VmInstrDelL(address.convert(), uuid));
    }

    // конструктор
    public EachStatement(AccessExpression arr, String name) {
        this.listVariable = arr;
        this.variableName = name;
    }
}
