package com.slavlend.Vm.Instructions;

import com.slavlend.Vm.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
Инструкция для ошибки для VM
 */
@Getter
public class VmInstrErr implements VmInstr {
    // адресс
    private final VmInAddr addr;
    // эксцепшен
    private VmException e;

    // пишем ли мы кондишены
    @Setter
    private boolean writingConditions;

    // конструктор
    public VmInstrErr(VmInAddr addr, VmException e) {
        this.addr = addr; this.e = e;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        throw e;
    }

    @Override
    public void print() {
        System.out.println("ERR(" + e.getMessage() + ")");
    }

    @Override
    public String toString() {
        return "ERR(" + e.getMessage() + ")";
    }
}
