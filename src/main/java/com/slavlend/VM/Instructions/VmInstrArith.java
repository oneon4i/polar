package com.slavlend.VM.Instructions;

import com.slavlend.Parser.Operator;
import com.slavlend.VM.*;

/*
Инструкция арифметической операции
 */
@SuppressWarnings({"SpellCheckingInspection", "RedundantCast", "ConstantValue"})
public class VmInstrArith implements VmInstr {
    // оператор
    private final Operator operator;

    public VmInstrArith(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        Object r = vm.pop();
        Object l = vm.pop();
        if (r == null) {
            r = "nil";
        }
        if (l == null) {
            l = "nil";
        }
        switch (operator.operator) {
            case "+" -> {
                if (l instanceof String || r instanceof String) {
                    vm.push(l.toString() + r.toString());
                    return;
                }
                vm.push((float)l + (float)r);
            }
            case "-" -> vm.push((float)l - (float)r);
            case "*" -> vm.push((float)l * (float)r);
            case "/" -> vm.push((float)l / (float)r);
            case "%" -> vm.push((float)l % (float)r);
        }
    }

    @Override
    public void print() {
        System.out.println("ARITH("+operator.operator+")");
    }

    public boolean equal(Object l, Object r) {
        if (l instanceof String && r instanceof String) {
            return ((String)l).equals(((String)r));
        }
        else if (l == null && r != null) {
            return false;
        }
        else if (l != null && r == null) {
            return false;
        }
        else if (l == null && r == null) {
            return true;
        }
        else if (l instanceof VmObj && r instanceof VmObj) {
            return (l == r);
        }
        else if (l instanceof Boolean && r instanceof Boolean) {
            return (l == r);
        }
        else if (l instanceof Float && r instanceof Float) {
            return (l == r);
        }
        else if (l instanceof VmClass && r instanceof VmClass) {
            return (l == r);
        }
        else {
            throw new RuntimeException("invalid comparable types: " + l + ", " + r);
        }
    }

    @Override
    public String toString() {
        return "ARITH(" + operator.operator + ")";
    }
}
