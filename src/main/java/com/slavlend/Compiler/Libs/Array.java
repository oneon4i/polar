package com.slavlend.Compiler.Libs;

import com.slavlend.Vm.Instructions.VmInstrCondOperator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Библиотека для работы со списками
 */
@Getter
public class Array {
    private List<Object> array = new ArrayList<>();

    public void add(Object o) {
        array.add(o);
    }

    public void del(Float index) {
        array.remove(index.intValue());
    }

    public Object get(Float index) {
        return array.get(((Float)index).intValue());
    }

    public Object contains(Object obj) {
        for (Object o : array) {
            if (VmInstrCondOperator.equal(o, obj)) {
                return true;
            }
        }

        return false;
    }

    public void set(Float i, Object v) {
        array.set(i.intValue(), v);
    }

    public Object size() {
        return ((Integer)array.size()).floatValue();
    }

    public Object stringify() {
        StringBuilder s = new StringBuilder();
        for (Object o : array) {
            s.append(o);
        }
        return s.toString();
    }
    public void insert(Float i, Object v) {
        array.add(i.intValue(), v);
    }

    public float index_of(Object obj) {
        for (Object o : array) {
            if (VmInstrCondOperator.equal(o, obj)) {
                return array.indexOf(o);
            }
        }

        return -1;
    }
    public static Array of(List<Object> values) {
        Array arr = new Array();
        arr.array = values;
        return arr;
    }
    public void del_all(Array arr) {
        this.array.removeAll(arr.array);
    }
}
