package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;
import lombok.Getter;

/*
Ломает текущее исполнение цикла или переходит на следующую итерацию
 */
@Getter
public class VmInstrLoopEnd extends RuntimeException implements VmInstr {
    private final boolean onlyCurrentIteration;

    public VmInstrLoopEnd(boolean onlyCurrentIteration) {
        this.onlyCurrentIteration = onlyCurrentIteration;
    }

    @Override
    public void run(IceVm vm, VmFrame<Object> scope) {
        throw this;
    }

    @Override
    public String toString() {
        return "END(CI:"+ onlyCurrentIteration +")";
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
}
