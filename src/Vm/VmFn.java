package com.slavlend.Vm;

import java.util.ArrayList;
import java.util.List;

/*
Функция виртуальной машины
 */
public class VmFn {
    // стейтменты
    private final List<VmStmt> statements;
    // имя
    private final String name;
    // параметры
    public List<String> params = new ArrayList<>();

    // конструктор
    public VmFn(String name, List<VmStmt> statements, List<String> params) {
        this.name = name;
        this.statements = statements;
    }

    // вызов
    public void call(List<VmVal> vals) {
        for (String arg : params) {

        }
    }
}
