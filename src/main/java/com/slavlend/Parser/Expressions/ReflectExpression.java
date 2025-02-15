package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrRefl;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;

import java.util.List;

/*
Экспрешенн рефлексии -> создание
Java класса с использованием рефлексии
 */
@Getter
public class ReflectExpression implements Expression {
    // имя класса для создания объекта
    private final String className;
    // адресс
    private final Address address = App.parser.address();
    // конструктор
    private final List<Expression> constructor;

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
        Compiler.code.visitInstr(new VmInstrRefl(address.convert(), className, container));
    }

    public ReflectExpression(String className, List<Expression> constructor) {
        this.className = className;
        this.constructor = constructor;
    }
}