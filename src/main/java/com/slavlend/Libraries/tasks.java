package com.slavlend.Libraries;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Polar.StackHistoryWriter;
import com.slavlend.Parser.Statements.FunctionStatement;

import java.util.ArrayList;

/*
Библиотека для работы с таксками (потоками под капотом)
 */
public class tasks {

    // выполнение функции
    public void exec(PolarObject o, FunctionStatement func, PolarValue args) {
        // создаем новый поток
        Thread thread = new Thread() {
            @Override
            public void run() {
                // инициализируем стек под поток
                Storage.getInstance().callStack.set(new ArrayList<>());
                StackHistoryWriter.getInstance().hist.set(new ArrayList<>());
                // пушим в стек новый фрейм
                Storage.getInstance().push();
                // аргументы для функции
                ArrayList<PolarValue> _args = new ArrayList<>();
                _args.addAll(args.asList());
                // вызываем функцию
                func.call(o, _args);
                // удаляем фрейм из стека
                Storage.getInstance().pop();
            }
        };

        // запускаем поток
        thread.start();
    }
}
