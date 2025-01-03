package com.slavlend.Exceptions;

import java.util.List;

/*
Исключение
 */
public class PolarException extends RuntimeException {
    // ошибка
    private final String error;
    // линия ошибки
    private final int line;

    public PolarException(String error, int line) {
        this.error = error;
        this.line = line;
    }

    public String getLine() {
        return String.valueOf(line);
    }

    public String getError() {
        return error;
    }
}
