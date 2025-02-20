package com.slavlend.Vm;

/*
Вывод ошибок
 */
public interface VmErrLogger {
    /**
     * Выводит ошибку
     * @param addr - аддресс из
     *             скомпилированного кода
     * @param value - конкретное слово,
     *              показывающее на ошибку
     * @param message - сообщение
     */
    void error(VmInAddr addr, String message, String value);

    /**
     * Выводит ошибку без показывающего на ошибку
     * слова
     * @param addr - аддресс из
     *             скомпилированного кода
     * @param message - сообщение
     */
    void error(VmInAddr addr, String message);

    /**
     * Выводит ошибку со стак трейсом
     * @param addr - аддресс из
     *             скомпилированного кода
     * @param message - сообщение
     * @param e - исключение
     */
    void error(VmInAddr addr, String message, RuntimeException e);
}
