package com.slavlend.Executor;

import com.slavlend.App;
import com.slavlend.Colors;
import com.slavlend.Exceptions.PolarException;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Parser;
import com.slavlend.Parser.Statements.BlockStatement;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarBench;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Polar.StackHistoryWriter;

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

        // парсинг
        Parser parser = new Parser(
                lexer.getTokens()
        );
        String envPath = settings.getFilePath().substring(0, settings.getFilePath().lastIndexOf('\\'));
        String filePath = settings.getFilePath().substring(
                settings.getFilePath().lastIndexOf('\\')+1
        ).replace(".polar", "");
        parser.setEnvironmentPath(envPath);
        parser.setFileName(filePath);
        App.parser = parser;
        BlockStatement statement = parser.parse();
        // вывод сообщения о готовности
        System.out.println(Colors.ANSI_GREEN + "🐲 Done!" + Colors.ANSI_RESET);

        // интерпретируем
        PolarBench bench = new PolarBench();
        bench.start();
        try {
            statement.execute();
        } catch (PolarException e) {
            PolarLogger.printError(e);
        }
        System.out.println(Colors.ANSI_BLUE + "🧊 Execution time: " + (float)bench.end()/1000f + "s" + Colors.ANSI_RESET); bench.end();
    }
}
