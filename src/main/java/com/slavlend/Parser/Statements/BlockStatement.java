package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Access.AccessExpression;
import com.slavlend.Parser.Expressions.Access.AssignAccess;

import java.util.ArrayList;

/*
 блок стэйтмент. Главный блок.
 */
public class BlockStatement implements Statement {
    // стэйтменты
    public ArrayList<Statement> statements = new ArrayList<>();
    // адресс
    private Address address = App.parser.address();

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void optimize() {
        // ...
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // System.out.println("Executing Block");
        for (Statement statement : statements) {
            statement.execute();
        }
    }

    @Override
    public void interrupt() {
        throw new RuntimeException("Interruption call");
    }

    @Override
    public Statement copy() {
        throw new RuntimeException("Cannot Copy Block Statement");
    }

    @Override
    public Address address() {
        return address;
    }
}
