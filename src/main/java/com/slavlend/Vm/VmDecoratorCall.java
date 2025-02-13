package com.slavlend.Vm;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.Instructions.VmInstrPush;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
Вызов декоратора
 */
@Getter
@AllArgsConstructor
public class VmDecoratorCall {
    private final VmInAddr addr;
    private final VmVarContainer decorator;
    private final VmFunction argFunction;

    // процесс
    public void process() {
        // ищем функцию декоратора
        Object value = decorator.exec();
        VmFunction decoratorFn = (VmFunction) value;
        // загружаем аргумент
        new VmInstrPush(addr, argFunction).run(Compiler.iceVm, Compiler.iceVm.getVariables());
        // выполняем функцию
        decoratorFn.exec(Compiler.iceVm, false);
    }
}
