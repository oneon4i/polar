package com.slavlend.Vm;

/*
Значение виртуальной машины
 */
public class VmVal {
    // внутренний объект
    private final Object obj;

    // конструктор
    public VmVal(Object object) {
        this.obj = object;
    }

    // как бул
    public Boolean bool() {
        return (Boolean) obj;
    }

    // как число
    public float number() {
        return (float) obj;
    }

    // как строка
    public String string() {
        return (String) obj;
    }

    // как инстанс класса
    public VmInsn object() {
        return (VmInsn) obj;
    }
}
