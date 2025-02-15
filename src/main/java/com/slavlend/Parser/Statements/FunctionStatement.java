package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Vm.Instructions.VmInstrDecorate;
import com.slavlend.Vm.VmFunction;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Стэйтмент функции - для дефенишена функции
 */
@Getter
public class FunctionStatement implements Statement {
    // тело функции
    private final ArrayList<Statement> body = new ArrayList<>();
    // аргументы
    private final ArrayList<String> arguments;
    // полное имя
    private final String fullName;
    // имя
    private final String name;
    // декоратор
    @Setter
    private AccessExpression decorator;

    // адресс
    @Setter
    private Address address = App.parser.address();

    // добавление в бади
    public void add(Statement statement) {
        body.add(statement);
    }

    @Override
    public Statement copy() {
        FunctionStatement _copy = new FunctionStatement(fullName, name, arguments);

        for (Statement statement : body) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // функция
        VmFunction f = new VmFunction(name, fullName, arguments, address.convert());
        Compiler.code.defineFunction(address.convert(), f);
        Compiler.code.startWrite(f);
        for (Statement s : body) {
            s.compile();
        }
        ReturnStatement returnStatement = new ReturnStatement(null);
        Address retAddress = !body.isEmpty() ? body.get(body.size()-1).address() : address;
        returnStatement.setAddress(retAddress);
        returnStatement.compile();
        Compiler.code.endWrite();
        // декоратор
        if (decorator != null) {
            VmVarContainer decoratorContainer = new VmVarContainer();
            Compiler.code.startWrite(decoratorContainer);
            decorator.compile();
            Compiler.code.endWrite();
            Compiler.code.visitInstr(new VmInstrDecorate(address.convert(), decoratorContainer, f));
        }
    }

    // конструктор
    public FunctionStatement(String fullName, String name, ArrayList<String> arguments) {
        this.fullName = fullName;
        this.name = name;
        this.arguments = arguments;
    }
}
