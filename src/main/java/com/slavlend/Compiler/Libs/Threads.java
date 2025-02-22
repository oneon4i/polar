package com.slavlend.Compiler.Libs;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmFunction;
import com.slavlend.Vm.VmThrowable;
import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

/*
Библиотека для потоков и асинхронности
 */
public class Threads {
    // многопоточка
    public Thread start(VmFunction fn, Array args) {
        // создаем новый поток
        Thread thread = new Thread() {
            @SneakyThrows
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
            try {
                return fn.execAsync(Compiler.iceVm);
            } catch (VmThrowable e) {
                return null;
            }
        }, Compiler.iceVm.getAsyncExecutor());
    }
}
