package com.slavlend.Parser;

import lombok.Getter;

/*
Адресс стэйтмента. Позиция и линия.
 */
@Getter
public class Address {
    // линия
    private final int line;

    // адресс и с линией и с нормером символа
    public Address(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "(line: " + line + ")";
    }
}
