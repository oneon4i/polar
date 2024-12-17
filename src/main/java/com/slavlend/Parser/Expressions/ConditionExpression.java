package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Parser.Expressions.Access.VarAccess;
import com.slavlend.Parser.Operator;

/*
Эксперешен кондишен - возвращает бул
 */
public class ConditionExpression implements Expression {
    // право и лево
    public Expression r, l;
    // оператор
    public Operator o;
    // адресс
    private Address address = App.parser.address();

    // конструктор
    public ConditionExpression(Expression l, Operator o, Expression r) {
        this.l = l;
        this.r = r;
        this.o = o;
    }

    // для разных операторов -> разные проверки

    // оператор ==
    public boolean result() {
        PolarValue right = r.evaluate();
        PolarValue left = l.evaluate();
        return left.equal(address, right);
    }

    // оператор >
    public boolean bigger() {
        PolarValue right = r.evaluate();
        PolarValue left = l.evaluate();
        return left.asNumber() > right.asNumber();
    }

    // оператор <
    public boolean lower() {
        PolarValue right = r.evaluate();
        PolarValue left = l.evaluate();
        return left.asNumber() < right.asNumber();
    }

    // конструкция is
    private boolean is() {
        PolarValue left = l.evaluate();
        if (left.isObject()) {
            PolarObject _object = left.asObject();
            String name = ((VarAccess) ((AccessExpression) r).GetLast()).varName;
            return _object.clazz.name.equals(name);
        }
        else {
            return false;
        }
    }

    @Override
    public PolarValue evaluate() {
        if (o.operator.equals("==")) {
            return new PolarValue(result());
        }
        else if (o.operator.equals("!=")) {
            return new PolarValue(!result());
        }
        else if (o.operator.equals(">=")) {
            return new PolarValue(result() || bigger());
        }
        else if (o.operator.equals("<=")) {
            return new PolarValue(result() || lower());
        }
        else if (o.operator.equals(">")) {
            return new PolarValue(bigger());
        }
        else if (o.operator.equals("is")) {
            return new PolarValue(is());
        }
        else {
            return new PolarValue(lower());
        }
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }
}
