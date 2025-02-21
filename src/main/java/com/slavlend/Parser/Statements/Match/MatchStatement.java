package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.PolarLogger;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Vm.Instructions.VmInstrIf;
import lombok.Setter;

import java.util.ArrayList;

public class MatchStatement implements Statement {
    // мэтч экспрешенн
    private final Expression matchExpr;
    // кейсы
    private final ArrayList<Statement> statements = new ArrayList<>();
    // адресс
    @Setter
    private Address address = App.parser.address();

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmInstrIf first = null;
        VmInstrIf last = null;
        for (Statement s : statements) {
            VmInstrIf compiled;
            if (s instanceof CaseStatement caseStatement) {
                compiled = caseStatement.getCompiled(matchExpr);
            } else if (s instanceof DefaultStatement defStatement) {
                compiled = defStatement.getCompiled();
            } else {
                PolarLogger.exception("Cannot Use: " + s + " In Match Statement!", address);
                return;
            }
            if (last != null) {
                last.setElse(compiled);
            }
            if (first == null) {
                first = compiled;
            }
            last = compiled;
        }
        Compiler.code.visitInstr(first);
    }

    // конструктор
    public MatchStatement(Expression m) {
        this.matchExpr = m;
    }
}
