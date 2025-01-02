package com.slavlend.Polar;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.ClassStatement;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Parser.Statements.Statement;
import com.slavlend.Polar.Stack.Classes;

import java.util.ArrayList;
import java.util.HashMap;

/*
Класс языка Polar
 */
public class PolarClass {
    // функции
    public HashMap<String, FunctionStatement> statements = new HashMap<>();
    // модульные функции ( статические )
    public HashMap<String, FunctionStatement> module_statements = new HashMap<>();
    // модульные переменные ( статические )
    public HashMap<String, Expression> module_values = new HashMap<>();
    // конструктор
    public ArrayList<ArgumentExpression> constructor = new ArrayList<ArgumentExpression>();
    // полное имя
    public String fullName;
    // имя
    public String name;
    // адресс
    private final Address address;

    // конструктор
    public PolarClass(String fullName, String name, ArrayList<ArgumentExpression> constructor, Address address) {
        this.fullName = fullName;
        this.name = name;
        this.constructor = constructor;
        this.address = address;
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

    // геттер функций -> возвращает скопированные функции
    public HashMap<String, FunctionStatement> getFunctions() {
        HashMap<String, FunctionStatement> copy = new HashMap<String, FunctionStatement>();

        for (String key : statements.keySet()) {
            copy.put(key, (FunctionStatement) statements.get(key).copy());
        }

        return copy;
    }
}
