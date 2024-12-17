package com.slavlend.Parser.Statements;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;

import java.util.ArrayList;

// интерфейс для вызываемого объекта
public interface Callable {
    abstract PolarValue call(PolarObject calledFor, ArrayList<PolarValue> parameters);
}
