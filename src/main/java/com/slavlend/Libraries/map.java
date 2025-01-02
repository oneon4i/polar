package com.slavlend.Libraries;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Polar.Stack.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class map {
    public HashMap<PolarValue, PolarValue> valuesMap = new HashMap<PolarValue, PolarValue>();

    public map() {
    }

    public void add(PolarValue k, PolarValue v) {
        if (!contains(k).asBool()) {
            valuesMap.put(k, v);
        }
    }

    public void del(PolarValue k) {
        if (contains(k).asBool()) {
            for (PolarValue key : valuesMap.keySet()) {
                if (key.equal(k)) {
                    valuesMap.remove(key);
                    return;
                }
            }
        }
    }

    public PolarValue size() {
        return new PolarValue(valuesMap.size());
    }

    public PolarValue get(PolarValue k) {
        for (PolarValue key : valuesMap.keySet()) {
            if (key.equal(k)) {
                return valuesMap.get(key);
            }
        }

        return new PolarValue(null);
    }

    public PolarValue contains(PolarValue k) {
        for (PolarValue key : valuesMap.keySet()) {
            if (key.equal(k)) {
                return new PolarValue(true);
            }
        }

        return new PolarValue(false);
    }

    public PolarValue to_string() {
        StringBuilder str = new StringBuilder("{");

        for (PolarValue key : valuesMap.keySet()) {
            PolarValue value = valuesMap.get(key);

            if (!value.isObject()) {
                str.append(key.asString() + ": " + value.asString() + ", ");
            }
            else {
                if (value.asObject().clazz.name.equals("Map") ||
                        value.asObject().clazz.name.equals("Array")) {
                    String _value = "";

                    Storage.getInstance().push();
                    _value = value.asObject().classValues.get("to_string").asFunc().call(value.asObject(), new ArrayList<>()).asString();
                    Storage.getInstance().pop();

                    str.append(key.asString() + ": " + _value + ", ");
                }
                else {
                    str.append(key.asString() + ": " + value.asString() + ", ");
                }
            }
        }

        // удаляем последнию запятую
        str.delete(str.toString().length()-2, str.toString().length()-1);

        str.append("}");

        return new PolarValue(str.toString().replace("'", "\""));
    }

    public PolarValue dumps() {
        StringBuilder str = new StringBuilder("{");

        for (PolarValue key : valuesMap.keySet()) {
            PolarValue value = valuesMap.get(key);

            if (!value.isObject()) {
                str.append("'" + key.asString() + "': " + "'" + value.asString() + "'" + ", ");
            }
            else {
                if (value.asObject().clazz.name.equals("Map") ||
                        value.asObject().clazz.name.equals("Array")) {
                    String _value = "";

                    Storage.getInstance().push();
                    _value = value.asObject().classValues.get("dumps").asFunc().call(value.asObject(), new ArrayList<>()).asString();
                    Storage.getInstance().pop();

                    str.append("'" + key.asString() + "': " + _value + ", ");
                }
                else {
                    str.append("'" + key.asString() + "': " + value.asString() + ", ");
                }
            }
        }

        // удаляем последнию запятую
        if (str.toString().length() > 1) {
            str.delete(str.toString().length() - 2, str.toString().length() - 1);
        }

        str.append("}");

        return new PolarValue(str.toString().replace("'", "\""));
    }

    public PolarValue keys() {
        ArrayList<PolarValue> _keys = new ArrayList<>(valuesMap.keySet());
        PolarObject arr = PolarObject.create(Classes.getInstance().getClass("Array"), new ArrayList<>());
        ArrayList<PolarValue> _temp = new ArrayList<>();
        for (PolarValue key: _keys) {
            _temp.add(key);
            arr.classValues.get("add").asFunc().call(null, _temp);
            _temp.clear();
        }
        return new PolarValue(arr);
    }
}
