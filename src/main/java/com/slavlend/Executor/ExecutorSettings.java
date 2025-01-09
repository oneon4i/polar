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

    // конструктор
    public ExecutorSettings(String filePath, String code) {
        this.filePath = filePath;
        this.code = code;
    }
}
