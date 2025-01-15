package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.VmFunction;
import lombok.Getter;

import java.util.ArrayList;

/*
Стэйтмент функции - для дефенишена функции
 */
@Getter
public class FunctionStatement implements Statement {
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
    // аргументы
    private final ArrayList<String> arguments;
    // имя
    private final String name;

    // адресс
    private final Address address = App.parser.address();

    // добавление в бади
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public Statement copy() {
        FunctionStatement _copy = new FunctionStatement(name, arguments);

        for (Statement statement : statements) {
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
        VmFunction f = new VmFunction(name, arguments, address.convert());
        Compiler.code.defineFunction(address.convert(), f);
        Compiler.code.startWrite(f);
        for (Statement s : statements) {
            s.compile();
        }
        new BackStatement(null).compile();
        Compiler.code.endWrite();
    }

    // конструктор
    public FunctionStatement(String name, ArrayList<String> arguments) {
        this.name = name;
        this.arguments = arguments;
    }
}
