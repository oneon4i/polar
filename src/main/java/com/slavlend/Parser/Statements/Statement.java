package com.slavlend.Parser.Statements;

import com.slavlend.Parser.Address;

// интерфейс для любого стэйтмента
public interface Statement {
    abstract void optimize();
    abstract void execute();
    abstract void interrupt();
    abstract Statement copy();
    abstract Address address();
    abstract void compile();
}
