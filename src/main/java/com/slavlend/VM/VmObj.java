package com.slavlend.VM;

import lombok.Getter;

/*
Инстанс класса вм
 */
@Getter
public class VmObj {
    // скоуп
    private final VmFrame<Object> scope = new VmFrame<>();
    // класс
    private final VmClass clazz;

    public VmObj(IceVm vm, VmClass clazz) {
        this.clazz = clazz;
        scope.setRoot(vm.getVariables());
    }

    public void call(String name, IceVm vm) {
        VmFunction func = clazz.getFunctions().getValues().get(name).copy();
        func.getScope().setRoot(scope);
        func.exec(vm);
    }
}
