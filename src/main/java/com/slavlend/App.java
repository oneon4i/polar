package com.slavlend;

import com.slavlend.Executor.Executor;
import com.slavlend.Executor.ExecutorSettings;
import com.slavlend.Parser.Parser;
import com.slavlend.Polar.Ver.PolarVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Главный файл
 */
public class App 
{
    // парсер
    public static Parser parser;

    /*
    Точка входа в приложение
     */
    public static void main(String[] args) throws Exception {
        // заголовочек
        System.out.println("╭───────────────────╮");
        System.out.println("│ 🐻‍❄️ Polar v" + PolarVersion.build.toString());
        System.out.println("╰───────────────────╯");
        System.out.println();
        // комманды
        if (args.length != 1) {
            System.out.println("🥶 Invalid usage. Valid: polar script.polar");
            return;
        }
        // читаем код из файла
        String code = readCode(args[0]);
        // исполняем
        Executor.exec(new ExecutorSettings(args[0], code));
    }

    // чтение кода
    public static String readCode(String fileName) {
        File file = new File(fileName);
        StringBuilder code = new StringBuilder();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // парсим на код лайны
        while (sc.hasNextLine()) {
            code.append(sc.nextLine()).append("\n");
        };

        return code.toString();
    }
}

