package com.slavlend.Parser.Statements;

import com.slavlend.Parser.Address;

// интерфейс для любого стэйтмента
public interface Statement {
    abstract Address address();
    abstract void compile();
}
