package com.slavlend.Vm;

import java.util.ArrayList;
import java.util.List;

/*
Класс виртуальной машины
 */
public class VmClass {
    // имя
    private String name;
    // функции
    public List<VmFn> fnList = new ArrayList<>();
    // модульные переменные
    public List<VmVal> modVals = new ArrayList<>();
    // конструктор
    public List<String> constructor = new ArrayList<>();

    // конструктор
    public VmClass(String name, List<VmFn> fns, List<String> constructor) {
        this.name = name;
        this.fnList = fns;
        this.constructor = constructor;
    }
}
