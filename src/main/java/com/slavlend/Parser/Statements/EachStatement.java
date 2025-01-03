package com.slavlend.Parser.Statements;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Parser.Expressions.ConditionExpression;
import lombok.Getter;

import java.util.ArrayList;

/*
Фор стэйтмент - цикл
 */
@SuppressWarnings("PointlessBooleanExpression")
@Getter
public class EachStatement implements Statement {
    // тело
    private final ArrayList<Statement> statements = new ArrayList<>();
    // кодишены
    private final ArrayList<ConditionExpression> conditions = new ArrayList<>();
    // адресс
    private final Address address = App.parser.address();
    // имя переменной
    private final String variableName;
    // имя переменной списка
    private final AccessExpression listVariable;

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
        int index = 0;
        if (arr.size() > index) { Storage.getInstance().put(variableName, arr.get(index)); }
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
            Storage.getInstance().del(variableName);
            if (index >= arr.size()) { return; }
            Storage.getInstance().put(variableName, arr.get(index));
        }

        // удаляем темповую переменную
        Storage.getInstance().del(variableName);
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
        EachStatement _copy = new EachStatement(listVariable, variableName);

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
        this.variableName = name;
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
