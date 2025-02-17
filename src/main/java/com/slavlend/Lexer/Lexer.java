package com.slavlend.Lexer;

import com.slavlend.Parser.Address;
import com.slavlend.PolarLogger;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Новый лексер
 */
@Getter
public class Lexer {
    // токены
    // токен
    private final List<Token> tokens = new ArrayList<>();
    // код
    private final String code;
    // текущие значения
    private int line = 1;
    private int current = 0;
    // кейворды
    private static final Map<String, TokenType> keywords = new HashMap<>() {{
        put("func", TokenType.FUNC);
        put("return", TokenType.RETURN);
        put("if", TokenType.IF);
        put("true", TokenType.BOOL);
        put("false", TokenType.BOOL);
        put("while", TokenType.WHILE);
        put("class", TokenType.CLASS);
        put("new", TokenType.NEW);
        put("nil", TokenType.NIL);
        put("reflect", TokenType.REFLECT);
        put("elif", TokenType.ELIF);
        put("else", TokenType.ELSE);
        put("and", TokenType.AND);
        put("or", TokenType.OR);
        put("use", TokenType.USE);
        put("mod", TokenType.MOD);
        put("break", TokenType.BREAK);
        put("is", TokenType.IS);
        put("match", TokenType.MATCH);
        put("case", TokenType.CASE);
        put("default", TokenType.DEFAULT);
        put("for", TokenType.FOR);
        put("each", TokenType.EACH);
        put("assert", TokenType.ASSERT);
        put("next", TokenType.NEXT);
        put("safe", TokenType.SAFE);
        put("handle", TokenType.HANDLE);
        put("raise", TokenType.RAISE);
        put("juse", TokenType.JUSE);
        put("lambda", TokenType.LAMBDA);
        put("repeat", TokenType.REPEAT);
    }};

    // сканнер
    public Lexer(String code) {
        this.code = code;
    }

    // скан кода
    public List<Token> scan() {
        while (!isAtEnd()) {
            char current = advance();
            switch (current) {
                case '?': addToken(TokenType.TERNARY, "?"); break;
                case '+': {
                    if (match('=')) {
                        addToken(TokenType.ASSIGN_ADD, "+=");
                        break;
                    } else {
                        addToken(TokenType.OPERATOR, "+");
                        break;
                    }
                }
                case '-': {
                    if (match('=')) {
                        addToken(TokenType.ASSIGN_SUB, "-=");
                        break;
                    } else if (match('>')) {
                        addToken(TokenType.GO, "->");
                        break;
                    } else if (Character.isDigit(peek())) {
                        tokens.add(scanNumber('-'));
                        break;
                    } else {
                        addToken(TokenType.OPERATOR, "-");
                        break;
                    }
                }
                case '*': {
                    if (match('=')) {
                        addToken(TokenType.ASSIGN_MUL, "*=");
                        break;
                    } else {
                        addToken(TokenType.OPERATOR, "*");
                        break;
                    }
                }
                case '#': {
                    while (!isAtEnd() && !match('#')) advance();
                    break;
                }
                case '/': {
                    if (match('=')) {
                        addToken(TokenType.ASSIGN_DIVIDE, "/=");
                        break;
                    } else {
                        addToken(TokenType.OPERATOR, "/");
                        break;
                    }
                }
                case '(': addToken(TokenType.BRACKET, "("); break;
                case ')': addToken(TokenType.BRACKET, ")"); break;
                case '[': addToken(TokenType.Q_BRACKET, "["); break;
                case ']': addToken(TokenType.Q_BRACKET, "]"); break;
                case '{': addToken(TokenType.BRACE, "{"); break;
                case '}': addToken(TokenType.BRACE, "}"); break;
                case ',': addToken(TokenType.COMMA, ","); break;
                case '.': addToken(TokenType.DOT, "."); break;
                case '@': addToken(TokenType.DECORATOR, "@"); break;
                case ':': addToken(TokenType.COLON, ":"); break;
                case '<': {
                    if (match('=')) {
                        addToken(TokenType.LOWER_EQUAL, "<=");
                    } else {
                        addToken(TokenType.LOWER, "<");
                    }
                    break;
                }
                case '>': {
                    if (match('=')) {
                        addToken(TokenType.BIGGER_EQUAL, ">=");
                    } else {
                        addToken(TokenType.BIGGER, ">");
                    }
                    break;
                }
                case '!': {
                    if (match('=')) {
                        addToken(TokenType.NOT_EQUAL, "!=");
                    } else {
                        PolarLogger.exception("Only != operator available.", new Address(line));
                    }
                    break;
                }
                case '=': {
                    if (match('=')) {
                        addToken(TokenType.EQUAL, "==");
                    } else {
                        addToken(TokenType.ASSIGN, "=");
                    }
                    break;
                }
                // игнорируем пробелы, переносы, на новые строки переход и тд
                case ' ': {
                    break;
                }
                case '\r': {
                    break;
                }
                case '\t': {
                    break;
                }
                case '\n': {
                    line++;
                    break;
                }
                case '\'': {
                    addToken(TokenType.TEXT, scanString());
                    break;
                }
                default: {
                    if (Character.isDigit(current)) {
                        tokens.add(scanNumber(current));
                    } else if (Character.isLetter(current)) {
                        tokens.add(scanIdentifierOrKeyword(current));
                    } else {
                        PolarLogger.exception("Unexpected character: ", new Address(line));
                    }
                }
            }
        }

        // возвращаем токены
        return tokens;
    }

    // сканируем строку
    private String scanString() {
        StringBuilder text = new StringBuilder();
        while (peek() != '\'') {
            if (match('\n')) {
                line += 1;
                continue;
            }
            if (isAtEnd()) {
                PolarLogger.exception("Unclosed string quotes.", new Address(line));
            }
            text.append(advance());
        }
        if (isAtEnd()) {
            PolarLogger.exception("Unclosed string quotes.", new Address(line));
        }
        advance();
        return text.toString();
    }

    // сканируем число
    private Token scanNumber(char start) {
        StringBuilder text = new StringBuilder(String.valueOf(start));
        while (Character.isDigit(peek()) || peek() == '.') {
            if (match('\n')) {
                line += 1;
                continue;
            }
            text.append(advance());
            if (isAtEnd()) {
                break;
            }
        }
        return new Token(TokenType.NUM, text.toString(), line);
    }

    // сканируем идентификатор или ключевое слово
    private Token scanIdentifierOrKeyword(char start) {
        StringBuilder text = new StringBuilder(String.valueOf(start));
        while (Character.isLetter(peek()) || Character.isDigit(peek()) || peek() == '_' || peek() == '-') {
            if (match('\n')) {
                line += 1;
                continue;
            }
            text.append(advance());
            if (isAtEnd()) {
                break;
            }
        }
        TokenType type = keywords.getOrDefault(text.toString(), TokenType.ID);
        return new Token(type, text.toString(), line);
    }

    // в конце ли
    private boolean isAtEnd() {
        return current >= code.length();
    }

    // следующий символ
    private char advance() {
        return code.charAt(current++);
    }

    // символ на текущей позиции без добавления
    // еденицы к текущему символу
    private char peek() {
        if (isAtEnd()) return '\1';
        return code.charAt(current);
    }

    // добавление токена
    private void addToken(TokenType type, String value) {
        tokens.add(new Token(type, value, line));
    }

    // проверка на текущий
    private boolean match(char character) {
        if (isAtEnd()) return false;
        if (code.charAt(current) == character) {
            current += 1;
            return true;
        } else {
            return false;
        }
    }
}
