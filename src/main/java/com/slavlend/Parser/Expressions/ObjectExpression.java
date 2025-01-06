package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Parser.Address;
import com.slavlend.VM.Instructions.VmInstrPush;

import java.util.ArrayList;

/*
Обджект экспрешенн - возвращает экземпляр класса
 */
public class ObjectExpression implements Expression {
    // класс
    private final String className;
    // конструктор
    private final ArrayList<Expression> constructor;
    // адресс
    private Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        PolarObject obj = new PolarObject(Classes.getInstance().lookupClass(address, className), constructor);
        obj.init();
        return new PolarValue(obj);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrPush("not implemented"));
    }

    public ObjectExpression(String className, ArrayList<Expression> constructor) {
        this.className = className;
        this.constructor = constructor;
    }
}
