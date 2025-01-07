package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import lombok.Getter;

/*
Стэйтмент для ассерта ( проверяет условие )
 */
@Getter
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
                Optimizations.optimize(expr.getL()),
                expr.getO(),
                Optimizations.optimize(expr.getR())
        );
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // проверяем условие
        if (!expr.evaluate().asBool()) {
            PolarLogger.exception("Assertion Error", address);
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

    @Override
    public void compile() {

    }
}
