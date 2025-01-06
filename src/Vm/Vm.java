package com.slavlend.Vm;

import java.util.ArrayList;
import java.util.List;

/*
Виртуальная машина
 */
public class Vm {
    // стэйтменты
    private List<VmStmt> stmtList = new ArrayList<>();

    // инстанс
    public static Vm vm;

    // конструкция
    public Vm() {
        vm = this;
    }

    // помещение инструкции
    public void put(VmStmt stmt) {
        stmtList.add(stmt);
    }
}
