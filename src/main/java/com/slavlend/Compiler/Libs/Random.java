package com.slavlend.Compiler.Libs;

// Рандомное число
public class Random {
    // рандом
    private final java.util.Random random = new java.util.Random();

    // получение рандомного числа - целого
    public float randomNumber(Object origin, Object bound, Object isInteger) {
        int _origin = ((Float)origin).intValue();
        int _bound = ((Float)bound).intValue();
        boolean _isInteger = (boolean)isInteger;
        if (_isInteger) {
            return random.nextInt(_origin, _bound);
        } else {
            return random.nextFloat(_bound, _bound);
        }
    }
}
