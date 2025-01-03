package com.slavlend.Libraries;

/*
Библиотека на консоль
 */
@SuppressWarnings("unused")
public class console {
    // конструктор
    public console() {
    }

    // очистка консоли
    public void cls() {
        // очистка консоли
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
