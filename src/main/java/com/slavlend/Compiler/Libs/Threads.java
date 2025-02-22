package com.slavlend.Compiler.Libs;

import com.slavlend.Compiler.Compiler;
import com.slavlend.PolarLogger;
import com.slavlend.Vm.VmException;
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
                try {
                    fn.exec(Compiler.iceVm, false);
                } catch (VmException e) {
                    if (e.getValue() != null) {
                        PolarLogger.polarLogger.error(e.getAddr(), e.getMessage(), e.getValue());
                    } else {
                        PolarLogger.polarLogger.error(e.getAddr(), e.getMessage());
                    }
                }
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
            } catch (VmException e) {
                if (e.getValue() != null) {
                    PolarLogger.polarLogger.error(e.getAddr(), e.getMessage(), e.getValue());
                } else {
                    PolarLogger.polarLogger.error(e.getAddr(), e.getMessage());
                }
                return null;
            }
        }, Compiler.iceVm.getAsyncExecutor());
    }
}
