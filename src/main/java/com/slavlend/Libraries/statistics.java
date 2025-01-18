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

    public PolarValue median(PolarObject arr) {
        array _array = (array) arr.getClassValues().get("arr").asObject().getClassValues().get("javaArr")
                .asReflected().getReflectedObject();
        // [0, 1, 2, 3]
        if (_array.size().asNumber() % 2 == 0) {
            int v = ((Float)(_array.size().asNumber()/2)).intValue()-1;
            System.out.println("f: " + _array.get(v).asNumber());
            System.out.println("s: " + _array.get(v+1).asNumber());
            return new PolarValue(
                     (_array.get(v).asNumber()+_array.get(v+1).asNumber())/2
            );
        }
        // [0, 1, 2, 3, 4]
        else {
            // 1.5
            Float s = ((Float)_array.size().asNumber()/2)-1;
            // 2
            int floored = ((Double)Math.ceil(s.doubleValue())).intValue();
            return _array.get(floored);
        }
    }
}
