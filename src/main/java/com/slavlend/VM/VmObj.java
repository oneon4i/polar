package com.slavlend.VM;

import lombok.Getter;

/*
Инстанс класса ВМ
 */
@Getter
public class VmObj {
    // скоуп
    private final VmFrame<Object> scope = new VmFrame<>();
    // класс
    private final VmClass clazz;

    // конструктор
    public VmObj(IceVm vm, VmClass clazz) {
        this.clazz = clazz;
        for (int i = clazz.getConstructor().size()-1; i >= 0; i--) {
            Object arg = vm.pop();
            scope.set(clazz.getConstructor().get(i).data, arg);
        }
        scope.setRoot(vm.getVariables());
        if (clazz.getFunctions().getValues().containsKey("init")) {
            call("init", vm);
        }
    }

    /**
     * Вызов функции объекта
     * @param name - имя функции
     * @param vm - ВМ
     */
    public void call(String name, IceVm vm) {
        // копируем и вызываем функцию
        VmFunction func = clazz.getFunctions().getValues().get(name).copy();
        func.setDefinedFor(this);
        func.getScope().get().setRoot(scope);
        func.exec(vm);
    }
}
