package com.slavlend.Compiler.Libs;

import com.slavlend.Vm.Instructions.VmInstrCondOperator;

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

    public Object stringify() {
        return map.toString();
    }

    public Object get(Object k) {
        for (Object o : map.keySet()) {
            if (VmInstrCondOperator.equal(k, o)) {
                return map.get(o);
            }
        }
        throw new RuntimeException("key not found: " + k);
    }

    public Object has_key(Object obj) {
        for (Object o : map.keySet()) {
            if (VmInstrCondOperator.equal(o, obj)) {
                return true;
            }
        }

        return false;
    }

    public Object has_value(Object obj) {
        for (Object o : map.values()) {
            if (VmInstrCondOperator.equal(o, obj)) {
                return true;
            }
        }

        return false;
    }

    public Array keys() {
        return Array.of(map.keySet().stream().toList());
    }

    public Array values() {
        return Array.of(map.values().stream().toList());
    }
    public float size() {
        return map.size();
    }
}
