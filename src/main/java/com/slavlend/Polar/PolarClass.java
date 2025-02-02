package com.slavlend.Polar;

import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.Parser.Statements.ClassStatement;
import com.slavlend.Polar.Stack.Classes;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

/*
Класс языка Polar
 */
@Getter
public class PolarClass {
    // функции
    private final HashMap<String, PolarFunction> functions = new HashMap<>();
    // модульные функции ( статические )
    private final HashMap<String, PolarFunction> moduleFunctions = new HashMap<>();
    // модульные переменные ( статические )
    private final HashMap<String, PolarValue> moduleValues = new HashMap<>();
    // конструктор
    private final ArrayList<ArgumentExpression> constructor;
    // полное имя
    private final String fullName;
    // имя
    private final String name;
    // адресс
    private final Address address;
    // стейтмент - которым создан объект
    private final ClassStatement createdBy;

    // получение модульных переменных
    public HashMap<String, PolarValue> lookupModuleValues() {
        // если модульные переменные не были
        // загружены из выражений до -> загружаем
        if (getModuleValues().keySet().isEmpty()) {
            for (String key : createdBy.getModuleVariables().keySet()) {
                addModuleVariable(key, createdBy.getModuleVariables().get(key).evaluate());
            }
        }
        return moduleValues;
    }

    // конструктор
    public PolarClass(ClassStatement createdBy, String fullName, String name, ArrayList<ArgumentExpression> constructor, Address address) {
        this.createdBy = createdBy;
        this.fullName = fullName;
        this.name = name;
        this.constructor = constructor;
        this.address = address;
        Classes.getInstance().getClasses().add(this);
    }

    // эддеры функций
    public void add(PolarFunction statement) {
        functions.put(statement.getName(), statement);
    }
    public void addModule(PolarFunction statement) {
        moduleFunctions.put(statement.getName(), statement);
    }

    // эддер переменной
    public void addModuleVariable(String name, PolarValue expr) {
        moduleValues.put(name, expr);
    }

    // возвращает скопированные функции
    public HashMap<String, PolarFunction> copyFunctions() {
        HashMap<String, PolarFunction> copy = new HashMap<>();

        for (String key : functions.keySet()) {
            copy.put(key, functions.get(key).copy());
        }

        return copy;
    }
}
