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
    // дебаг мод
    private final boolean debugMode;

    // конструктор
    public ExecutorSettings(String filePath, String code, boolean debugMode) {
        this.filePath = filePath;
        this.code = code;
        this.debugMode = debugMode;
    }
}
