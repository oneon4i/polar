package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

/*
Нил экспрешенн - возвращает нулл
 */
public class NilExpression implements Expression {
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        return new PolarValue(null);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile(/*MethodVisitor visitor*/) {
        /*visitor.visitInsn(Opcodes.ACONST_NULL)*/;
    }

    public NilExpression() {
    }
}
