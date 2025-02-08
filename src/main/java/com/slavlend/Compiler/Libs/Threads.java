package com.slavlend.Compiler.Libs;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmFunction;
import com.slavlend.Vm.VmObj;

/*
Потоки
 */
public class Threads {
    public void start(VmFunction fn, Array args) {
        // создаем новый поток
        Thread thread = new Thread() {
            @Override
            public void run() {
                // инициализируем стек под поток
                Compiler.iceVm.initStackForThread();
                // помещаем аргументы в стек
                Compiler.iceVm.push(args);
                // вызов функции
                fn.exec(Compiler.iceVm, false);
            }
        };
        thread.start();
    }
}
