package com.slavlend.System;

/*
Информация об ос
 */
public class PolarSystem {
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
}
