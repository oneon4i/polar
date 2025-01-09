package com.slavlend.Optimization;

import com.slavlend.Parser.Expressions.Expression;

/*
Интерфейс оптимизации экспрешеннов
 */
public interface Optimization {
    public Expression optimize(Expression expr);
}
