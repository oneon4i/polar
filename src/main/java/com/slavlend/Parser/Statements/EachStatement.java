package com.slavlend.Parser.Statements;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Parser.Expressions.ConditionExpression;

import java.util.ArrayList;

/*
Фор стэйтмент - цикл
 */
public class EachStatement implements Statement {
    // тело
    public ArrayList<Statement> statements = new ArrayList<Statement>();
    // кодишены
    public ArrayList<ConditionExpression> conditions = new ArrayList<>();
    // адресс
    private Address address = App.parser.address();
    // имя переменной
    private String variable;
    // имя переменной списка
    private AccessExpression listVariable;
    // текущий индекс
    private int index;

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // создаём темповую переменную
        ArrayList<PolarValue> arr = listVariable.evaluate().asList();
        index = 0;
        if (arr.size() > index) { Storage.getInstance().put(variable, arr.get(index)); }
        else { return; }

        // кондишены
        while (conditions() == true) {
            // плюсуем индекс
            index++;
            // стэйтменты
            for (Statement statement : statements) {
                try {
                    statement.execute();
                } catch (BreakStatement breakStatement) {
                    return;
                } catch (NextStatement nextStatement) {
                    // continue
                    break;
                }
            }
            // удаляем значение и помещаем новое
            Storage.getInstance().del(variable);
            if (index >= arr.size()) { return; }
            Storage.getInstance().put(variable, arr.get(index));
        }

        // удаляем темповую переменную
        Storage.getInstance().del(variable);
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void interrupt() {

    }

    // копирование

    @Override
    public Statement copy() {
        EachStatement _copy = new EachStatement(listVariable, variable);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    // адресс
    @Override
    public Address address() {
        return address;
    }

    // конструктор
    public EachStatement(AccessExpression arr, String name) {
        this.listVariable = arr;
        this.variable = name;
    }

    // кондишены
    public boolean conditions() {
        for (ConditionExpression e : conditions) {
            PolarValue v = e.evaluate();
            if (!v.asBool()) {
                return false;
            }
        }

        return true;
    }
}
