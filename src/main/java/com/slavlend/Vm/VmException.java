package com.slavlend.Vm;

import lombok.Getter;

/*
Выкидываемый эксцепшен
 */
@Getter
public class VmException extends RuntimeException {
    // адресс
    private final VmInAddr addr;
    // сообщение
    private final String message;
    // значение
    private final String value;

    /**
     * Конструктор
     * @param addr - адресс
     * @param message - сообщение
     * @param value - слово ссылающиеся
     *              на ошибку
     */
    public VmException(VmInAddr addr, String message, String value) {
        this.addr = addr;
        this.message = message;
        this.value = value;
    }

    /**
     * Конструктор
     * @param addr - адресс
     * @param message - сообщение
     */
    public VmException(VmInAddr addr, String message) {
        this.addr = addr;
        this.message = message;
        this.value = null;
    }
}
