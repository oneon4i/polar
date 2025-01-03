package com.slavlend.Ver;

import lombok.Getter;

/*
Билд версии языка
 */
public class PolarBuild {
    // мажорная версия
    @Getter
    private final int major;
    // минорная версия
    @Getter
    private final int minor;
    // патч версии
    @Getter
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
        return String.format("%s.%s.%s", major, minor, patch);
    }
}
