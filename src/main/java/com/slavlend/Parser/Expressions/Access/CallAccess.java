package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.VmInstrCall;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Акссесс к функции
 */
@Getter
public class CallAccess implements Access {
    // следующий
    private Access next;
    // имя функции
    private final String funcName;
    // аддресс
    private final Address address;
    // параметры
    @Setter
    private ArrayList<Expression> params;

    // конструктор
    public CallAccess(Address address, Access next, String funcName, ArrayList<Expression> params) {
        this.address = address;
        this.next = next;
        this.funcName = funcName;
        this.params = params;
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
    public void compile(boolean hasPrevious) {
        VmVarContainer container = new VmVarContainer();
        Compiler.code.startWrite(container);
        for (Expression e : params) {
            e.compile();
        }
        Compiler.code.endWrite();
        Compiler.code.visitInstr(new VmInstrCall(address.convert(), funcName, container, hasPrevious));
        if (hasNext()) {
            getNext().compile(true);
        }
    }
}
