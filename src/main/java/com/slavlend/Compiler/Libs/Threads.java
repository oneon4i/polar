package com.slavlend.Compiler.Libs;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmFunction;
import com.slavlend.Vm.VmObj;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Потоки
 */
public class Threads {
    // многопоточка
    public Thread start(VmFunction fn, Array args) {
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
        return thread;
    }

    // асинхронность
    public CompletableFuture<Object> async(VmFunction fn, Array args) {
        return CompletableFuture.supplyAsync(() -> {
            // инициализируем стек под асинхронный поток
            Compiler.iceVm.initStackForThread();
            // помещаем аргументы в стек
            Compiler.iceVm.push(args);
            // запускаем
            return fn.execAsync(Compiler.iceVm);
        }, Compiler.iceVm.getAsyncExecutor());
    }
}
