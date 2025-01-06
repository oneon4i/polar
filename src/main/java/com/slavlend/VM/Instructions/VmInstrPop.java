package com.slavlend.VM.Instructions;

import com.slavlend.VM.IceVm;
import com.slavlend.VM.VmFrame;
import com.slavlend.VM.VmInstr;

/*
Удаление значения из VM
 */
public class VmInstrPop implements VmInstr {
    @Override
    public void run(IceVm vm, VmFrame<Object> frame) {
        vm.pop();
    }
}
