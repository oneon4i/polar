package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Env.PolarEnv;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;

/*
Стэйтмент для ассерта ( проверяет условие )
 */
public class AssertStatement implements Statement {
    // экспрешен
    private ConditionExpression expr;
    // аддресс
    private final Address address = App.parser.address();

    // конструктор
    public AssertStatement(ConditionExpression expr) {
        this.expr = expr;
    }

    @Override
    public void optimize() {
        // оптимизация
        expr = new ConditionExpression(
                Optimizations.optimize(expr.l),
                expr.o,
                Optimizations.optimize(expr.r)
        );
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // проверяем условие
        if (!expr.evaluate().asBool()) {
            PolarEnv.Crash("Assertion Error", address);
        }
    }

    @Override
    public void interrupt() {
        // ...
    }

    @Override
    public Statement copy() {
        return new AssertStatement(expr);
    }

    @Override
    public Address address() {
        return address;
    }
}
