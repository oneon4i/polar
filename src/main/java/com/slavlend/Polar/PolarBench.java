package com.slavlend.Polar;

import lombok.Getter;

/*
Бенчмарк Полара
 */
@Getter
public class PolarBench {
    private long startTime;

    // Начало
    public void start() {
        startTime = System.currentTimeMillis();
    }

    // Конец
    public long end() {
        return System.currentTimeMillis()-startTime;
    }
}
