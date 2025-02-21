package com.slavlend.Compiler.Libs;

/*
 Библиотека для рандома.
 */
public class Random {
    // рандом
    private final java.util.Random random = new java.util.Random();

    // получение рандомного числа - целого
    public float randomNumber(Float origin, Float bound, boolean isInteger) {
        int _origin = origin.intValue();
        int _bound = bound.intValue();
        if (isInteger) {
            return random.nextInt(_origin, _bound);
        } else {
            return random.nextFloat(_bound, _bound);
        }
    }
}
