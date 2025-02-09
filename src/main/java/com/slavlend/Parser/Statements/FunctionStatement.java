package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.VmFunction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Стэйтмент функции - для дефенишена функции
 */
@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
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
        returnStatement.setAddress(body.get(body.size()-1).address());
        returnStatement.compile();
        Compiler.code.endWrite();
    }

    // конструктор
    public FunctionStatement(String fullName, String name, ArrayList<String> arguments) {
        this.fullName = fullName;
        this.name = name;
        this.arguments = arguments;
    }
}
