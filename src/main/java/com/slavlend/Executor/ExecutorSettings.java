package com.slavlend.Executor;

import lombok.Getter;

/*
Настройки экзекьютера
 */
@Getter
public class ExecutorSettings {
    // путь к файлу
    private final String filePath;
    // код
    private final String code;
    // режим компиляции
    private final Boolean compilerMode;

    // конструктор
    public ExecutorSettings(String filePath, String code, Boolean compilerMode) {
        this.filePath = filePath;
        this.code = code;
        this.compilerMode = compilerMode;
    }
}
