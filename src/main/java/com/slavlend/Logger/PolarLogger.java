package com.slavlend.Logger;

import com.slavlend.Polar.StackHistoryWriter;
import com.slavlend.Colors;
import com.slavlend.Parser.Address;

public class PolarLogger {
    /*
    Крашит выполнение, выводя сообщение
    об ошибке вместе с линией ошибки.
     */
    public static void Crash(String message, Address line) {
        // исключение
        System.out.println(Colors.ANSI_RED + "╭──────────────────────────╮");
        System.out.println("│ 📔 Line: " + line.line);
        System.out.println("│ 📕 Error: " + message);
        System.out.println("│ ");
        System.out.println("│ 📑 Stack Trace: ");
        StackHistoryWriter.getInstance().printStackTrace();
        System.out.println("│ ");
        System.out.println("│ 📃 Java stack: ");
        printCurrentStackTrace();
        System.out.println("╰──────────────────────────╯" + Colors.ANSI_RESET);
        // выход с кодом ошибки
        System.exit(1);
    }

    /*
    Выводит сообщение пользовательского варнинга
    вместе с линией варнинга, не останавливая(краша) выполнение программы.
     */
    public static void Warning(String message, int line) {
        // warning
        System.out.println(Colors.ANSI_YELLOW +"╭──────────────────────────╮");
        System.out.println("│ ⚠️ Warning at: " + line);
        System.out.println("│ ☁️ Message: " + message);
        System.out.println("╰──────────────────────────╯" + Colors.ANSI_RESET);
    }

    // вывод java стак-трейса этого потока.
    public static void printCurrentStackTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        for (StackTraceElement element : stackTraceElements) {
            System.out.println("| " + element);
        }
    }

    // получение пути к языку
    public static String getPolarPath() {
        // return System.getenv("POLAR_HOME");
        return "E:\\polar-lang\\language\\src\\main\\resources\\PolarDirectory\\pkgs";
    }
}
