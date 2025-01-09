package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ConditionExpression;
import lombok.Getter;

/*
Стэйтмент для ассерта ( проверяет условие )
 */
@Getter
public class AssertStatement implements Statement {
    // экспрешен
    private final ConditionExpression expr;
    // аддресс
    private final Address address = App.parser.address();

    @Override
    public Statement copy() {
        return new AssertStatement(expr);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // not implemented
    }

    // конструктор
    public AssertStatement(ConditionExpression expr) {
        this.expr = expr;
    }
}
