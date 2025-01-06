package com.slavlend.VM;

import lombok.Getter;

import java.util.Stack;

/*
Виртуальная машина ICE
 */
@Getter
public class IceVm {
    private final Stack<Object> stack = new Stack<>();
    private final VmFrame<Object> variables = new VmFrame<Object>();
    private final VmFrame<VmFunction> functions = new VmFrame<VmFunction>();
    private final VmFrame<VmClass> classes = new VmFrame<VmClass>();

    public void defineClass(VmClass cls) {
        this.classes.getValues().put(cls.getName(), cls);
    }

    public void defineFunction(VmFunction fn) {
        this.functions.getValues().put(fn.getName(), fn);
    }

    public void run(VmCode code) {
        for (VmInstr instr : code.getInstructions()) {
            instr.run(this, variables);
        }
    }

    public void push(Object val) {
        stack.push(val);
    }

    public Object pop() {
        return stack.pop();
    }

    public void load(VmFrame<Object> frame, String name) {
        stack.push(frame.getValues().get(name));
    }

    public void call(String name) {
        if (!stack.isEmpty() && stack.firstElement() instanceof VmObj obj) {
            if (obj.getClazz().getFunctions().lookup(name) != null) {
                obj.call(name, this);
            } else {
                if (functions.lookup(name) != null) {
                    functions.lookup(name).exec(this);
                } else {
                    if (name.equals("put")) {
                        System.out.println(pop());
                    }
                }
            }
        } else {
            if (functions.lookup(name) != null) {
                functions.lookup(name).exec(this);
            } else {
                if (name.equals("put")) {
                    System.out.println(pop());
                }
            }
        }
    }
}
