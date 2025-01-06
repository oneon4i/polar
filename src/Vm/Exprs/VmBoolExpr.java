package com.slavlend.Vm.Exprs;

import com.slavlend.Vm.VmExpr;
import com.slavlend.Vm.VmVal;

public class VmBoolExpr implements VmExpr {
    private String data;
    public VmBoolExpr(String data) {
        this.data = data;
    }
    @Override
    public VmVal eval() {
        return new VmVal(Boolean.parseBoolean(data));
    }
}