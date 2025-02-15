package com.slavlend;

import com.slavlend.Parser.Address;
import com.slavlend.Vm.VmErrLogger;
import com.slavlend.Vm.VmInAddr;

/*
Логгер ошибок
 */
public class PolarLogger implements VmErrLogger {
    /*
    Инстанс
     */
    public static PolarLogger polarLogger = new PolarLogger();


    /*
    Крашит выполнение, выводя сообщение
    об ошибке вместе с линией ошибки.
     */
    public static void exception(String message, Address line) {
        // исключение
        polarLogger.error(new VmInAddr(line.getLine()), message);
    }

    @Override
    public void error(VmInAddr addr, String message) {
        System.out.println(Colors.ANSI_RED + "╭───────────error──────────╮");
        System.out.println("│ 📔 Line: " + addr.getLine());
        System.out.println("│ 📕 Error: " + message);
        System.out.println("│ 📗 Thread: " + Thread.currentThread().getName());
        System.out.println("│ ☃️ Stack trace of Java: ");
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println("| " + element);
        }
        System.out.println("╰──────────────────────────╯" + Colors.ANSI_RESET);
        /*
        for (StackTraceElement elem : Thread.currentThread().getStackTrace()) {
            System.out.println(elem);
        }

         */
        System.exit(1);
    }

    @Override
    public void error(VmInAddr addr, String message, RuntimeException exception) {
        System.out.println(Colors.ANSI_RED + "╭───────────error──────────╮");
        System.out.println("│ 📔 Line: " + addr.getLine());
        System.out.println("│ 📕 Error: " + message);
        System.out.println("│ 📗 Thread: " + Thread.currentThread().getName());
        System.out.println("│ ☃️ Stack trace of Java: ");
        for (StackTraceElement element : exception.getStackTrace()) {
            System.out.println("| " + element);
        }
        System.out.println("╰──────────────────────────╯" + Colors.ANSI_RESET);
        /*
        for (StackTraceElement elem : Thread.currentThread().getStackTrace()) {
            System.out.println(elem);
        }

         */
        System.exit(1);
    }
}
