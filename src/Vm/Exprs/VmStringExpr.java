package com.slavlend.Vm.Exprs;

import com.slavlend.Vm.VmExpr;
import com.slavlend.Vm.VmVal;

public class VmStringExpr implements VmExpr {
    private String data;
    public VmStringExpr(String data) {
        this.data = data;
    }
    @Override
    public VmVal eval() {
        return new VmVal(data);
    }
}