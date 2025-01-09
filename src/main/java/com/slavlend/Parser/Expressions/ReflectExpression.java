package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrRefl;
import lombok.Getter;

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

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrRefl(address.convert(), className));
    }

    public ReflectExpression(String className) {
        this.className = className;
    }
}