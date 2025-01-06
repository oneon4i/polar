package com.slavlend.VM;

import lombok.Getter;

/*
Переменная
 */
@Getter
public class HasNext {
    private final Object value;

    public HasNext(Object value) {
        this.value = value;
    }
}
