package com.slavlend.Libraries;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.Stack.Classes;
import com.slavlend.Env.PolarEnv;
import com.slavlend.Polar.PolarValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/*
Библиотека на работу с файлами
 */
public class files {
    // конструктор
    public files() {

    }

    // чтение файла по имени
    public PolarValue read(PolarValue name) {
        try {
            // открываем файл
            File file = new File(name.asString());
            StringBuilder text = new StringBuilder();
            Scanner sc = new Scanner(file);

            // читаем файл
            while (sc.hasNextLine()) {
                text.append(sc.nextLine());
            }

            // возвращаем содержимое файла
            return new PolarValue(text.toString());
        } catch (Exception e) {
            // ошибка
            PolarEnv.Crash("Cannot Read: " + name + ", (Java) " + e.getMessage(), name.instantiateAddress);

            return null;
        }
    }

    // запись в файл по имени данных(data)
    public void write(PolarValue name, PolarValue data) {
        try {
            // пишем в файл
            BufferedWriter writer = new BufferedWriter(new FileWriter(name.asString()));
            writer.write(data.asString());

            // закрываем буффер
            writer.close();
        } catch (Exception e) {
            // ошибка
            PolarEnv.Crash("Cannot Write: " + name + ", (Java): " + e.getMessage(), name.instantiateAddress);
        }
    }

    // список файлов в определенной директории
    public PolarValue files(PolarValue path) {
        // файлы
        try {
            Stream<Path> stream = Files.list(Paths.get(path.asString()));
            PolarObject _array = new PolarObject(Classes.getInstance().getClass("Array"), new ArrayList<>());
            _array.init();

            for (Path _path : stream.toList()) {
                ArrayList<PolarValue> params = new ArrayList<PolarValue>();
                params.add(new PolarValue(_path.getFileName().toString()));
                _array.classValues.get("add").asFunc().call(null, params);
            }

            return new PolarValue(_array);
        } catch (IOException e) {
            PolarEnv.Crash(
                    "Io Exception (Java): " + e.getCause().getMessage(), path.instantiateAddress
            );
            return null;
        }
    }
}
