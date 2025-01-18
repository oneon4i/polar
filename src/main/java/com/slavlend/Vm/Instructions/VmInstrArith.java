package com.slavlend.Vm.Instructions;

import com.slavlend.Parser.Operator;
import com.slavlend.Vm.*;
import lombok.Getter;

/*
Инструкция арифметической операции
 */
@SuppressWarnings({"SpellCheckingInspection", "RedundantCast", "ConstantValue"})
@Getter
public class VmInstrArith implements VmInstr {
    // адресс
    private final VmInAddr addr;
    // оператор
    private final String operator;

    public VmInstrArith(VmInAddr addr, String operator) {
        this.addr = addr;
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
        switch (operator) {
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
            default -> IceVm.logger.error(addr, "operator not found: " + operator);
        }
    }

    @Override
    public void print() {
        System.out.println("ARITH("+operator+")");
    }

    @Override
    public String toString() {
        return "ARITH(" + operator + ")";
    }
}
