package com.slavlend.Executor;

/*
Настройки экзекьютера
 */
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

    // геттеры
    public String getFilePath() {
        return filePath;
    }

    public String getCode() {
        return code;
    }
}
