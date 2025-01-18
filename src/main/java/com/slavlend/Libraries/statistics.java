package com.slavlend.Libraries;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;

/*
Модуль статистики для языка.
Необходим для вычислений статистика.
 */
public class statistics {
    public PolarValue mean(PolarObject arr) {
        array _array = (array) arr.getClassValues().get("arr").asObject().getClassValues().get("javaArr")
                .asReflected().getReflectedObject();
        float sum = 0;
        for (PolarValue elem : _array.getValueArrayList()) {
            sum += elem.asNumber();
        }
        return new PolarValue(sum/_array.size().asNumber());
    }
}
