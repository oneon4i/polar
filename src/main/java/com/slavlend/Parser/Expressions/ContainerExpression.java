package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.*;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ContainerExpression implements Expression {
    // контейнер
    private final ArrayList<Expression> container;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrRefl(address.convert(), "com.slavlend.Compiler.Libs.Array"));
        for (Expression e : container) {
            Compiler.code.visitInstr(new VmInstrDup(address.convert()));
            VmVarContainer container = new VmVarContainer();
            Compiler.code.startWrite(container);
            e.compile();
            Compiler.code.endWrite();
            Compiler.code.visitInstr(new VmInstrCall(address.convert(), "add", container, true, false));
        }
    }


    public ContainerExpression(ArrayList<Expression> container) {
        this.container = container;
    }
}
