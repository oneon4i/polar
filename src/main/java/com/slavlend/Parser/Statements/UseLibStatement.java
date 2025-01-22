package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.TextExpression;
import com.slavlend.Parser.Parser;
import lombok.Getter;

import java.io.*;
import java.util.Scanner;

/*
Юз Стэйтмент - использование библиотеки Полара. Подгружает ее.
 */
@Getter
public class UseLibStatement implements Statement{
    // имя библиотеки
    private final TextExpression libName;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    // конструктор
    public UseLibStatement(TextExpression libName) {
        // имя
        this.libName = libName;
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
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
        tempLexer.Tokenize();
        // импортируем классы
        Parser tempParser = new Parser(tempLexer.getTokens());
        tempParser.setFileName(libName.getData().replace(".polar", ""));
        tempParser.loadAsAdditionalFile();
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new UseLibStatement(libName);
    }

    @Override
    public Address address() {
        return address;
    }
}
