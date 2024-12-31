package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Reflected;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Optimization.Optimizations;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Expressions.NumberExpression;
import com.slavlend.Parser.Statements.FunctionStatement;

import java.lang.reflect.Field;

/*
Акссесс к функции
 */
public class AssignAccess implements Access {
    // следующий
    public Access next;
    // имя функции
    private final String varName;
    // аддресс
    private final Address address;
    // экспрешен
    private final Expression to;
    // аккссес тип
    private final AccessType type;

    // конструктор
    public AssignAccess(Address address, Access next, String varName, Expression to, AccessType type) {
        this.address = address;
        this.next = next;
        this.varName = varName;
        this.to = to;
        this.type = type;
    }

    // акссес
    @Override
    public PolarValue access(PolarValue previous) {
        // если нет предыдущего
        if (previous == null) {
            // получаем переменную
            PolarValue res = null;
            // устанавливаем переменную
            switch (type) {
                case SET -> Storage.getInstance().put(address, varName, Optimizations.optimize(to).evaluate());
                case MUL -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    Storage.getInstance().put(address, varName, Optimizations.optimize(new NumberExpression(
                            String.valueOf(e.asNumber() * to.evaluate().asNumber()))
                    ).evaluate());
                }
                case DIVIDE -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    Storage.getInstance().put(address, varName, Optimizations.optimize(new NumberExpression(
                            String.valueOf(e.asNumber() / to.evaluate().asNumber()))
                    ).evaluate());
                }
                case PLUS -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    Storage.getInstance().put(address, varName, Optimizations.optimize(new NumberExpression(
                            String.valueOf(e.asNumber() + to.evaluate().asNumber()))
                    ).evaluate());
                }
                case MINUS -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    Storage.getInstance().put(address, varName, Optimizations.optimize(new NumberExpression(
                            String.valueOf(e.asNumber() - to.evaluate().asNumber()))
                    ).evaluate());
                }
            }


            return null;
        }
        // если предыдущий рефлектед
        else if (previous.isReflected()) {
            // получаем переменную
            Reflected r = previous.asReflected();
            PolarValue res = null;
            // получаем
            try {
                // получаем значение java-переменной
                Field field = r.clazz.getField(varName);
                field.setAccessible(true);
                PolarValue _value = to.evaluate();
                Object value = convertToJavaValue(field, _value);
                field.set(r.o, field.getType().cast(value));
            } catch (IllegalAccessException e) {
                PolarLogger.Crash("Illegal Access Exception (Java): " + e.getMessage(), address);
            } catch (NoSuchFieldException e) {
                PolarLogger.Crash("No Such Field Exception (Java): " + e.getMessage(), address);
            }

            // если нет следующего
            if (next == null) {
                return res;
            } else {
                return next.access(res);
            }
        }
        // если предыдущий объект
        else {
            // получаем переменную
            PolarObject v = previous.asObject();
            // устанавливаем
            // устанавливаем переменную
            switch (type) {
                case SET -> v.classValues.put(varName, Optimizations.optimize(to).evaluate());
                case MUL -> {
                    PolarValue e = v.classValues.get(varName);
                    String mul = String.valueOf(e.asNumber() * Optimizations.optimize(to).evaluate().asNumber());
                    v.classValues.put(varName, new PolarValue(new NumberExpression(mul)));
                }
                case DIVIDE -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    String div = String.valueOf(e.asNumber() / Optimizations.optimize(to).evaluate().asNumber());
                    v.classValues.put(varName, new PolarValue(new NumberExpression(div)));
                }
                case PLUS -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    String div = String.valueOf(e.asNumber() + Optimizations.optimize(to).evaluate().asNumber());
                    v.classValues.put(varName, new PolarValue(new NumberExpression(div)));
                }
                case MINUS -> {
                    PolarValue e = Storage.getInstance().get(address, varName);
                    String div = String.valueOf(e.asNumber() - Optimizations.optimize(to).evaluate().asNumber());
                    v.classValues.put(varName, new PolarValue(new NumberExpression(div)));
                }
            }

            return null;
        }
    }

    // конвертация в джава значение
    private Object convertToJavaValue(Field field, PolarValue v) {
        // онвертируем
        Class<?> _clazz = field.getClass();
        // результат
        Object result = null;
        // числа
        if (_clazz == Integer.class) {
            result = ((Integer) ((int) ((double)v.asNumber())));
        }
        else if (_clazz == int.class) {
            result = ((Integer) ((int) ((double)v.asNumber())));
        }
        // символы
        else if (_clazz == String.class) {
            result = (v.asString());
        }
        else if (_clazz == Character.class) {
            result = ((Character) v.asString().charAt(0));
        }
        else if (_clazz == char.class) {
            result = ((char) v.asString().charAt(0));
        }
        // логика
        else if (_clazz == boolean.class) {
            result = ((boolean) v.asBool());
        }
        else if (_clazz == Boolean.class) {
            result = ((Boolean) v.asBool());
        }
        // объекты
        else if (_clazz == Object.class) {
            result = (v);
        }
        else if (_clazz == PolarObject.class) {
            result = (v.asObject());
        }
        else if (_clazz == PolarValue.class) {
            result = (v);
        }
        else if (_clazz == FunctionStatement.class) {
            result = (v.asFunc());
        } else {
            if (v.isReflected()) {
                result = (_clazz.cast(v.asReflected().o));
            }
            else {
                PolarLogger.Crash("Impossible To Convert Not Reflected Types To Java Like Classes", address);
            }
        }

        return result;
    }

    @Override
    public void setNext(Access access) {
        if (next != null) {
            next.setNext(access);
        }
        else {
            next = access;
        }
    }

    @Override
    public void setLast(Access access) {
        if (next.hasNext()) {
            next.setLast(access);
        }
        else {
            next = access;
        }
    }

    @Override
    public boolean hasNext() { return next != null; }

    @Override
    public Access getNext() { return next; }
}
