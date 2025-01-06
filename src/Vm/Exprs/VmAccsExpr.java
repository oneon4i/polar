package com.slavlend.Vm.Exprs;

import com.slavlend.Vm.VmExpr;
import com.slavlend.Vm.VmVal;

public class VmAccsExpr implements VmExpr {
    private String data;
    public VmAccsExpr(String data) {
        this.data = data;
    }
    @Override
    public VmVal eval() {
        return new VmVal(Boolean.parseBoolean(data));
    }
}