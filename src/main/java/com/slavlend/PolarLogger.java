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
    public static void exception(String message, String value, Address line) {
        // исключение
        polarLogger.error(new VmInAddr(line.getLine()), message, value);
    }

    /*
    Крашит выполнение, выводя сообщение
    об ошибке вместе с линией ошибки.
     */
    public static void exception(String message, Address line) {
        // исключение
        polarLogger.error(new VmInAddr(line.getLine()), message);
    }

    @Override
    public void error(VmInAddr addr, String message, String value) {
        System.out.println(Colors.ANSI_RED + "╭ ☃️ err: " + message);
        System.out.println("│ at line: " + addr.getLine());
        System.out.println("│ thread: " + Thread.currentThread().getName());
        System.out.println("│ ");
        System.out.println("│ " + value + " <╮");
        System.out.println("╰─" + "─".repeat(value.length()) + "──╯" + Colors.ANSI_RESET);
        System.exit(1);
    }

    @Override
    public void error(VmInAddr addr, String message) {
        System.out.println(Colors.ANSI_RED + "╭ ☃️ err: " + message);
        System.out.println("│ at line: " + addr.getLine());
        System.out.println("│ thread: " + Thread.currentThread().getName());
        System.out.println("╰");
        System.exit(1);
    }

    @Override
    public void error(VmInAddr addr, String message, RuntimeException exception) {
        System.out.println(Colors.ANSI_RED + "╭ ☃️ err: " + message);
        System.out.println("│ at line: " + addr.getLine());
        System.out.println("│ thread: " + Thread.currentThread().getName());
        System.out.println("│ java stack trace: ");
        System.out.println("╰");
        System.exit(1);
    }
}
