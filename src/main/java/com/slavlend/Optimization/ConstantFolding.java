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
        // оптимизация арифметики
        if (expr instanceof ArithmeticExpression _expr) {
            Expression l = optimize(_expr.getL());
            Expression r = optimize(_expr.getR());

            // числа
            if (l instanceof NumberExpression first &&
                r instanceof NumberExpression second) {
                switch (_expr.getOperator().operator) {
                    case "+" -> {
                        return new NumberExpression(
                                String.valueOf(first.evaluate().asNumber() + second.evaluate().asNumber())
                        );
                    }
                    case "-" -> {
                        return new NumberExpression(
                                String.valueOf(first.evaluate().asNumber() - second.evaluate().asNumber())
                        );
                    }
                    case "*" -> {
                        return new NumberExpression(
                                String.valueOf(first.evaluate().asNumber() * second.evaluate().asNumber())
                        );
                    }
                    default -> {
                        return new NumberExpression(
                                String.valueOf(first.evaluate().asNumber() / second.evaluate().asNumber())
                        );
                    }
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
