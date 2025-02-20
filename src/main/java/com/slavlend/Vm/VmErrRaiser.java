package com.slavlend.Vm;

/*
Выкидыватель ошибок
 */
public class VmErrRaiser {
    public void error(VmInAddr inAddr, String message, String value) {
        throw new VmException(inAddr, message, value);
    }
}
