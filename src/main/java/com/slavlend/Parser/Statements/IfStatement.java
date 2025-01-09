package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Operator;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.VM.Instructions.VmInstrComputeConds;
import com.slavlend.VM.Instructions.VmInstrIf;
import com.slavlend.VM.VmInstr;
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
    // кодишены для ифа
    private final ArrayList<Expression> conditions;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();

        // если условие сработало
        if (conditions()) {
            // стэйтменты
            for (Statement statement : statements) {
                 statement.execute();
            }
        }
        else {
            // если условие не сработало
            // экзекьютим если есть что-то в ином случае
            if (elseCondition != null) {
                elseCondition.execute();
            }
        }
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    // кодишены
    public boolean conditions() {
        for (Expression e : conditions) {
            PolarValue v = e.evaluate();
            if (!v.asBool()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        IfStatement _copy = new IfStatement(conditions);

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
        VmInstrIf ifInstr = new VmInstrIf();
        Compiler.code.visitInstr(ifInstr);
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        compileConditions();
        ifInstr.setWritingConditions(false);
        for (Statement s : statements) {
            s.compile();
        }
        Compiler.code.endWrite();
        if (elseCondition != null) {
            ifInstr.setElse(elseCondition.getCompiled());
        }
    }

    private void compileConditions() {
        int conditionsAmount = 0;
        for (Expression cond : conditions) {
            cond.compile();
            if (conditionsAmount+1 == 2) {
                Compiler.code.visitInstr(new VmInstrComputeConds(new Operator("&&")));
            }
            else {
                conditionsAmount += 1;
            }
        }
    }

    public VmInstrIf getCompiled() {
        VmInstrIf ifInstr = new VmInstrIf();
        Compiler.code.startWrite(ifInstr);
        ifInstr.setWritingConditions(true);
        compileConditions();
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
    public IfStatement(ArrayList<Expression> expressions) {
        this.conditions = expressions;
    }
}
