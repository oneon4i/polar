package com.slavlend.Functions;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Parser.Address;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
Built-in функции
 */
public class BuiltInFunctions {
    // функции
    public static Map<String, BuiltInFunction> functionHashMap = Map.of(
            "put", new PutFunction(),
            "len", new LenFunction(),
            "scan", new ScanFunction(),
            "warning", new WarningFunction(),
            "panic", new PanicFunction(),
            "sleep", new SleepFunction(),
            "string", new StringFunction(),
            "num", new NumberFunction(),
            "number", new NumberFunction(),
            "bool", new BoolFunction()
    );

    // put
    public static class PutFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // Выводим текст
            System.out.println(args.get(0).asString());
            return null;
        }
        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // scan
    public static class ScanFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // Выводим текст
            System.out.println(args.get(0).asString());
            // сканнер
            Scanner scanner = new Scanner(System.in);
            return new PolarValue(scanner.nextLine());
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // len
    public static class LenFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            return new PolarValue((float) args.get(0).asString().length());
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // warning
    public static class WarningFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // действуем
            PolarLogger.Warning(args.get(0).asString(), address.line);
            // возвращаем
            return new PolarValue(null);
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // panic
    public static class PanicFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // действуем
            PolarLogger.Crash(args.get(0).asString(), address);
            // возвращаем
            return new PolarValue(null);
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // sleep
    public static class SleepFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // действуем
            try {
                Thread.sleep(args.get(0).asNumber().longValue());
            } catch (InterruptedException e) {
                PolarLogger.Crash("Error In Thread (Java): " + Thread.currentThread().toString() + ": " + e.getMessage(), address);
            }
            // возвращаем
            return new PolarValue(null);
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // string
    public static class StringFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // возвращаем
            return new PolarValue(String.valueOf(args.get(0).data));
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // number
    public static class NumberFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // возвращаем
            return new PolarValue(Float.parseFloat(args.get(0).asString()));
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }

    // bool
    public static class BoolFunction implements BuiltInFunction {

        @Override
        public PolarValue execute(Address address, List<PolarValue> args) {
            // возвращаем
            if (args.get(0).isBool()) {
                return new PolarValue(args.get(0).asBool());
            }
            if (args.get(0).isString()) {
                return new PolarValue(Boolean.parseBoolean(args.get(0).asString()));
            }
            if (args.get(0).isNumber()) {
                if (args.get(0).asNumber() == 1) {
                    return new PolarValue(true);
                }
                else {
                    return new PolarValue(false);
                }
            }

            // в другом случае выводим false
            return new PolarValue(false);
        }

        @Override
        public int argsAmount() {
            return 1;
        }
    }
}
