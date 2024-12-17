package com.slavlend.Parser;

/*
Адресс стэйтмента. Позиция и линия.
 */
public class Address {
    // номер символа
    public int ch;
    // линия
    public int line;

    // адресс и с линией и с нормером символа
    public Address(int line, int ch) {
        this.line = line;
        this.ch = ch;
    }

    // адресс только с линией
    public Address(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "(line: " + line + ", char: " + ch + ")";
    }
}
