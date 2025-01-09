package com.slavlend.Deprecated.Functions;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Parser.Address;

import java.util.List;

/*
Билд-ин функции
 */
public interface BuiltInFunction {
    // запуск функции
    public abstract PolarValue execute(Address address, List<PolarValue> args);
    // колличество аргументов
    public abstract int argsAmount();
}
