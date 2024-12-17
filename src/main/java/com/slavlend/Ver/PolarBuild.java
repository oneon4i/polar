package com.slavlend.Ver;

/*
Билд версии языка
 */
public class PolarBuild {
    // мажорная версия
    private final int major;
    // минорная версия
    private final int minor;
    // патч версии
    private final int patch;

    // конструктор
    public PolarBuild(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    // в строку
    @Override
    public String toString() {
        return String.format("%s. %s. %s", major, minor, patch);
    }
}
