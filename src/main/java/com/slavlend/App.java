package com.slavlend;

import com.slavlend.Executor.Executor;
import com.slavlend.Executor.ExecutorSettings;
import com.slavlend.Parser.Parser;
import com.slavlend.Ver.PolarVersion;

import java.io.File;
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
    public static void main(String[] args) {
        // заголовочек
        System.out.println("╭───────────────────╮");
        System.out.println("│ 🐻‍❄️ Polar v" + PolarVersion.build);
        System.out.println("╰───────────────────╯");
        System.out.println();
        String currentDir = System.getProperty("user.dir");
        System.out.println("❄️ Current dir: " + currentDir);
        System.out.println();
        // проверяем на наличие аргумента
        if (args.length == 0) {
            System.out.println("🦩 Arguments is empty");
        }
        else {
            // загружаем файлы
            File file;
            StringBuilder code;
            Scanner sc;
            try {
                file = new File(args[0]);
                code = new StringBuilder();
                sc = new Scanner(file);
            } catch (Exception e) {
                System.out.println("👽 Invalid file: " + args[0]);
                return;
            }

            // парсим на код линии
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                code.append(line).append("\n");
            }
            sc.close();

            // нужно ли вывести инструкции и тд
            boolean debugMode = false;
            if (args.length > 1) {
                if (args[1].equals("--debug")) {
                    debugMode = true;
                }
            }

            // исполняем код на VM
            Executor.exec(
                    new ExecutorSettings(args[0], code.toString(), debugMode)
            );
        }
    }
}

