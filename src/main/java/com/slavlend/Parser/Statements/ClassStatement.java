package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Polar.PolarClass;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.VM.Instructions.VmInstrStoreM;
import com.slavlend.VM.VmClass;
import lombok.Getter;
import org.jetbrains.kotlin.wasm.ir.WasmHeapType;

import java.util.ArrayList;
import java.util.HashMap;

// стэтймент класса
@Getter
public class ClassStatement implements Statement {
    // класс
    private final PolarClass polarClass;
    // адресс
    private final Address address = App.parser.address();
    // выражения для модульных переменных
    private final HashMap<String, Expression> moduleVariables = new HashMap<>();

    @Override
    public void optimize() {
        // ...
    }

    // конструктор
    public ClassStatement(String fullName, String name, ArrayList<ArgumentExpression> constructor) {
        this.polarClass = new PolarClass(this, fullName, name, constructor, address);
        Classes.getInstance().getClasses().add(this.polarClass);
    }

    // эддеры функций
    public void add(FunctionStatement statement) {
        polarClass.add(statement);
    }
    public void addModule(FunctionStatement statement) {
        polarClass.addModule(statement);
    }

    // эддер переменной
    public void addModuleVariable(String name, Expression expr) {
        moduleVariables.put(name, expr);
    }

    @Override
    public void execute() {
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new ClassStatement(polarClass.getFullName(), polarClass.getName(), polarClass.getConstructor());
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // пишем класс
        VmClass vmClass = new VmClass(polarClass.getName(), polarClass.getConstructor());
        Compiler.code.defineClass(vmClass);
        Compiler.code.startWrite(vmClass);
        for (FunctionStatement fn : polarClass.getFunctions().values()) {
            fn.compile();
        }
        // пишем модульные функции
        vmClass.setModuleFunctionsWriting(true);
        for (FunctionStatement st : polarClass.getModuleFunctions().values()) {
            st.compile();
        }
        vmClass.setModuleFunctionsWriting(false);
        Compiler.code.endWrite();
        // пишем модульные переменные
        for (String name : moduleVariables.keySet()) {
            Expression e = moduleVariables.get(name);
            e.compile();
            Compiler.code.visitInstr(new VmInstrStoreM(vmClass, name));
        }
    }
}
