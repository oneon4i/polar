package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.BackStatement;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Vm.Instructions.VmInstrPush;
import com.slavlend.Vm.VmFunction;

import java.util.ArrayList;
import java.util.List;

/*
Лямбда-выражение или анонимное объявление
функции
 */
public class LambdaExpression implements Expression {
    // адресс
    private final Address address = App.parser.address();
    // тело
    private final List<Statement> body = new ArrayList<>();
    // аргументы
    private final ArrayList<String> arguments;

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmFunction fn = new VmFunction("lambda", arguments, address.convert());
        Compiler.code.startWrite(fn);
        for (Statement s : body) {
            s.compile();
        }
        new BackStatement(null).compile();
        Compiler.code.endWrite();
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), fn));
    }

    public LambdaExpression(ArrayList<String> args) {
        this.arguments = args;
    }

    public void add(Statement statement) {
        body.add(statement);
    }
}
