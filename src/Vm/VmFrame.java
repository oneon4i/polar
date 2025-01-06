package com.slavlend.Vm;

/*
Фрейм виртуальной машины
 */

import java.util.Map;

public class VmFrame {
    private Map<String, VmVal> vars;

    public void put(String name, VmVal var) {
        vars.put(name, var);
    }

    public VmVal get(String name) {
        return vars.get(name);
    }

    public Boolean has(String name) {
        return vars.containsKey(name);
    }
}
