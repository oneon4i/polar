package com.slavlend.Vm.Instructions;

import com.slavlend.Vm.*;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/*
Вызов функции в VM
 */
@SuppressWarnings("ClassCanBeRecord")
@Getter
public class VmInstrCall implements VmInstr {
    // адресс
    private final VmInAddr addr;
    // имя
    private final String name;
    // есть ли предыдущий аксесс
    private final boolean hasPrevious;
    // аргументы
    private final VmVarContainer args;

    // конструктор
    public VmInstrCall(VmInAddr addr, String name, VmVarContainer args, boolean hasPrevious) {
        this.addr = addr;
        this.name = name; this.args = args; this.hasPrevious = hasPrevious;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        if (!hasPrevious) {
            if (frame.has(name)) {
                // аргументы
                int argsAmount = passArgs(vm, frame);
                VmFunction fn = ((VmFunction)frame.lookup(addr, name));
                checkArgs(fn.getArguments().size(), argsAmount);
                // вызов
                fn.exec(vm);
            } else {
                // аргументы
                int argsAmount = passArgs(vm, frame);
                // вызов
                if (!vm.isCoreFunc(name)) {
                    if (vm.getFunctions().has(name)) {
                        checkArgs(vm.getFunctions().lookup(addr, name).getArguments().size(), argsAmount);
                    }
                    else {
                        checkArgs(((VmFunction)vm.getVariables().lookup(addr, name)).getArguments().size(), argsAmount);
                    }
                    vm.callGlobal(addr, name);
                } else {
                    checkArgs(vm.getCoreFunctions().lookup(addr, name).argsAmount(), argsAmount);
                    vm.callGlobal(addr, name);
                }
            }
        } else {
            Object last = vm.pop();
            if (last instanceof VmObj vmObj) {
                // аргументы
                int argsAmount = passArgs(vm, frame);
                VmFunction fn = vmObj.getClazz().getFunctions().lookup(addr, name);
                checkArgs(fn.getArguments().size(), argsAmount);
                // вызов
                vmObj.call(addr, name, vm);
            } else if (last instanceof VmClass vmClass){
                // аргументы
                int argsAmount = passArgs(vm, frame);
                VmFunction fn = vmClass.getModFunctions().lookup(addr, name);
                checkArgs(fn.getArguments().size(), argsAmount);
                // вызов модульной функции
                vmClass.getModFunctions().lookup(addr, name).exec(vm);
            } else {
                // аргументы
                int argsAmount = passArgs(vm, frame);
                ArrayList<Object> callArgs = new ArrayList<>();
                for (int i = argsAmount-1; i >= 0; i--) {
                    Object arg = vm.pop();
                    callArgs.add(0, arg);
                }
                // рефлексийный вызов
                Method[] methods = last.getClass().getMethods();
                Method func = null;
                for (Method m : methods) {
                    if (m.getName().equals(name)) {
                        // System.out.println("name: " + name + ":" + m.getParameterCount() + ", " + args.getVarContainer());
                    }
                    if (m.getName().equals(name) &&
                        m.getParameterCount() == argsAmount) {
                        func = m;
                    }
                }
                if (func == null) {
                    IceVm.logger.error(addr, "function not found: " + name + " in: " + last.getClass().getName());
                }
                else {
                    checkArgs(func.getParameterCount(), callArgs.size());
                    try {
                        Object returned = func.invoke(last, callArgs.toArray());
                        // 👇 НЕ ВОЗВРАЩАЕТ NULL, ЕСЛИ ФУНКЦИЯ НИЧЕГО НЕ ВОЗВРАЩАЕТ
                        if (returned != null) {
                            vm.push(returned);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        IceVm.logger.error(addr, e.getCause().getMessage());
                    }
                }
            }
        }
    }

    // проверка на колличество параметров и аргументов
    private void checkArgs(int parameterAmount, int argsAmount) {
        if (parameterAmount != argsAmount) {
            IceVm.logger.error(addr,
                    "args and params not match: (expected:"+parameterAmount+",got:"+argsAmount +")");
        }
    }

    // помещает аргументы в стек
    private int passArgs(IceVm vm, VmFrame<Object> frame) {
        int size = vm.getStack().get().size();
        for (VmInstr instr : args.getVarContainer()) {
            instr.run(vm, frame);
        }
        return vm.getStack().get().size()-size;
    }

    @Override
    public String toString() {
        return "CALL[" + name  + "]" + "(" + args.getVarContainer().size() + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
