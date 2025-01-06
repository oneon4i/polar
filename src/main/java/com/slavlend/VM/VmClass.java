package com.slavlend.VM;

import lombok.Getter;

import java.util.List;

/*
Класс
 */
@Getter
public class VmClass {
    // имя класса
    private final String name;
    // функции
    private final VmFrame<VmFunction> functions = new VmFrame<>();

    public VmClass(String name) {
        this.name = name;
    }
}
