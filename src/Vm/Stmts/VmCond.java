package com.slavlend.Vm.Stmts;

import com.slavlend.Vm.*;

import java.util.List;

/*
Стейтмент кондишена
 */
public class VmCond implements VmStmt {
    private final VmExpr r;
    private final VmExpr l;
    private final VmOperator operator;
    private final String name;
    private final List<VmStmt> stmtList;

    // конструктор
    public VmCond(String name, VmExpr r, VmOperator operator, VmExpr l, List<VmStmt> stmt) {
        this.name = name;
        this.r = r;
        this.l = l;
        this.operator = operator;
        this.stmtList = stmt;
    }
    
    @Override
    public void exec() throws VmException {
        switch (operator) {
            case BI -> {
                if (l.eval().number() > r.eval().number()) {
                    for (VmStmt stmt : stmtList) {
                        stmt.exec();
                    }
                }
            }
            case LW -> {
                if (l.eval().number() < r.eval().number()) {
                    for (VmStmt stmt : stmtList) {
                        stmt.exec();
                    }
                }
            }
            case EQ -> {
                if (l.eval().number() == r.eval().number()) {
                    for (VmStmt stmt : stmtList) {
                        stmt.exec();
                    }
                }
            }
            case NEQ -> {
                if (l.eval().number() != r.eval().number()) {
                    for (VmStmt stmt : stmtList) {
                        stmt.exec();
                    }
                }
            }
            case BEQ -> {
                if (l.eval().number() >= r.eval().number()) {
                    for (VmStmt stmt : stmtList) {
                        stmt.exec();
                    }
                }
            }
            case LEQ -> {
                if (l.eval().number() <= r.eval().number()) {
                    for (VmStmt stmt : stmtList) {
                        stmt.exec();
                    }
                }
            }
            default -> {
                throw new VmException("Invalid Cond Operator: " + operator.name());
            }
        }
    }
}
