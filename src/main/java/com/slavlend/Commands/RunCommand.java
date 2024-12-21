package com.slavlend.Commands;

import com.slavlend.Colors;
import com.slavlend.Executor.Executor;
import com.slavlend.Executor.ExecutorSettings;

import java.io.File;
import java.util.Scanner;

// комманда запуска файла
public class RunCommand implements Command {
    @Override
    public void execute(String[] args) {
        // вводим файл нэйм для открытия
        // System.out.println("🪶 Enter File Name: ");
        // проверяем на колличество аргументов
        if (args.length != 1) {
            System.out.println(
                    "🛸 Error! Invalid args amount. (Expected:1,Founded:"
                            +String.valueOf(args.length) + ")"
            );
            return;
        }
        // получаем файл из аргументов
        String filePath = args[0];

        // читаем файл
        File file = null;
        StringBuilder code = null;
        Scanner sc = null;
        try {
            file = new File(filePath);
            code = new StringBuilder();
            sc = new Scanner(file);
        } catch (Exception e) {
            System.out.println("👽 Invalid file: " + filePath);
            return;
        }

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

        // выводим сообщение о завршении программы
        // System.out.println(Colors.ANSI_YELLOW + "💡 Program was finished!" + Colors.ANSI_RESET);
    }
}
