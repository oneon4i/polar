package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Exceptions.PolarException;
import com.slavlend.Functions.BuiltInFunctions;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.Expression;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.*;
import com.slavlend.Polar.Stack.Storage;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/*
Акссесс к функции
 */
@Getter
public class CallAccess implements Access {
    // следующий
    private Access next;
    // имя функции
    private final String funcName;
    // аддресс
    private final Address address;
    // параметры
    @Setter
    private ArrayList<Expression> params;

    // конструктор
    public CallAccess(Address address, Access next, String funcName, ArrayList<Expression> params) {
        this.address = address;
        this.next = next;
        this.funcName = funcName;
        this.params = params;
    }

    // парсинг параметров
    public ArrayList<PolarValue> parseParameters() {
        // параметры
        ArrayList<PolarValue> _params = new ArrayList<>();

        // эвалуируем, заполняем
        for (Expression e : this.params) {
            _params.add(e.evaluate());
        }

        // возвращаем
        return _params;
    }

    // акссес
    @Override
    public PolarValue access(PolarValue previous) {
        // если нет предыдущего
        if (previous == null) {
            // рузльтат
            PolarValue res = null;
            // пробуем найти функцию из built-in
            if (BuiltInFunctions.functionHashMap.containsKey(funcName) && next == null) {
                res = BuiltInFunctions.functionHashMap.get(funcName).execute(address, parseParameters());
                return res;
            }
            // пробуем найти функцию юзера
            PolarValue v = Storage.getInstance().get(address, funcName);
            // вызываем
            ArrayList<PolarValue> _params = parseParameters();
            StackHistoryWriter.getInstance().pushCall(address, funcName);
            Storage.getInstance().push();
            res = v.asFunc().call(null, _params);
            Storage.getInstance().pop();

            // если нет следующего
            if (next == null) {
                return res;
            }
            else {
                return next.access(res);
            }
        }
        // если есть
        else {
            // если предыдущий объект
            if (previous.isObject()) {
                // получаем функцию
                PolarObject v = previous.asObject();
                // вызываем
                ArrayList<PolarValue> _params = parseParameters();
                StackHistoryWriter.getInstance().pushCall(address, v.getClazz().getName() + "." + funcName);
                if (!v.getClassValues().containsKey(funcName)) {
                    PolarLogger.exception("Function: " + funcName + " Not Found! ", address);
                    return null;
                }
                checkArgs(
                        v.getClazz().getName() + "." + funcName,
                        v.getClassValues().get(funcName).asFunc().getArguments().size(),
                        _params.size());
                Storage.getInstance().push();
                PolarValue res = v.getClassValues().get(funcName).asFunc().call(previous.asObject(), _params);
                Storage.getInstance().pop();

                // если нет следующего
                if (next == null) {
                    return res;
                } else {
                    return next.access(res);
                }
            }
            // если предыдущий рефлектед
            else if (previous.isReflected())
            {
                // получаем переменную
                Reflected r = previous.asReflected();
                PolarValue res = null;
                // получаем
                try {
                    // получаем значение java-метода
                    Method method = null;
                    for (Method _method : r.getClazz().getMethods()) {
                        if (_method.getName().equals(funcName) && _method.getParameterCount() == params.size()) {
                            method = _method;
                        }
                    }
                    if (method == null) PolarLogger.exception(
                            "Java Method With Name: " + funcName + " Not Found In: " + r.getClazz().getSimpleName(),
                            address
                    );
                    // конвертируем аргументы в java-like
                    ArrayList<Object> javaLikeParams = convertToJavaParams(method);
                    // вызываем метод
                    Object result = null;
                    try {
                        if (method.getParameterCount() == params.size()) {
                            result = method.invoke(r.getReflectedObject(), javaLikeParams.toArray());
                        } else {
                            PolarLogger.exception(
                                    "Arguments & Parameters Size Doesn't Match ("
                                            + method.getName()
                                            + ")"
                                            + " (Expected: "
                                            + method.getParameterCount()
                                            + ", Founded: "
                                            + params.size()
                                            + ")",
                                    address
                            );
                        }
                    } catch (InvocationTargetException e) {
                        String exception = "";
                        if (e.getCause() instanceof PolarException polarException) {
                            exception = polarException.getError();
                        }
                        else {
                            exception = e.getCause().getMessage();
                        }
                        PolarLogger.exception(
                                "Invocation Target Exception (Java): \n│ " + exception + ",\n│ while calling: " + method.getName(),
                                address,
                                e.getCause().getStackTrace());
                    }
                    // в зависимости от типа создаем переменную
                    if (result instanceof Integer) {
                        res = new PolarValue(((Integer)result).floatValue());
                    }
                    else if (result instanceof Long ||
                            result instanceof Float ||
                            result instanceof Double) {
                        res = new PolarValue(result);
                    } else if (result instanceof String || result instanceof Boolean) {
                        res = new PolarValue(result);
                    } else if (result instanceof PolarValue) {
                        res = (PolarValue)result;
                    } else if (result instanceof PolarObject) {
                        res = new PolarValue((PolarObject) result);
                    } else {
                        if (result != null) {
                            res = new PolarValue(new Reflected(address, result.getClass(), result));
                        }
                    }
                } catch (IllegalAccessException e) {
                    PolarLogger.exception("Illegal Access Exception (Java): " + e.getMessage(), address);
                }

                // если нет следующего
                if (next == null) {
                    return res;
                } else {
                    return next.access(res);
                }
            }
            // если предыдущий класс
            else {
                // получаем функцию
                PolarClass v = previous.asClass();
                // вызываем
                ArrayList<PolarValue> _params = parseParameters();
                StackHistoryWriter.getInstance().pushCall(address, v.getName() + "." + funcName);
                if (previous.asClass().getModuleFunctions().containsKey(funcName)) {
                    checkArgs(v.getName() + "." + funcName, v.getModuleFunctions().get(funcName).getArguments().size(), _params.size());
                } else {
                    PolarLogger.exception("Mod Function Not Found: " + funcName, address);
                }
                Storage.getInstance().push();
                PolarValue res = v.getModuleFunctions().get(funcName).call(null, _params);
                Storage.getInstance().pop();

                // если нет следующего
                if (next == null) {
                    return res;
                } else {
                    return next.access(res);
                }
            }
        }
    }

    // конвертация в джава параметры
    private ArrayList<Object> convertToJavaParams(Method method) {
        Class<?>[] parameters = method.getParameterTypes();
        ArrayList<Object> values = new ArrayList<>();

        // проходимся по параметрам и конвертируем
        for (int i = 0; i < parameters.length; i++) {
            PolarValue v = params.get(i).evaluate();
            Class<?> _clazz = parameters[i];
            // числа
            if (_clazz == Integer.class) {
                values.add((Integer) ((int) ((double)v.asNumber())));
            }
            else if (_clazz == int.class) {
                values.add((Integer) ((int) ((double)v.asNumber())));
            }
            else if (_clazz == Float.class) {
                values.add((v.asNumber()));
            }
            else if (_clazz == float.class) {
                values.add((v.asNumber()));
            }
            // символы
            else if (_clazz == String.class) {
                values.add(v.asString());
            }
            else if (_clazz == Character.class) {
                values.add((Character) v.asString().charAt(0));
            }
            else if (_clazz == char.class) {
                values.add((char) v.asString().charAt(0));
            }
            // логика
            else if (_clazz == boolean.class) {
                values.add((boolean) v.asBool());
            }
            else if (_clazz == Boolean.class) {
                values.add((Boolean) v.asBool());
            }
            // объекты
            else if (_clazz == Object.class) {
                values.add(v);
            }
            else if (_clazz == PolarObject.class) {
                values.add(v.asObject());
            }
            else if (_clazz == PolarValue.class) {
                values.add(v);
            }
            else if (_clazz == FunctionStatement.class) {
                values.add(v.asFunc());
            } else {
                if (v.isReflected()) {
                    values.add(_clazz.cast(v.asReflected().getReflectedObject()));
                }
                else {
                    PolarLogger.exception("Impossible To Convert Not Reflected Types To Java Like Classes: " + _clazz, address);
                }
            }
        }

        return  values;
    }

    // проверяем колличество аргументов
    public void checkArgs(String name, int fSize, int sSize) {
        if (fSize != sSize) {
            // ошибка если колличество
            // аргументов не совпадает
            PolarLogger.exception(
                    "Arguments & Parameters Size Doesn't Match ("
                            + name
                            + ")"
                            + " (Expected: "
                            + fSize
                            + ", Founded: "
                            + sSize
                            + ")",
                    address
            );
        }
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
