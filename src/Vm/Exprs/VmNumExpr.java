package com.slavlend.Vm.Exprs;

import com.slavlend.Vm.VmExpr;
import com.slavlend.Vm.VmVal;

public class VmNumExpr implements VmExpr {
    private String data;
    public VmNumExpr(String data) {
        this.data = data;
    }
    @Override
    public VmVal eval() {
        return new VmVal(Float.parseFloat(data));
    }
}
