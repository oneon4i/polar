package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;

import java.nio.file.Paths;

/*
Класс для работы с системой
 */
@SuppressWarnings("unused")
public class sys {
    // конструктор
    public sys() {

    }

    // завершение программы
    public void exit(int status) {
        System.exit(status);
    }

    // имя ос
    public PolarValue getOs() {
        return new PolarValue(System.getProperty("os.name"));
    }

    // получение переменной среды
    public PolarValue getEnv(PolarValue name) {
        return new PolarValue(System.getenv(name.asString()));
    }

    // получение текущей рабочей директории
    public PolarValue getCwd() {
        return new PolarValue(Paths.get(""));
    }
}
