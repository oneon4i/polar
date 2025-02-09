package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrIf;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class IfStatement implements Statement {
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
    // в ином случае
    @Setter
    private IfStatement elseCondition = null;
    // логическое выражение для ифа
    private final Expression expression;
    // адресс
    @Setter
    private Address address = App.parser.address();

    // добавление стейтмента в блок
    public void add(Statement statement) {
        statements.add(statement);
    }

    // копирование
    @Override
    public Statement copy() {
        IfStatement _copy = new IfStatement(expression);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        if (elseCondition != null) {
            _copy.elseCondition = (IfStatement) elseCondition.copy();
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.visitInstr(ifInstr);
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        expression.compile();
        ifInstr.setWritingConditions(false);
        for (Statement s : statements) {
            s.compile();
        }
        Compiler.code.endWrite();
        if (elseCondition != null) {
            ifInstr.setElse(elseCondition.getCompiled());
        }
    }

    public VmInstrIf getCompiled() {
        VmInstrIf ifInstr = new VmInstrIf(address.convert());
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        expression.compile();
        ifInstr.setWritingConditions(false);
        for (Statement s : statements) {
            s.compile();
        }
        Compiler.code.endWrite();
        if (elseCondition != null) {
            ifInstr.setElse(elseCondition.getCompiled());
        }
        return ifInstr;
    }

    // конструктор
    public IfStatement(Expression e) {
        this.expression = e;
    }
}
