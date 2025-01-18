package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Operator;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarValue;
import lombok.Getter;

/*
Эксперешен логики - возвращает бул
 */
@Getter
public class LogicExpression implements Expression {
    // правая и левая стороны
    private final Expression r, l;
    // оператор
    private final Operator o;
    // адресс
    private final Address address = App.parser.address();

    // конструктор
    public LogicExpression(Expression l, Operator o, Expression r) {
        this.l = l;
        this.r = r;
        this.o = o;
    }

    @Override
    public PolarValue evaluate() {
        switch (o.operator) {
            case "&&" -> {
                return new PolarValue(r.evaluate().asBool() && l.evaluate().asBool());
            }
            case "||" -> {
                return new PolarValue(r.evaluate().asBool() || l.evaluate().asBool());
            }
            default -> {
                PolarLogger.exception("Unexpected value: " + o.operator, address);
                return null;
            }
        }
    }

    @Override
    public Address address() {
        return address;
    }
}
