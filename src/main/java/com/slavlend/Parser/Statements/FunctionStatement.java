package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Expressions.NilExpression;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.ArgumentExpression;
import com.slavlend.VM.VmFunction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/*
Стэйтмент функции - для дефенишена функции
 */
@Getter
public class FunctionStatement implements Statement, Callable {
    // тело функции
    private final ArrayList<Statement> statements = new ArrayList<>();
    // аргументы
    private final ArrayList<ArgumentExpression> arguments;
    // имя
    private final String name;
    // дефайнуто для
    @Setter
    private PolarObject definedFor;

    @Override
    public void optimize() {
        // оптимизируем стэйтменты
        /*
        ArrayList<Statement> newStatements = new ArrayList<Statement>();

        for (Statement statement : statements) {
            newStatements.add(Optimizations.);
        }
         */
    }

    // адресс
    private final Address address = App.parser.address();
    // конструктор
    public FunctionStatement(String name, ArrayList<ArgumentExpression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    // помещение в глоабальный скоуп
    public void putToFunctions() {
        Storage.getInstance().put(name, new PolarValue(this));
    }

    // добавление в бади
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void execute() {

    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        FunctionStatement _copy = new FunctionStatement(name, arguments);

        for (Statement statement : statements) {
            _copy.add(statement.copy());
        }

        return _copy;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        VmFunction f = new VmFunction(name, arguments);
        Compiler.code.defineFunction(f);
        Compiler.code.startWrite(f);
        for (Statement s : statements) {
            s.compile();
        }
        new BackStatement(new NilExpression()).compile();
        Compiler.code.endWrite();
    }

    // вызов функции
    @Override
    public PolarValue call(PolarObject calledFor, ArrayList<PolarValue> params) {
        // оптимизурем
        optimize();

        // дефайн переменной this
        if (calledFor != null) { Storage.getInstance().last().put("this", new PolarValue(calledFor)); }
        else if (definedFor != null) { Storage.getInstance().last().put("this", new PolarValue(definedFor)); }

        // дефайним переданные параметры
        for (int i = 0; i < arguments.size(); i++) {
            Storage.getInstance().put(arguments.get(i).evaluate().asString(), params.get(i));
        }

        // экзекьютим стэйтменты
        for (Statement statement : statements) {
            // бэк -> возвращает значение
            try {
                statement.execute();
            } catch (PolarValue v) {
                return v;
            }
        }

        return null;
    }
}
