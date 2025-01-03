package com.slavlend.Executor;

import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Polar.StackHistoryWriter;
import com.slavlend.App;
import com.slavlend.Colors;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Parser;

/*
Исполнение файла с кодом.
Экзекьютер.
 */
public class Executor {
    // запуск polar-файла
    public static void exec(ExecutorSettings settings) {
        // инициализируем cтэк
        new Classes();
        new Storage();
        new StackHistoryWriter();
        Storage.getInstance().threadInit();

        // статус - парсинг
        System.out.println("🗺️ Parsing...");

        // лексер
        Lexer lexer = new Lexer(settings.getCode());
        // токенизация
        lexer.Tokenize();

        // парсер
        Parser parser = new Parser(
                lexer.getTokens()
        );
        String envPath = settings.getFilePath().substring(0, settings.getFilePath().lastIndexOf('\\'));
        String filePath = settings.getFilePath().substring(
                settings.getFilePath().lastIndexOf('\\')+1
        ).replace(".polar", "");
        parser.setEnv(envPath);
        parser.setFile(filePath);
        App.parser = parser;

        // интерпретация
        System.out.println(Colors.ANSI_GREEN + "🐲 Done!" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_CYAN + "❄️ Interpreting..." + Colors.ANSI_RESET);

        // интерпретируем
        parser.execute();
    }
}
