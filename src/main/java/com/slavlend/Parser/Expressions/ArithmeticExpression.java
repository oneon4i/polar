package com.slavlend.Parser.Expressions;


import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Operator;
import com.slavlend.Logger.PolarLogger;

/*
Арифмитическое выражение
 */
public class ArithmeticExpression implements Expression {
    // правая и левая стороны
    public Expression r;
    public Expression l;
    // оператор
    public Operator operator;
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        PolarValue right = r.evaluate();
        PolarValue left = l.evaluate();

        // класс и строка
        if (right.isString() && left.isClass()) {
            return new PolarValue(right.asString() + left.asClass().toString());
        }
        else if (right.isClass() && left.isString()) {
            return new PolarValue(right.asClass().toString() + left.asString());
        }
        // строка и строка
        if (right.isString() && left.isString()) {
            return new PolarValue(right.asString() + left.asString());
        }
        // функция и строка
        if (right.isFunc() && left.isString()) {
            return new PolarValue(right.asFunc().name + left.asString());
        }
        else if (right.isString() && left.isFunc()) {
            return new PolarValue(right.asString() + left.asFunc().name);
        }
        // число и строка
        else if (right.isNumber() && left.isString()) {
            return new PolarValue(right.asString() + left.asString());
        }
        else if (right.isString() && left.isNumber()) {
            return new PolarValue(right.asString() + left.asString());
        }
        // число и число
        else if (right.isNumber() && left.isNumber()) {
            if (operator.operator.equals("+")) {
                return new PolarValue(right.asNumber() + left.asNumber());
            }
            else if (operator.operator.equals("*")) {
                return new PolarValue(right.asNumber() * left.asNumber());
            }
            else if (operator.operator.equals("/")) {
                return new PolarValue(right.asNumber() / left.asNumber());
            }
            else if (operator.operator.equals("-")) {
                return new PolarValue(right.asNumber() - left.asNumber());
            }
            else if (operator.operator.equals("%")) {
                return new PolarValue(right.asNumber() % left.asNumber());
            }
            else {
                PolarLogger.Crash("Cannot Concat: " + right.data + " and " + left.data, address);
                return null;
            }
        }

        PolarLogger.Crash("Cannot Concat: " + right.data + " and " + left.data, address);
        return null;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {

    }

    // конструктор
    public ArithmeticExpression(Expression r, Operator operator, Expression l) {
        this.r = r; this.operator = operator; this.l = l;
    }

}
