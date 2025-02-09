package com.slavlend.Parser.Expressions;

import com.slavlend.Parser.Address;
import lombok.Setter;

/*
Интерфейс экспрешенна
 */
public interface Expression {
    abstract Address address();
    abstract void compile();
}
