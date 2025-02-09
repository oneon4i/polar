package com.slavlend.Parser.Expressions.Access;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.Statement;
import lombok.Getter;
import lombok.Setter;

/*
Экспрешен для акссеса к переменной
 */
@Getter
public class AccessExpression implements Expression, Statement {
    // аксес
    private Access access;
    // адресс
    @Setter
    private Address address = App.parser.address();
    // стэйтмент ли
    @Setter
    private boolean isStatement;

    // конструктор
    public AccessExpression(Address address, Access access, boolean isStatement) {
        this.address = address;
        this.access = access;
        this.isStatement = isStatement;
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
    public Statement copy() {
        return new AccessExpression(address, access.copy(), isStatement);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        access.compile(false, isStatement);
    }
}
