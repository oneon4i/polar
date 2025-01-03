package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.PolarClass;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Parser.Expressions.Expression;
import lombok.Getter;

import java.util.ArrayList;

// стэтймент класса
@Getter
public class ClassStatement implements Statement {
    // класс
    private final PolarClass polarClass;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    // конструктор
    public ClassStatement(String fullName, String name, ArrayList<ArgumentExpression> constructor) {
        this.polarClass = new PolarClass(fullName, name, constructor, address);
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
        polarClass.addModuleVariable(name, expr);
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
}
