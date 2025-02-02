package com.slavlend.Lexer;

/*
Тип токена
 */
public enum TokenType {
    FUNC,
    OPERATOR, // +, -, *, /
    BRACKET, // (, )
    BRACE, // {, }
    CALL, // @
    EQUAL, // ==
    NOT_EQUAL, // !=
    TEXT, // 'text'
    NUM, // 1234567890.0123456789
    ASSIGN, // =
    ID, // variable id
    COMMA, // ,
    BACK, // back
    IF, // if
    BOOL, // bool
    WHILE, // while
    CLASS, // class
    NEW, // new
    DOT, // dot
    BIGGER, // >
    LOWER,  // <
    BIGGER_EQUAL, // >=
    LOWER_EQUAL, // <=
    NIL, // nil
    REFLECT, // reflect
    RCALL, // rcall
    ELIF, // elif
    ELSE, // else
    AND, // logical and
    OR, // logical or
    USE, // use
    ASSIGN_ADD, // assign add
    ASSIGN_SUB, // assign sub
    ASSIGN_MUL, // assign mul
    ASSIGN_DIVIDE,  // assign divide
    MOD, // mod
    IS, // is
    BREAK, // break
    MATCH, // match
    CASE, // case
    DEFAULT, // default
    Q_BRACKET, // [ & ]
    COLON, // colon :
    FOR, // for
    TERNARY, // ternary
    EACH, // each loop
    ASSERT, // assert
    NEXT, // next
    SAFE, // try
    HANDLE, // handle
    RAISE, // raise
    JUSE, // juse
    LAMBDA, // lambda
    GO, // ->
    REPEAT // repeat
}
