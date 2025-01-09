package com.slavlend.Parser.Statements.Match;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.VM.Instructions.VmInstrIf;

import java.util.ArrayList;

public class MatchStatement implements Statement {
    // мэтч экспрешенн
    private Expression matchExpr;
    // кейсы
    private final ArrayList<Statement> statements = new ArrayList<>();
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // оптимизируем константной сверткой
        matchExpr = Optimizations.optimize(matchExpr);
    }

    @Override
    public void execute() {
        // стэйтмент дефаулт
        DefaultStatement defaultStatement = null;
        boolean _default = true;
        // стэйтменты
        for (Statement statement : statements) {
            // кейс стэйтмент
            if (statement instanceof CaseStatement) {
                // is right
                if (((CaseStatement) statement).isRight(matchExpr)) {
                    _default = false;
                }
                // value
                PolarValue _value = ((CaseStatement) statement).executeBy(matchExpr);
                if (_value != null) {
                    throw _value;
                }
            }
            // дефаулт стэйтмент
            else if (statement instanceof DefaultStatement) {
                defaultStatement = (DefaultStatement) statement;
            }
            else {
                PolarLogger.exception("Cannot Use Any Statement Except Default | Case In Match!", address);
            }
        }

        // дефолт вызов если все кейсы не сработали
        if (_default) {
            if (defaultStatement != null) {
                defaultStatement.execute();
            }
            else {
                PolarLogger.exception("Default Statement Not Found In Match Statement", address());
            }
        }
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        MatchStatement _copy = new MatchStatement(matchExpr);

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
