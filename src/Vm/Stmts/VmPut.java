package com.slavlend.Vm.Stmts;

import com.slavlend.Vm.VmExpr;
import com.slavlend.Vm.VmStack;
import com.slavlend.Vm.VmStmt;
import com.slavlend.Vm.VmVal;

/*
Стейтмент помещения переменной в стек
 */
public class VmPut implements VmStmt {
    private final VmExpr expr;
    private final String name;

    // конструктор
    public VmPut(String name, VmExpr expr) {
        this.name = name;
        this.expr = expr;
    }
    
    @Override
    public void exec() {
        VmStack.instance.put(name, expr.eval());
    }
}
