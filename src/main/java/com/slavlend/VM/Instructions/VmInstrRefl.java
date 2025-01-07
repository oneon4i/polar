package com.slavlend.VM.Instructions;

import com.slavlend.VM.*;
import lombok.Getter;

import java.util.HashMap;

/*
Создание рефлексийного объекта
 */
@SuppressWarnings("ClassCanBeRecord")
@Getter
public class VmInstrRefl implements VmInstr {
    // имя
    private final String name;

    // конструктор
    public VmInstrRefl(String name) {
        this.name = name;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        // ищем класс
        try {
            Object o = Class.forName(name).newInstance();
            vm.push(o);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "REFLECT[" + name  + "]";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
