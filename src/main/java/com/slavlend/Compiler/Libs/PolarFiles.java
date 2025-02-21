package com.slavlend.Compiler.Libs;

import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*
Библиотека для работы с файлами
 */
public class PolarFiles {
    public byte[] read_bytes(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            throw new VmException(Compiler.iceVm.getLastCallAddr(), "cannot open directory as a file.");
        } else {
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                throw new VmException(Compiler.iceVm.getLastCallAddr(), "Io Exception: " + e.getMessage());
            }
        }
    }
    public String read_text(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            throw new VmException(Compiler.iceVm.getLastCallAddr(), "cannot open directory as a file.");
        } else {
            try {
                return new String(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                throw new VmException(Compiler.iceVm.getLastCallAddr(), "Io Exception: " + e.getMessage());
            }
        }
    }
    public void write_bytes(String path, byte[] bytes) {
        File file = new File(path);
        if (file.isDirectory()) {
            throw new VmException(Compiler.iceVm.getLastCallAddr(), "cannot open directory as a file.");
        } else {
            try {
                Files.write(file.toPath(), bytes);
            } catch (IOException e) {
                throw new VmException(Compiler.iceVm.getLastCallAddr(), "Io Exception: " + e.getMessage());
            }
        }
    }
    public void write_text(String path, String text) {
        File file = new File(path);
        if (file.isDirectory()) {
            throw new VmException(Compiler.iceVm.getLastCallAddr(), "cannot open directory as a file.");
        } else {
            try {
                Files.writeString(file.toPath(), text);
            } catch (IOException e) {
                throw new VmException(Compiler.iceVm.getLastCallAddr(), "Io Exception: " + e.getMessage());
            }
        }
    }
}
