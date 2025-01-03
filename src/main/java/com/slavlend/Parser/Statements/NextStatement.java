package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import lombok.Getter;

/*
Некст стэйтмент - итерирует forStatement
 */
@Getter
public class NextStatement extends RuntimeException implements Statement {
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // кидаем
        throw this;
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new NextStatement();
    }

    @Override
    public Address address() {
        return address;
    }
}
