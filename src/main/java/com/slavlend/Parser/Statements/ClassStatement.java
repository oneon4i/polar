package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.VmInstrDecorate;
import com.slavlend.Vm.Instructions.VmInstrEvalLazy;
import com.slavlend.Vm.VmClass;
import com.slavlend.Vm.VmLazy;
import com.slavlend.Vm.VmVarContainer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

// стэтймент класса
@Getter
public class ClassStatement implements Statement {
    // адресс
    @Setter
    private Address address = App.parser.address();
    // функции
    private final HashMap<String, FunctionStatement> functions = new HashMap<>();
    // модульные функции ( статические )
    private final HashMap<String, FunctionStatement> moduleFunctions = new HashMap<>();
    // выражения для модульных переменных
    private final HashMap<String, Expression> moduleVariables = new HashMap<>();
    // имя
    private final String name;
    private final String fullName;
    // конструктор
    private final ArrayList<String> constructor;

    // конструктор
    public ClassStatement(String fullName, String name, ArrayList<String> constructor) {
        this.fullName = fullName;
        this.name = name;
        this.constructor = constructor;
    }

    // эддеры функций
    public void add(FunctionStatement statement) {
        functions.put(statement.getName(), statement);
    }
    public void addModule(FunctionStatement statement) {
        moduleFunctions.put(statement.getName(), statement);
    }

    // эддер переменной
    public void addModuleVariable(String name, Expression expr) {
        moduleVariables.put(name, expr);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // пишем класс
        VmClass vmClass = new VmClass(getName(), getFullName(), getConstructor(), address.convert());
        Compiler.code.defineClass(address.convert(), vmClass);
        Compiler.code.startWrite(vmClass);
        for (FunctionStatement fn : getFunctions().values()) {
            fn.compile();
        }
        // пишем модульные функции
        vmClass.setModuleFunctionsWriting(true);
        for (FunctionStatement st : getModuleFunctions().values()) {
            st.compile();
        }
        vmClass.setModuleFunctionsWriting(false);
        Compiler.code.endWrite();
        // компилируем декораторы функций
        compileFunctionDecorators(vmClass);
        // пишем модульные переменные
        compileModValues(vmClass);
        // вычисляем lazy модульные значения после
        // блока объявления класса
        Compiler.code.visitInstr(new VmInstrEvalLazy(vmClass));
    }

    // компиляция декораторов функций
    private void compileFunctionDecorators(VmClass vmClass) {
        for (FunctionStatement fn : getFunctions().values()) {
            if (fn.getDecorator() != null) {
                VmVarContainer decoratorContainer = new VmVarContainer();
                Compiler.code.startWrite(decoratorContainer);
                fn.getDecorator().compile();
                Compiler.code.endWrite();
                Compiler.code.visitInstr(
                        new VmInstrDecorate(address.convert(), decoratorContainer, vmClass.getFunctions().lookup(address.convert(), fn.getName()))
                );
            }
        }
        // пишем декораторы для модульных функций
        for (FunctionStatement fn : getModuleFunctions().values()) {
            if (fn.getDecorator() != null) {
                VmVarContainer decoratorContainer = new VmVarContainer();
                Compiler.code.startWrite(decoratorContainer);
                fn.getDecorator().compile();
                Compiler.code.endWrite();
                Compiler.code.visitInstr(
                        new VmInstrDecorate(address.convert(), decoratorContainer, vmClass.getModFunctions().lookup(address.convert(), fn.getName()))
                );
            }
        }
    }

    // модульные значения
    private void compileModValues(VmClass vmClass) {
        // перебираем значение
        for (String name : moduleVariables.keySet()) {
            VmVarContainer lazyContainer = new VmVarContainer();
            Compiler.code.startWrite(lazyContainer);
            Expression e = moduleVariables.get(name);
            e.compile();
            Compiler.code.endWrite();
            vmClass.getModValues().set(name, new VmLazy(lazyContainer));
        }
    }
}
