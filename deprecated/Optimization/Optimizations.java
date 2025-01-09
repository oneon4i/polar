package com.slavlend.Optimization;

import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.FunctionStatement;

/*
Оптимизация AST
 */
public class Optimizations {
    // оптимизируем
    public static Expression optimize(Expression e) {
        Expression expr = new ConstantFolding().optimize(e);
        return e;
    }
}
