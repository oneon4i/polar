package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Vm.Instructions.VmInstrStoreM;
import com.slavlend.Vm.VmClass;
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
    public Statement copy() {
        return new ClassStatement(getFullName(), getName(), getConstructor());
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
        // пишем модульные переменные
        for (String name : moduleVariables.keySet()) {
            // пре-инструкции
            Compiler.code.startWrite(Compiler.code.getPreInstructions());
            // запись переменной
            Expression e = moduleVariables.get(name);
            e.compile();
            Compiler.code.visitInstr(new VmInstrStoreM(address.convert(),vmClass, name));
            // выходим из записи пре-инструкций
            Compiler.code.endWrite();
        }
    }
}
