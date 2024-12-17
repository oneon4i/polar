package com.slavlend.Lexer;

/*
Токены - на них делится текст.
 */
public class Token {
    // тип токена
    public TokenType type;
    // значение токена
    public String value;
    // линия адресса токена
    public int line = 0;

    // конструктор
    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }
}
