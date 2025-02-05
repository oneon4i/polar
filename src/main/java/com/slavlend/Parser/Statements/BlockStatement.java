package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import lombok.Getter;

import java.util.ArrayList;

/*
 блок стэйтмент. Главный блок.
 */
@Getter
public class BlockStatement implements Statement {
    // стэйтменты
    private final ArrayList<Statement> statements = new ArrayList<>();
    // адресс
    private final Address address = App.parser.address();

    // добавка стейтмента в блок
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public Statement copy() {
        throw new RuntimeException("cannot copy block statement");
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        for (Statement s : statements) {
            s.compile();
        }
    }

    // Импортирование
    public void importAll() {
        // перебор стейтментов
        for (Statement s : statements) {
            // Если функция или класс - ток компилируем
            if (s instanceof FunctionStatement
                    || s instanceof ClassStatement) {
                s.compile();
            }
        }
    }
}
