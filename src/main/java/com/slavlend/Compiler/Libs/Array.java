package com.slavlend.Compiler.Libs;

import com.slavlend.Vm.Instructions.VmInstrCondOperator;

import java.util.ArrayList;

/*
Библиотека списков для VM
 */
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
}
