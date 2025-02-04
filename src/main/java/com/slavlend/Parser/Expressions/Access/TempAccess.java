package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ObjectExpression;
import lombok.Getter;

/*
Акссесс к функции
 */
@Getter
public class TempAccess implements Access {
    // следующий
    private Access next;
    // временный объект
    private final ObjectExpression objectExpr;
    // аддресс
    private final Address address;

    // конструктор
    public TempAccess(Address address, Access next, ObjectExpression _object) {
        this.address = address;
        this.next = next;
        this.objectExpr = _object;
    }

    @Override
    public void setNext(Access access) {
        if (next != null) {
            next.setNext(access);
        }
        else {
            next = access;
        }
    }

    @Override
    public void setLast(Access access) {
        if (next.hasNext()) {
            next.setLast(access);
        }
        else {
            next = access;
        }
    }

    @Override
    public boolean hasNext() { return next != null; }

    @Override
    public Access getNext() { return next; }

    @Override
    public void compile(boolean hasPrevious, boolean isStatement) {
        boolean shouldPushResult = hasNext() || !isStatement;
        if (shouldPushResult) {
            objectExpr.compile();
        }
        if (hasNext()) {
            getNext().compile(true, isStatement);
        }
    }

    @Override
    public Access copy() {
        if (next != null) {
            return new TempAccess(address, next.copy(), objectExpr);
        } else {
            return new TempAccess(address, null, objectExpr);
        }
    }
}
