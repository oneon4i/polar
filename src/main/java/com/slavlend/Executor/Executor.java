package com.slavlend.Executor;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Compiler.Functions.PolarFunctions;
import com.slavlend.Lexer.Token;
import com.slavlend.Parser.Statements.BlockStatement;
import com.slavlend.App;
import com.slavlend.Colors;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Parser;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.PolarLogger;
import com.slavlend.System.PolarSystem;
import com.slavlend.Vm.IceVm;

import java.nio.file.Paths;

/*
Исполнение файла с кодом.
Экзекьютер.
 */
@SuppressWarnings({"DuplicateExpressions"})
public class Executor {
    // запуск polar-файла
    public static void exec(ExecutorSettings settings) {
        // статус - парсинг
        System.out.println(Colors.ANSI_LIME + "Analyzing code..." + Colors.ANSI_RESET);
        System.out.println("╭ Parsing...");
        // лексер
        Lexer lexer = new Lexer(settings.getCode());
        // токенизация
        lexer.Tokenize();
        // парсер
        Parser parser = new Parser(
                lexer.getTokens()
        );
        String envPath = "";
        String filePath = "";
        if (PolarSystem.isLinux()) {
            if (settings.getFilePath().lastIndexOf('/') != -1) {
                // полный путь
                envPath = settings.getFilePath().substring(0, settings.getFilePath().lastIndexOf('/'));
            } else {
                // Относительный путь
                envPath = Paths.get(".").toString();
                filePath = envPath + "/" + settings.getFilePath();
            }
        }
        else {
            if (settings.getFilePath().lastIndexOf('\\') != -1) {
                // Полный путь
                envPath = settings.getFilePath().substring(0, settings.getFilePath().lastIndexOf('\\'));
                filePath = settings.getFilePath().substring(
                        settings.getFilePath().lastIndexOf('\\')+1
                ).replace(".polar", "");
            } else {
                // Относительный путь
                envPath = Paths.get(".").toString();
                filePath = envPath + "\\" + settings.getFilePath();
            }
        }
        parser.setEnvironmentPath(envPath);
        parser.setFileName(filePath);
        App.parser = parser;
        // парсим код
        BlockStatement statement = parser.parse();
        // выводим сообщение
        System.out.println("╰⭢ " + Colors.ANSI_LIME + "Done!" + Colors.ANSI_RESET);
        // устанавливаем логгер
        IceVm.setLogger(PolarLogger.polarLogger);
        // компилируем
        System.out.println("╭ Compiling..." + Colors.ANSI_RESET);
        statement.compile();
        System.out.println("╰⭢ " + Colors.ANSI_LIME + "Done!" + Colors.ANSI_RESET);
        // помещаем функции Полара
        PolarFunctions.provide();
        // выводим пустую строку
        System.out.println();
        // исполняем
        Compiler.iceVm.run(Compiler.code, settings.isDebugMode());
    }
}
