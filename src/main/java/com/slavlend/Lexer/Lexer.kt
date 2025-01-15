package com.slavlend.Lexer

import com.slavlend.PolarLogger
import com.slavlend.Parser.Address

/*
Лексер - переводит символы в лексемы (токены)
*/
class Lexer // конструктор
    (// код для парсинга в токены
    var input: String
) {
    // поток токендов
    var tokens: MutableList<Token> = ArrayList()

    // текущие переменные
    // для адрессации ( current - символ,
    // line - линия )
    var current: Int = 0
    var line: Int = 0

    // кейворды
    var keywords: Array<String> = arrayOf(
        "func",
        "back",
        "if",
        "true",
        "false",
        "while",
        "class",
        "new",
        "nil",
        "reflect",
        "elif",
        "else",
        "and",
        "use",
        "mod",
        "break",
        "is",
        "match",
        "case",
        "default",
        "for",
        "each",
        "assert",
        "next",
        "safe",
        "handle",
        "raise"
    )

    // типы токенов для кейвордов
    var keyword_tokens: HashMap<String?, TokenType?> = object : HashMap<String?, TokenType?>() {
        init {
            put("func", TokenType.FUNC)
            put("back", TokenType.BACK)
            put("if", TokenType.IF)
            put("true", TokenType.BOOL)
            put("false", TokenType.BOOL)
            put("while", TokenType.WHILE)
            put("class", TokenType.CLASS)
            put("new", TokenType.NEW)
            put("nil", TokenType.NIL)
            put("reflect", TokenType.REFLECT)
            put("elif", TokenType.ELIF)
            put("else", TokenType.ELSE)
            put("and", TokenType.AND)
            put("use", TokenType.USE)
            put("mod", TokenType.MOD)
            put("break", TokenType.BREAK)
            put("is", TokenType.IS)
            put("match", TokenType.MATCH)
            put("case", TokenType.CASE)
            put("default", TokenType.DEFAULT)
            put("for", TokenType.FOR)
            put("each", TokenType.EACH)
            put("assert", TokenType.ASSERT)
            put("next", TokenType.NEXT)
            put("safe", TokenType.SAFE)
            put("handle", TokenType.HANDLE)
            put("raise", TokenType.RAISE)
        }
    }

    // токенизация
    fun Tokenize() {
        // перебираем символы
        while (current < input.length) {
            //System.out.println("parsing... " + String.valueOf(current) + " | " + String.valueOf(Peek(0)));
            // текущий символ
            val cur = Peek(0)
            // space
            if (cur == '\t') {
                Next(1)
                continue
            } else if (cur == '\n') {
                line += 1
                Next(1)
                continue
            } else if (cur == '.') {
                tokens.add(
                    Token(
                        TokenType.DOT,
                        cur.toString(),
                        line + 1
                    )
                )

                Next(1)
                continue
            } else if (cur == '~') {
                tokens.add(
                    Token(
                        TokenType.RCALL,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == '?') {
                tokens.add(
                    Token(
                        TokenType.TERNARY,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == '#') {
                var i = 1

                while (input[current + i] != '#') {
                    i++
                }

                Next(i + 1)
                continue
            } else if (cur == '|' && Peek(1) == '>') {
                tokens.add(
                    Token(
                        TokenType.OPERATOR,
                        "|>",
                        line + 1
                    )
                )
                Next(2)
                continue
            } else if (cur == '*' || cur == '+' || cur == '-' || cur == '/' || cur == '%') {
                if (input[current + 1] != '=') {
                    if (cur == '-' && Peek(1).isDigit()) {
                        // отрицательное число
                        val builder = StringBuilder("-")

                        var i = 1
                        var isInt = false

                        while (Character.isDigit(Peek(i)) || Peek(i) == '.') {
                            if (!isInt && Peek(i) == '.') {
                                PolarLogger.exception("Cannot Parse Num With Two Dots.", Address(line + 1))
                            }
                            isInt = Peek(i) != '.'
                            builder.append(Peek(i))

                            if (current + i + 1 < input.length) {
                                i++
                            } else {
                                tokens.add(
                                    Token(
                                        TokenType.NUM,
                                        builder.toString(),
                                        line + 1
                                    )
                                )
                                Next(i)
                                return
                            }
                        }

                        tokens.add(
                            Token(
                                TokenType.NUM,
                                builder.toString(),
                                line + 1
                            )
                        )
                        Next(i)
                        continue
                    }
                    else {
                        // оператор
                        tokens.add(
                            Token(
                                TokenType.OPERATOR,
                                cur.toString(),
                                line + 1
                            )
                        )
                        Next(1)
                        continue
                    }
                }
                else {
                    // +=
                    if (cur == '+') {
                        tokens.add(
                            Token(
                                TokenType.ASSIGN_ADD,
                                (cur.code + input[current + 1].code).toString(),
                                line + 1
                            )
                        )

                        Next(2)
                        continue
                    } else if (cur == '-') {
                        tokens.add(
                            Token(
                                TokenType.ASSIGN_SUB,
                                (cur.code + input[current + 1].code).toString(),
                                line + 1
                            )
                        )

                        Next(2)
                        continue
                    } else if (cur == '*') {
                        tokens.add(
                            Token(
                                TokenType.ASSIGN_MUL,
                                (cur.code + input[current + 1].code).toString(),
                                line + 1
                            )
                        )

                        Next(2)
                        continue
                    } else if (cur == '/') {
                        tokens.add(
                            Token(
                                TokenType.ASSIGN_DIVIDE,
                                (cur.code + input[current + 1].code).toString(),
                                line + 1
                            )
                        )

                        Next(2)
                        continue
                    } else {
                        PolarLogger.exception("Invalid Operator % For Assigning Value", Address(line))
                    }
                }
            } else if (cur == ',') {
                tokens.add(
                    Token(
                        TokenType.COMMA,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == ':') {
                tokens.add(
                    Token(
                        TokenType.COLON,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == '{' ||
                cur == '}'
            ) {
                tokens.add(
                    Token(
                        TokenType.BRACE,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == '(' ||
                cur == ')'
            ) {
                tokens.add(
                    Token(
                        TokenType.BRACKET,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == '[' ||
                cur == ']'
            ) {
                tokens.add(
                    Token(
                        TokenType.Q_BRACKET,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (Character.isDigit(cur)) {
                val builder = StringBuilder()

                var i = 0
                var isInt = true

                while (Character.isDigit(Peek(i)) || Peek(i) == '.') {
                    if (!isInt && Peek(i) == '.') {
                        PolarLogger.exception("Cannot Parse Num With Two Dots.", Address(line + 1))
                    }
                    if (Peek(i) == '.') { isInt = false }
                    builder.append(Peek(i))

                    if (current + i + 1 < input.length) {
                        i++
                    } else {
                        break
                    }
                }

                tokens.add(
                    Token(
                        TokenType.NUM,
                        builder.toString(),
                        line + 1
                    )
                )
                Next(i)
                continue
            } else if (cur == '\'') {
                val builder = StringBuilder()

                var i = 1
                while (Peek(i) != '\'') {
                    if (Peek(i) != '\'') {
                        builder.append(Peek(i))
                        i++
                    }
                }

                tokens.add(
                    Token(
                        TokenType.TEXT,
                        builder.toString(),
                        line + 1
                    )
                )

                Next(i+1)
                continue
            } else if (cur == '@') {
                tokens.add(
                    Token(
                        TokenType.CALL,
                        cur.toString(),
                        line + 1
                    )
                )
                Next(1)
                continue
            } else if (cur == '!') {
                if (Peek(1) == '=') {
                    tokens.add(
                        Token(
                            TokenType.NOT_EQUAL,
                            cur.toString() + Peek(1),
                            line + 1
                        )
                    )
                    Next(2)
                    continue
                }
            } else if (cur == '>') {
                if (Peek(1) == '=') {
                    tokens.add(
                        Token(
                            TokenType.BIGGER_EQUAL,
                            cur.toString() + Peek(1),
                            line + 1
                        )
                    )
                    Next(2)
                    continue
                } else {
                    tokens.add(
                        Token(
                            TokenType.BIGGER,
                            cur.toString(),
                            line + 1
                        )
                    )
                    Next(1)
                    continue
                }
            } else if (cur == '<') {
                if (Peek(1) == '=') {
                    tokens.add(
                        Token(
                            TokenType.LOWER_EQUAL,
                            cur.toString() + Peek(1),
                            line + 1
                        )
                    )
                    Next(2)
                    continue
                } else {
                    tokens.add(
                        Token(
                            TokenType.LOWER,
                            cur.toString(),
                            line + 1
                        )
                    )
                    Next(1)
                    continue
                }
            } else if (cur == '=') {
                if (Peek(1) == '=') {
                    tokens.add(
                        Token(
                            TokenType.EQUAL,
                            cur.toString() + Peek(1),
                            line + 1
                        )
                    )
                    Next(2)
                    continue
                } else {
                    tokens.add(
                        Token(
                            TokenType.ASSIGN,
                            cur.toString(),
                            line + 1
                        )
                    )
                    Next(1)
                    continue
                }
            } else if (cur == ' ') {
                Next(1)
                continue
            } else {
                // id || keyword
                var j = 0
                val builder = StringBuilder()
                var isKeyword = false
                var foundedKeyword: TokenType? = null

                while ((current + j) < input.length && Peek(j) != '(' && Peek(j) != '{' && Peek(j) != ' ' && Peek(j) != '\n' && Peek(j) != '}' && Peek(
                        j
                    ) != ')' && Peek(j) != ',' && Peek(j) != '.' && Peek(j) != '=' && Peek(j) != ' ' && Peek(j) != '@' && Peek(
                        j
                    ) != '\t' && Peek(j) != ':' && Peek(j) != '[' && Peek(j) != ']' && Peek(j) != '+' && Peek(j) != '-' && Peek(
                        j) != '*' && Peek(j) != '/' && Peek(j) != '%'
                ) {
                    //System.out.println("%%% " + Peek(j));
                    builder.append(Peek(j))
                    j++
                }

                for (keyword in keywords) {
                    if (keyword.contentEquals(builder)) {
                        isKeyword = true
                        foundedKeyword = keyword_tokens[keyword]
                        break
                    }
                }

                if (isKeyword) {
                    tokens.add(
                        Token(
                            foundedKeyword,
                            builder.toString(),
                            line + 1
                        )
                    )
                } else {
                    tokens.add(
                        Token(
                            TokenType.ID,
                            builder.toString(),
                            line + 1
                        )
                    )
                }
                Next(builder.toString().length)
                continue
            }
        }

        //System.out.println("END");
    }

    // char peek
    fun Peek(offset: Int): Char {
        return input[current + offset]
    }

    // next overload
    fun Next(i: Int) {
        current += i
    }
}