package com.slavlend.Libraries;

import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            // System.out.println("f: " + _array.get(v).asNumber());
            // System.out.println("s: " + _array.get(v+1).asNumber());
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

    public PolarValue mode(PolarValue arr) {
        array _array = (array) arr.asObject().getClassValues().get("arr").asObject().getClassValues().get("javaArr")
                .asReflected().getReflectedObject();
        ArrayList<Float> arrayList = new ArrayList<Float>();
        for (PolarValue v : _array.getValueArrayList()) {
            if (v.isNumber()) {
                arrayList.add(v.asNumber());
            } else {
                PolarLogger.exception("Cannot Use Not Number Array In Mode Func", arr.getInstantiateAddress());
            }
        }
        Map<Float, Integer> frequencyMap = new HashMap<>();
        for (float num : arrayList) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        float mode = 0;
        int maxAmount = 0;
        for (Map.Entry<Float, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxAmount) {
                mode = entry.getKey();
                maxAmount = entry.getValue();
            }
        }
        return new PolarValue(mode);
    }

    public PolarValue hmean(PolarValue arr) {
        array _array = (array) arr.asObject().getClassValues().get("arr").asObject().getClassValues().get("javaArr")
                .asReflected().getReflectedObject();
        ArrayList<Float> arrayList = new ArrayList<>();
        for (PolarValue v : _array.getValueArrayList()) {
            if (v.isNumber()) {
                arrayList.add(v.asNumber());
            } else {
                PolarLogger.exception("Cannot Use Not Number Array In Hmean Func", arr.getInstantiateAddress());
            }
        }
        float revertSum = 0;
        for (Float f : arrayList) {
            revertSum += 1/f;
        }
        return new PolarValue(_array.size().asNumber()/revertSum);
    }
}
