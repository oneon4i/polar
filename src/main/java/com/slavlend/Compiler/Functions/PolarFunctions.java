package com.slavlend.Compiler.Functions;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmCoreFunction;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Функции Полара
 */
public class PolarFunctions {
    // Вывод строки на следующую линию
    public static class PutFn implements VmCoreFunction {
        @Override
        public Object exec() {
            System.out.println(Compiler.iceVm.pop());
            return null;
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Вывод строки
    public static class PutsFn implements VmCoreFunction {
        @Override
        public Object exec() {
            System.out.print(Compiler.iceVm.pop());
            return null;
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Ввод пользователя
    public static class ScanFn implements VmCoreFunction {
        @Override
        public Object exec() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Длина строки
    public static class LenFn implements VmCoreFunction {
        @Override
        public Object exec() {
            return ((Integer)((String)Compiler.iceVm.pop()).length()).floatValue();
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Выжидание
    public static class SleepFn implements VmCoreFunction {
        @Override
        public Object exec() {
            try {
                Thread.sleep(((Float)Compiler.iceVm.pop()).longValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Превращение во float
    public static class ToFloatFn implements VmCoreFunction {
        @Override
        public Object exec() {
            return Float.parseFloat((String)Compiler.iceVm.pop());
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }


    // Превращение в bool
    public static class ToBoolFn implements VmCoreFunction {
        @Override
        public Object exec() {
            return Boolean.parseBoolean((String)Compiler.iceVm.pop());
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Превращение в строку
    public static class ToStringFn implements VmCoreFunction {
        @Override
        public Object exec() {
            return String.valueOf(Compiler.iceVm.pop());
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Объявление
    public static void provide() {
        Compiler.iceVm.getCoreFunctions().set("put", new PutFn());
        Compiler.iceVm.getCoreFunctions().set("puts", new PutsFn());
        Compiler.iceVm.getCoreFunctions().set("len", new LenFn());
        Compiler.iceVm.getCoreFunctions().set("scan", new ScanFn());
        Compiler.iceVm.getCoreFunctions().set("sleep", new SleepFn());
        Compiler.iceVm.getCoreFunctions().set("bool", new ToBoolFn());
        Compiler.iceVm.getCoreFunctions().set("number", new ToFloatFn());
        Compiler.iceVm.getCoreFunctions().set("string", new ToStringFn());
    }
}
