package com.slavlend.Executor;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Compiler.Functions.PolarFunctions;
import com.slavlend.Parser.Statements.BlockStatement;
import com.slavlend.App;
import com.slavlend.Colors;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Parser;
import com.slavlend.PolarLogger;
import com.slavlend.Vm.IceVm;

/*
Исполнение файла с кодом.
Экзекьютер.
 */
@SuppressWarnings("ExtractMethodRecommender")
public class Executor {
    // запуск polar-файла
    public static void exec(ExecutorSettings settings) {
        // статус - парсинг
        System.out.println(Colors.ANSI_GREEN + "🗺️ Parsing..." + Colors.ANSI_RESET);
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
        parser.setEnvironmentPath(envPath);
        parser.setFileName(filePath);
        App.parser = parser;
        // парсим код
        BlockStatement statement = parser.parse();
        // компилируем
        System.out.println(Colors.ANSI_BLUE + "🧊 Compiling..." + Colors.ANSI_RESET);
        statement.compile();
        // говорим что исполняем код
        System.out.println(Colors.ANSI_DARK_BLUE + "🥶 Compiled!" + Colors.ANSI_RESET);
        // устанавливаем логгер
        IceVm.setLogger(PolarLogger.polarLogger);
        // помещаем функции Полара
        PolarFunctions.provide();
        // выводим пустую строку
        System.out.println();
        // исполняем
        Compiler.iceVm.run(Compiler.code, settings.isDebugMode());
    }
}
