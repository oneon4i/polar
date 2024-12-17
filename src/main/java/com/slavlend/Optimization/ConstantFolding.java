package com.slavlend.Optimization;

import com.slavlend.Parser.Expressions.ArithmeticExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Expressions.NumberExpression;
import com.slavlend.Parser.Expressions.TextExpression;

/*
Оптимизация свертки констант
 */
public class ConstantFolding implements Optimization {
    @Override
    public Expression optimize(Expression expr) {
        // оптимизация конкатенации
        if (expr instanceof ArithmeticExpression _expr) {
            Expression l = optimize(_expr.l);
            Expression r = optimize(_expr.r);

            // числа
            if (l instanceof NumberExpression first &&
                r instanceof NumberExpression second) {
                if (_expr.operator.operator.equals("+")) {
                    NumberExpression o = new NumberExpression(
                            String.valueOf(first.evaluate().asNumber() + second.evaluate().asNumber())
                    );
                    return o;
                }
                else if (_expr.operator.operator.equals("-")) {
                    NumberExpression o = new NumberExpression(
                            String.valueOf(first.evaluate().asNumber() - second.evaluate().asNumber())
                    );
                    return o;
                }
                else if (_expr.operator.operator.equals("*")) {
                    NumberExpression o = new NumberExpression(
                            String.valueOf(first.evaluate().asNumber() * second.evaluate().asNumber())
                    );
                    return o;
                }
                else {
                    NumberExpression o = new NumberExpression(
                            String.valueOf(first.evaluate().asNumber() / second.evaluate().asNumber())
                    );
                    return o;
                }
            }
            // строки
            else if (l instanceof TextExpression first &&
                    r instanceof TextExpression second) {
                return new TextExpression(
                        first.evaluate().asString() + second.evaluate().asString()
                );
            }
            // в ином случае
            else {
                return _expr;
            }
        }
        else {
            return expr;
        }
    }
}
