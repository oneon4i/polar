package com.slavlend.Compiler.Libs;

import com.slavlend.Vm.Instructions.VmInstrCondOperator;
import lombok.Getter;

import java.util.ArrayList;

/*
Библиотека списков для VM
 */
@Getter
public class Array {
    private final ArrayList<Object> array = new ArrayList<>();

    public void add(Object o) {
        array.add(o);
    }

    public void del(Object index) {
        array.remove((int)index);
    }

    public Object get(Object index) {
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

    public void set(Object i, Object v) {
        array.set(((Float)i).intValue(), v);
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
}
