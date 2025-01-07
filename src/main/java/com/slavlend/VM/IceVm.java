package com.slavlend.VM;

import com.slavlend.Colors;
import lombok.Getter;

import java.util.Scanner;
import java.util.Stack;

/*
Виртуальная машина ICE
 */
@Getter
public class IceVm {
    // стек объектов
    private final ThreadLocal<Stack<Object>> stack = new ThreadLocal<>();
    // хранилище
    private final VmFrame<Object> variables = new VmFrame<>();
    private final VmFrame<VmFunction> functions = new VmFrame<>();
    private final VmFrame<VmClass> classes = new VmFrame<>();

    /**
     *
     * @param code - код для запуска
     *             кода в виртуальной машине
     */
    public void run(VmCode code) {
        // выводим псевдо-байткод
        printByteCode(code);
        // запускаем бенчмарк
        VmBenchmark benchmark = new VmBenchmark();
        benchmark.start();
        // инициализация стека
        initStackForThread();
        // исполняем код
        for (VmInstr instr : code.getInstructions()) {
            instr.run(this, variables);
        }
        // останавливаем бенчмарк
        System.out.println(
                Colors.ANSI_BLUE + "🧊 Exec time: " + benchmark.end() + Colors.ANSI_RESET
        );
    }

    /**
     * Инициализация стека под поток
     */
    private void initStackForThread() {
        stack.set(new Stack<>());
    }

    /**
     *
     * @param code - код для вывода
     *             в консоль
     */
    private void printByteCode(VmCode code) {
        System.out.println(Colors.ANSI_BLUE + "🛞 Bytecode:" + Colors.ANSI_RESET);
        printCode(code);
        System.out.println();
    }

    /**
     * Выводит инструкции
     * @param code - код для вывода
     *             в консоль
     */
    private void printCode(VmCode code) {
        // выводим классы
        for (VmClass clazz : classes.getValues().values()) {
            clazz.print();
        }
        // выводим функции
        for (VmFunction function : functions.getValues().values()) {
            function.print();
        }
        // выводим другие инструкции
        for (VmInstr instr : code.getInstructions()) {
            instr.print();
        }
    }

    /**
     * Пушит в стек объект
     * @param val - объект, для помещения в стек
     */
    public void push(Object val) {
        stack.get().push(val);
        /*
        if (val == null) {
            StackTraceElement[] elems =  Thread.currentThread().getStackTrace();
            for (StackTraceElement elem : elems) {
                System.out.println(elem.toString());
            }
        }
         */
    }

    /**
     * Удаляет объект с верхушки стека, возвращая его
     * @return - отдаёт объект с верхушки стека
     */
    public Object pop() {
        return stack.get().pop();
    }

    /**
     * Загружает переменную в стек
     * @param frame - фрейм, для поиска переменной
     * @param name - имя переменной для поиска
     */
    public void load(VmFrame<Object> frame, String name) {
        stack.get().push(frame.lookup(name));
    }

    /**
     * Вызывает глобальную функцию
     * @param name - имя для вызова
     */
    public void callGlobal(String name) {
        switch (name) {
            case "put" -> {
                Object o = pop();
                System.out.println(o);
            }
            case "scan" -> {
                Object o = pop();
                if (!((String)o).isEmpty()) {
                    System.out.println(o);
                }
                Scanner sc = new Scanner(System.in);
                push(sc.nextLine());
                sc.close();
            }
            default -> {
                if (functions.getValues().containsKey(name)) {
                    functions.lookup(name).exec(this);
                } else {
                    ((VmFunction)variables.lookup(name)).exec(this);
                }
            }
        }
    }
}
