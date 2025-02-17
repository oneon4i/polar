package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.PolarLogger;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.TextExpression;
import com.slavlend.Parser.Parser;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Scanner;

/*
Стэйтмент - использование библиотеки. Подгружает ее.
 */
@Getter
public class UseLibStatement implements Statement{
    // имя библиотеки
    private final TextExpression libName;
    // адресс
    @Setter
    private Address address = App.parser.address();

    // конструктор
    public UseLibStatement(TextExpression libName) {
        // имя
        this.libName = libName;
    }

    @Override
    public Statement copy() {
        return new UseLibStatement(libName);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // файл
        StringBuilder code = new StringBuilder();
        Scanner sc = null;
        // инпут стрим
        InputStream is;
        if (Parser.libraries.containsKey(libName.getData())) {
            is = getClass().getResourceAsStream("/resources/" + Parser.libraries.get(libName.getData()));
        }
        else {
            File file = new File(App.parser.getEnvironmentPath() + "/" + libName.getData());
            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (is != null) {
            sc = new Scanner(is);
        }
        else {
            PolarLogger.exception("Cannot Load: " + libName.getData() + " (Not Found)", address);
        }

        // линии кода
        while (sc.hasNextLine()) {
            code.append(sc.nextLine()).append("\n");
        }
        // лексер
        Lexer tempLexer = new Lexer(code.toString());
        // импортируем классы
        Parser mainParser = App.parser;
        // временный парсер
        Parser tempParser = new Parser(tempLexer.scan());
        App.parser = tempParser;
        tempParser.setFileName(libName.getData().replace(".polar", ""));
        BlockStatement s = tempParser.parse();
        s.importAll();
        // устанавливаем парсер на главный
        App.parser = mainParser;
    }
}
