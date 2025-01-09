package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.*;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class MapContainerExpression implements Expression {
    // контейнер
    private final HashMap<Expression, Expression> container;
    // адресс
    private final Address address = App.parser.address();

    public MapContainerExpression(HashMap<Expression, Expression> container) {
        this.container = container;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrRefl(address.convert(),"com.slavlend.Compiler.Libs.Map"));
        for (Expression e : container.keySet()) {
            Compiler.code.visitInstr(new VmInstrDup(address.convert()));
            VmVarContainer _container = new VmVarContainer();
            Compiler.code.startWrite(_container);
            container.get(e).compile();
            e.compile();
            Compiler.code.endWrite();
            Compiler.code.visitInstr(new VmInstrCall(address.convert(), "set", _container, true));
        }
    }
}
