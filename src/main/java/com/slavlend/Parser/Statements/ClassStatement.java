package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Parser.Expressions.Expression;

import java.util.ArrayList;
import java.util.HashMap;

// стэтймент класса
public class ClassStatement implements Statement {
    // функции
    public HashMap<String, FunctionStatement> statements = new HashMap<>();
    // модульные функции ( статические )
    public HashMap<String, FunctionStatement> module_statements = new HashMap<>();
    // модульные переменные ( статические )
    public HashMap<String, Expression> module_values = new HashMap<>();
    // конструктор
    public ArrayList<ArgumentExpression> constructor = new ArrayList<ArgumentExpression>();
    // имя
    public String name;
    // адресс
    private Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    // конструктор
    public ClassStatement(String name, ArrayList<ArgumentExpression> constructor) {
        this.name = name;
        this.constructor = constructor;
        Classes.getInstance().classes.add(this);
    }

    // эддеры функций
    public void add(FunctionStatement statement) {
        statements.put(statement.name, statement);
    }
    public void addModule(FunctionStatement statement) {
        module_statements.put(statement.name, statement);
    }

    // эддер переменной
    public void addModuleVariable(String name, Expression expr) {
        module_values.put(name, expr);
    }


    @Override
    public void execute() {

    }

    // геттер функций -> возвращает скопированные функции
    public HashMap<String, FunctionStatement> getFunctions() {
        HashMap<String, FunctionStatement> copy = new HashMap<String, FunctionStatement>();

        for (String key : statements.keySet()) {
            copy.put(key, (FunctionStatement) statements.get(key).copy());
        }

        return copy;
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new ClassStatement(name, constructor);
    }

    @Override
    public Address address() {
        return address;
    }
}
