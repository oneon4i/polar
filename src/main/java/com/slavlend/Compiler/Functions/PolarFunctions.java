package com.slavlend.Compiler.Functions;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmCoreFunction;
import com.slavlend.Vm.VmException;
import com.slavlend.Vm.VmInAddr;

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
        public Object exec(VmInAddr addr) {
            System.out.println(Compiler.iceVm.pop(addr));
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
        public Object exec(VmInAddr addr) {
            System.out.print(Compiler.iceVm.pop(addr));
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
        public Object exec(VmInAddr addr) {
            try {
                String val = (String) Compiler.iceVm.pop(addr);
                if (!val.isEmpty()) {
                    System.out.println(val);
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new VmException(addr, e.getMessage());
                }
            } catch (RuntimeException e) {
                throw new VmException(addr, e.getMessage());
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
        public Object exec(VmInAddr addr) {
            try {
                return ((Integer)((String)Compiler.iceVm.pop(addr)).length()).floatValue();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Выжидание
    public static class SleepFn implements VmCoreFunction {
        @Override
        public Object exec(VmInAddr addr) {
            try {
                Thread.sleep(((Float)Compiler.iceVm.pop(addr)).longValue());
            } catch (InterruptedException e) {
                throw new VmException(addr, e.getMessage());
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
        public Object exec(VmInAddr addr) {
            try {
                return Float.parseFloat((String) Compiler.iceVm.pop(addr));
            } catch (RuntimeException e) {
                throw new VmException(addr, e.getMessage());
            }
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }


    // Превращение в bool
    public static class ToBoolFn implements VmCoreFunction {
        @Override
        public Object exec(VmInAddr addr) {
            try {
                return Boolean.parseBoolean((String)Compiler.iceVm.pop(addr));
            } catch (RuntimeException e) {
                throw new VmException(addr, e.getMessage());
            }
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // Превращение в строку
    public static class ToStringFn implements VmCoreFunction {
        @Override
        public Object exec(VmInAddr addr) {
            try {
                return String.valueOf(Compiler.iceVm.pop(addr));
            } catch (RuntimeException e) {
                throw new VmException(addr, e.getMessage());
            }
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
