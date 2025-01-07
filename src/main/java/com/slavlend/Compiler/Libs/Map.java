package com.slavlend.Compiler.Libs;

import com.slavlend.VM.Instructions.VmInstrCondOperator;

import java.util.HashMap;

/*
Библиотека мапы для VM
 */
public class Map {
    private final HashMap<Object, Object> map = new HashMap<Object, Object>();

    public void set(Object k, Object v) {
        map.put(k,v);
    }

    public void del(Object k) {
        for (Object o : map.keySet()) {
            if (VmInstrCondOperator.equal(k, o)) {
                map.remove(o);
            }
        }
    }

    public Object get(Object k) {
        for (Object o : map.keySet()) {
            if (VmInstrCondOperator.equal(k, o)) {
                return map.get(o);
            }
        }
        throw new RuntimeException("key not found: " + k);
    }

    public Object hasKey(Object obj) {
        for (Object o : map.keySet()) {
            if (VmInstrCondOperator.equal(o, obj)) {
                return true;
            }
        }

        return false;
    }
}
