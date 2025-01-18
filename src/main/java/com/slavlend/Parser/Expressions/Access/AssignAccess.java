package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Operator;
import com.slavlend.PolarLogger;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.VmInstrArith;
import com.slavlend.Vm.Instructions.VmInstrLoad;
import com.slavlend.Vm.Instructions.VmInstrPush;
import com.slavlend.Vm.Instructions.VmInstrStore;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;

/*
Акссесс к функции
 */
@Getter
public class AssignAccess implements Access {
    // следующий
    private Access next;
    // имя функции
    private final String varName;
    // аддресс
    private final Address address;
    // экспрешен
    private final Expression to;
    // аккссес тип
    private final AccessType type;
    // весь акссесс (вся цепь)
    private final AccessExpression accessExpression;

    // конструктор
    public AssignAccess(AccessExpression accessExpression, Address address, Access next, String varName, Expression to, AccessType type) {
        this.accessExpression = accessExpression;
        this.address = address;
        this.next = next;
        this.varName = varName;
        this.to = to;
        this.type = type;
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
        VmVarContainer assignArgs = new VmVarContainer();
        Compiler.code.startWrite(assignArgs);
        to.compile();
        switch (type) {
            case SET -> {
                Compiler.code.endWrite();
                Compiler.code.visitInstr(new VmInstrStore(address.convert(), varName, hasPrevious, assignArgs));
            }
            case MUL -> {
                AccessExpression e = (AccessExpression) accessExpression.copy();
                e.set(new VarAccess(address, next, varName));
                e.compile();
                Compiler.code.visitInstr(new VmInstrArith(address.convert(), "*"));
                Compiler.code.endWrite();
                Compiler.code.visitInstr(new VmInstrStore(address.convert(), varName, hasPrevious, assignArgs));
            }
            case DIVIDE -> {
                AccessExpression e = (AccessExpression) accessExpression.copy();
                e.set(new VarAccess(address, next, varName));
                e.compile();
                Compiler.code.visitInstr(new VmInstrArith(address.convert(), "/"));
                Compiler.code.endWrite();
                Compiler.code.visitInstr(new VmInstrStore(address.convert(), varName, hasPrevious, assignArgs));
            }
            case PLUS -> {
                AccessExpression e = (AccessExpression) accessExpression.copy();
                e.set(new VarAccess(address, next, varName));
                e.compile();
                Compiler.code.visitInstr(new VmInstrArith(address.convert(), "+"));
                Compiler.code.endWrite();
                Compiler.code.visitInstr(new VmInstrStore(address.convert(), varName, hasPrevious, assignArgs));
            }
            case MINUS -> {
                AccessExpression e = (AccessExpression) accessExpression.copy();
                e.set(new VarAccess(address, next, varName));
                e.compile();
                Compiler.code.visitInstr(new VmInstrArith(address.convert(), "-"));
                Compiler.code.endWrite();
                Compiler.code.visitInstr(new VmInstrStore(address.convert(), varName, hasPrevious, assignArgs));
            }
        }
        if (hasNext()) {
            PolarLogger.exception("How You Want Use Something After Assign? 😂", address);
        }
    }

    @Override
    public Access copy() {
        if (next != null) {
            return new AssignAccess(accessExpression, address, next.copy(), varName, to, type);
        } else {
            return new AssignAccess(accessExpression, address, null, varName, to, type);
        }
    }
}
