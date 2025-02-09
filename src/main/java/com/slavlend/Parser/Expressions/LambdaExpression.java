package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.ReturnStatement;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Vm.Instructions.VmInstrDup;
import com.slavlend.Vm.Instructions.VmInstrMakeClosure;
import com.slavlend.Vm.Instructions.VmInstrPush;
import com.slavlend.Vm.VmFunction;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
Лямбда-выражение или анонимное объявление
функции
 */
public class LambdaExpression implements Expression {
    // тело
    private final List<Statement> body = new ArrayList<>();
    // аргументы
    private final ArrayList<String> arguments;
    // адресс
    private final Address address = App.parser.address();
    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmFunction fn = new VmFunction("lambda", "lmb:lambda", arguments, address.convert());
        Compiler.code.startWrite(fn);
        for (Statement s : body) {
            s.compile();
        }
        new ReturnStatement(null).compile();
        Compiler.code.endWrite();
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), fn));
        Compiler.code.visitInstr(new VmInstrDup(address.convert()));
        Compiler.code.visitInstr(new VmInstrMakeClosure(address.convert()));
    }

    public LambdaExpression(ArrayList<String> args) {
        this.arguments = args;
    }

    public void add(Statement statement) {
        body.add(statement);
    }
}
