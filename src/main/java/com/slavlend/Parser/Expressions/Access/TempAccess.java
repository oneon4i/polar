package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ObjectExpression;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarValue;
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

    // акссес
    @Override
    public PolarValue access(PolarValue previous) {
        // если нет предыдущего
        if (previous == null) {
            // получаем функцию
            PolarValue res = objectExpr.evaluate();

            // если нет следующего
            if (next == null) {
                return res;
            }
            else {
                return next.access(res);
            }
        }
        // если есть
        else {
            PolarLogger.exception("Cannot Use Temp Access Not First Of Call Chain", address);

            return new PolarValue(null);
        }
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
}
