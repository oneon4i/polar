package com.slavlend;

import java.io.IOException;

// цвета
public class Colors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[38;5;197m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[38;5;221m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_DARK_BLUE = "\u001B[38;2;77;102;214m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_LIME = "\u001B[92m";

    public static void Clear() {
        for (int i = 0; i < 100; i++) {
            System.out.print("\n");
            System.out.flush();
        }
    }
}
