package com.slavlend.Parser.Expressions;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

/*
Интерфейс экспрешенна
 */
public interface Expression {
    abstract PolarValue evaluate();
    abstract Address address();
    abstract void compile();
}
