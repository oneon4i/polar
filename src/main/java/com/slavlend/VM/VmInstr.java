package com.slavlend.VM;

/*
Инструкция
 */
public interface VmInstr {
    abstract void run(IceVm vm, VmFrame<Object> scope);
    abstract void print();
}