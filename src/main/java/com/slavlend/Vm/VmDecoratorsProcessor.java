package com.slavlend.Vm;
import lombok.Getter;

import java.util.ArrayList;

/*
Проходится по функциям реализующим
декоратор, и выполняет инструкции.
 */
@Getter
public class VmDecoratorsProcessor {
    // запланированный вызов
    private final ArrayList<VmDecoratorCall> schedules = new ArrayList<>();

    // планирование
    public void schedule(VmVarContainer decorator, VmFunction fn) {
        schedules.add(new VmDecoratorCall(fn.getAddr(), decorator, fn));
    }

    // процессинг декораций
    public void processDecorators() {
        for (VmDecoratorCall call : schedules) {
            call.process();
        }
    }
}
