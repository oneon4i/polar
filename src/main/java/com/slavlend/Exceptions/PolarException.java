package com.slavlend.Exceptions;

import lombok.Getter;

/*
Исключение
 */
@Getter
public class PolarException extends RuntimeException {
    // ошибка
    private final String error;
    // линия ошибки
    private final int line;
    // стак трейс
    private final StackTraceElement[] stackTrace;

    public PolarException(String error, int line, StackTraceElement[] stackTrace) {
        this.error = error;
        this.line = line;
        this.stackTrace = stackTrace;
    }
}
