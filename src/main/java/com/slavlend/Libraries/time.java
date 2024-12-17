package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;

/*
Библиотека для работы со временем
 */
public class time {
    // наносекунды
    public PolarValue get_ns() {
        return new PolarValue((float) System.nanoTime());
    }
    // миллисекунды
    public PolarValue get_ms() {
        return new PolarValue((float) System.currentTimeMillis());
    }
    // секунды
    public PolarValue get_sec() {
        return new PolarValue((float) System.currentTimeMillis() / 1000);
    }
}
