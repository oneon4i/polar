package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrDelL;
import com.slavlend.Vm.Instructions.VmInstrSafe;
import com.slavlend.Vm.Instructions.VmInstrStore;
import com.slavlend.Vm.Instructions.VmInstrStoreL;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Сэйф стэйтмент - гарантирует
безопасное исполнение кода.
Ловит ошибки и передает в стейтмент
handle
 */
@Getter
public class SafeStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // тело при пойманом throwable
    private final ArrayList<Statement> handleStatement = new ArrayList<>();
    // имя переменной
    @Setter
    private String variableName;
    // адресс
    private final Address address = App.parser.address();

    public void add(Statement statement) {
        statements.add(statement);
    }
    public void addHandleStatement(Statement statement) {
        handleStatement.add(statement);
    }

    // копирование
    @Override
    public Statement copy() {
        SafeStatement _copy = new SafeStatement(variableName);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        for (Statement statement : handleStatement) {
            _copy.addHandleStatement(statement.copy());
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
        VmInstrSafe vmInstrSafe = new VmInstrSafe(address.convert());
        Compiler.code.startWrite(vmInstrSafe);
        // компилируем стейтменты
        for (Statement s : statements) {
            s.compile();
        }
        // компилируем стейтменты хэндла
        vmInstrSafe.setWritingHandlePart(true);
        Compiler.code.visitInstr(new VmInstrStoreL(address.convert(), variableName));
        for (Statement hS : handleStatement) {
            hS.compile();
        }
        Compiler.code.visitInstr(new VmInstrDelL(address.convert(), variableName));
        vmInstrSafe.setWritingHandlePart(false);
        Compiler.code.endWrite();
        Compiler.code.visitInstr(vmInstrSafe);
    }

    // конструктор
    public SafeStatement(String variableName) {
        this.variableName = variableName;
    }
}
