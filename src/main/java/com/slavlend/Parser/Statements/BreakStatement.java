package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;

/*
Брэйк стэйтмент - когда whil'у возвращается это значение из
другого whil'а, if'а или просто встречает этот стейтмент. Цикл
заканчивается
 */
public class BreakStatement extends RuntimeException implements Statement {
    // адресс
    private Address address = App.parser.address();

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
        return new BreakStatement();
    }

    @Override
    public Address address() {
        return address;
    }
}
