package com.slavlend.Compiler.Libs;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/*
Библиотека времени для Вм
 */

public class Time {
    /*
    Получение текущего времени в мс
     */
    public long now_mills() {
        return java.lang.System.currentTimeMillis();
    }

    /*
    Получение текущего времени в мс с оффсетом
     */
    public long now_mills_offset(Float offset) {
        return java.lang.System.currentTimeMillis() + offset.longValue();
    }

    /*
    Получение текущего времени в сек
     */
    public long now_seconds() {
        return java.lang.System.currentTimeMillis() / 1000;
    }

    /*
    Сравнение
     */
    public boolean is_greater(long left, long right) { return left > right; }
    public boolean is_less(long left, long right) { return left < right; }

    /*
    Из таймстампа
     */
    public static LocalDateTime from_unix(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    /*
    Конвертация в мс
     */
    public static long to_unix(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
