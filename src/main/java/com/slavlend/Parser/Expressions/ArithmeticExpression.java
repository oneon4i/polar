package com.slavlend.Parser.Expressions;


import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Operator;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.VM.Instructions.VmInstrArith;
import lombok.Getter;

/*
Арифмитическое выражение
 */
@Getter
public class ArithmeticExpression implements Expression {
    // правая и левая стороны
    private final Expression r, l;
    // оператор
    private final Operator operator;
    // адресс
    private final Address address = App.parser.address();

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
        // функция и строка
        if (right.isFunc() && left.isString()) {
            return new PolarValue(right.asFunc().getName() + left.asString());
        }
        else if (right.isString() && left.isFunc()) {
            return new PolarValue(right.asString() + left.asFunc().getName());
        }
        // число и число
        else if (right.isNumber() && left.isNumber()) {
            switch (operator.operator) {
                case "+" -> {
                    return new PolarValue(right.asNumber() + left.asNumber());
                }
                case "*" -> {
                    return new PolarValue(right.asNumber() * left.asNumber());
                }
                case "/" -> {
                    return new PolarValue(right.asNumber() / left.asNumber());
                }
                case "-" -> {
                    return new PolarValue(right.asNumber() - left.asNumber());
                }
                case "%" -> {
                    return new PolarValue(right.asNumber() % left.asNumber());
                }
                default -> {
                    PolarLogger.exception("Cannot Concat: " + right.getData() + " and " + left.getData(), address);
                    return new PolarValue(null);
                }
            }
        }
        // остальные случаи
        else {
            return new PolarValue(right.asString() + left.asString());
        }
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        r.compile();
        l.compile();
        Compiler.code.visitInstr(new VmInstrArith(operator));
    }

    // конструктор
    public ArithmeticExpression(Expression r, Operator operator, Expression l) {
        this.r = r; this.operator = operator; this.l = l;
    }

}
