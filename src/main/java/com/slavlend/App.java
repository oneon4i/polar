package com.slavlend;

import com.slavlend.Executor.Executor;
import com.slavlend.Executor.ExecutorSettings;
import com.slavlend.Parser.Parser;

import java.io.File;
import java.util.Scanner;

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
        System.out.println("│ 🐻‍❄️ Polar v1.0.27     ");
        System.out.println("╰───────────────────╯");
        System.out.println();

        // вводим файл нэйм для открытия
        System.out.println("🪶 Enter File Name: ");

        // парсим через сканнер имя файла
        String filePath = new Scanner(System.in).nextLine();
        File file = new File(filePath);
        StringBuilder code = new StringBuilder();
        Scanner sc = new Scanner(file);

        // пустая строка
        System.out.println();

        // парсим на код лайны
        while (sc.hasNextLine()) {
            code.append(sc.nextLine()).append("\n");
        }

        // экзекьютим
        Executor.exec(new ExecutorSettings(
                filePath,
                code.toString()
        ));

        // пустая строка
        System.out.println();

        // интерпретируем
        parser.execute();
    }
}

