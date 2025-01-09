package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrNewObj;
import com.slavlend.Vm.VmVarContainer;

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
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmVarContainer container = new VmVarContainer();
        Compiler.code.startWrite(container);
        for (Expression e : constructor) {
            e.compile();
        }
        Compiler.code.endWrite();
        Compiler.code.visitInstr(new VmInstrNewObj(address.convert(), className, container));
    }

    public ObjectExpression(String className, ArrayList<Expression> constructor) {
        this.className = className;
        this.constructor = constructor;
    }
}
