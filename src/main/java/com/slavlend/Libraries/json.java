package com.slavlend.Libraries;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Lexer.Lexer;
import com.slavlend.Parser.Expressions.MapContainerExpression;
import com.slavlend.Parser.Parser;

import java.util.ArrayList;

/*
Библиотека для работы с json
 */
@SuppressWarnings("unused")
public class json {
    // конструктор
    public json() {

    }

    // чтение текста
    public PolarValue read(PolarValue text) {
        // tokenize json
        Lexer lexer = new Lexer(text.asString().replace('"', '\''));
        lexer.Tokenize();
        // parse map
        Parser parser = new Parser(lexer.getTokens());
        MapContainerExpression e = parser.parseMap();
        // map
        return e.evaluate();
    }

    // дамп текста -> (перевод в строку)
    public PolarValue dumps(PolarValue data) {
        // получаем как объект мапу
        PolarObject o = data.asObject();
        // возвращаем
        return o.getClassValues().get("to_string").asFunc().call(o, new ArrayList<>());
    }
}
