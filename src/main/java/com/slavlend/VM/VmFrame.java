package com.slavlend.VM;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/*
Фрейм стека
 */
@Getter
public class VmFrame<T> {
    private HashMap<String, T> values = new HashMap<>();
    @Setter
    private VmFrame<T> root;
    public T lookup(String name) {
        VmFrame<T> current = this;
        while (current.root != null && !current.root.getValues().containsKey(name)) {
            current = current.root;
        }
        return current.getValues().getOrDefault(name, null);
    }
    public void set(String name, T val) {
        VmFrame<T> current = this;
        while (current.root != null && !current.root.getValues().containsKey(name)) {
            if (current.getValues().containsKey(name)) {
                current.getValues().put(name, val);
            }
            current = current.root;
        }
        if (current.getValues().containsKey(name)) {
            current.getValues().put(name, val);
        }
        else {
            getValues().put(name, val);
        }
    }
}
