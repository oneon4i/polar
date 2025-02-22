package com.slavlend.Compiler.Libs;

import com.slavlend.Vm.VmClass;
import com.slavlend.Vm.VmObj;

/**
 * Библиотека для рефлексии полара
 */
public class PolarReflection {
    public String class_name(Object o) {
        if (o instanceof VmClass) {
            return ((VmClass) o).getName();
        } else if (o instanceof VmObj) {
            return ((VmObj)o).getClazz().getName();
        } else {
            return o.getClass().getSimpleName();
        }
    }
}
