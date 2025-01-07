package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Address;
import com.slavlend.VM.Instructions.*;
import com.slavlend.VM.VmVarContainer;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ContainerExpression implements Expression {
    // контейнер
    private final ArrayList<Expression> container;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public PolarValue evaluate() {
        // возвращаем эррэй, если нет класса - кидаем ошибку
        if (Classes.getInstance().lookupClass("Array") != null) {
            //return new EggValue(new EggContainer(container));
            PolarObject array = new ObjectExpression("Array", new ArrayList<>()).evaluate().asObject();
            // добавляем все элементы
            for (Expression e : container) {
                // аргументы
                ArrayList<PolarValue> lst = new ArrayList<PolarValue>();
                lst.add(e.evaluate());
                // пушим фрейм в стек
                Storage.getInstance().push();
                // вызываем функцию
                array.getClassValues().get("add").asFunc().call(array, lst);
                // удаляет фрейм из стека
                Storage.getInstance().pop();
            }
            // возвращение
            return new PolarValue(array);
        }
        else {
            PolarLogger.exception("Cannot Find Array Class. Did You Forgot To Import 'lib.array'?", address);
            return new PolarValue(null);
        }
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrRefl("com.slavlend.Compiler.Libs.Array"));
        for (Expression e : container) {
            Compiler.code.visitInstr(new VmInstrDup());
            VmVarContainer container = new VmVarContainer();
            Compiler.code.startWrite(container);
            e.compile();
            Compiler.code.endWrite();
            Compiler.code.visitInstr(new VmInstrCall("add", container, true));
        }
    }


    public ContainerExpression(ArrayList<Expression> container) {
        this.container = container;
    }
}
