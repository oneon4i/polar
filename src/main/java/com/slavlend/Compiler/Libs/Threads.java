package com.slavlend.Compiler.Libs;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmFunction;

/*
Потоки
 */
public class Threads {
    public void start(VmFunction fn) {
        // создаем новый поток
        Thread thread = new Thread() {
            @Override
            public void run() {
                // инициализируем стек под поток
                Compiler.iceVm.initStackForThread();
                // вызов функции
                fn.copy().exec(Compiler.iceVm);
            }
        };
        thread.start();
    }
}
