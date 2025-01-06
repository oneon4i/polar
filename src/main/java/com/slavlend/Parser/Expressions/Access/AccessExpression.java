package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;
import lombok.Getter;

/*
Экспрешен для акссеса к переменной
 */
@SuppressWarnings("ThrowableNotThrown")
@Getter
public class AccessExpression implements Expression, Statement {
    // аксес
    private Access access;
    // аддресс
    private final Address address;

    // конструктор
    public AccessExpression(Address address, Access access) {
        this.address = address;
        this.access = access;
    }

    // добавление
    public void add(Access _access) {
        // устанавливаем на следующий
        if (access != null) {
            access.setNext(_access);
        }
        else {
            access = _access;
        }
    }

    // установка
    public void set(Access _access) {
        // устанавливаем на следующий
        if (access.hasNext()) {
            access.setLast(_access);
        }
        else {
            access = _access;
        }
    }

    // получаем последний акссес
    public Access getLast() {
        Access _access = access;

        while (_access.hasNext()) {
            _access = _access.getNext();
        }

        return _access;
    }

    @Override
    public PolarValue evaluate() {
        return access.access(null);
    }

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        access.access(null);
    }

    @Override
    public void interrupt() {
        // ...
    }

    @Override
    public Statement copy() {
        return new AccessExpression(address, access);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        access.compile(false);
    }
}
