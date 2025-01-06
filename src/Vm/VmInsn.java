package com.slavlend.Vm;

/*
Инстанс класса
 */
public class VmInsn {
    // представитель какого класса
    private VmClass clazz;
    private VmVal[] args;

    // конструктор
    public VmInsn(VmClass clazz, VmVal[] args) {
        this.clazz = clazz;
        this.args = args;
    }
}
