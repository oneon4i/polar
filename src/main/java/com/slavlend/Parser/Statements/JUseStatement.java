package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.TextExpression;
import com.slavlend.Polar.JvmClasses;
import com.slavlend.Polar.Logger.PolarLogger;
import com.slavlend.Polar.PolarClassLoader;
import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/*
ДжЮз Стэйтмент - использование джава модуля. Подгружает его.
 */
@Getter
public class JUseStatement implements Statement{
    // имя библиотеки
    private final TextExpression libName;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public void optimize() {
        // ...
    }

    // конструктор
    public JUseStatement(TextExpression libName) {
        // имя
        this.libName = libName;
    }

    @Override
    public void execute() {
        // оптимизурем
        optimize();
        // подгружаем класс в JVM
        String fullPath = App.parser.getEnvironmentPath() + "\\" + libName.getData();
        // загрузка
        PolarClassLoader urlClassLoader = new PolarClassLoader(fullPath);
        JvmClasses.define(urlClassLoader.defineJvmClass(
                libName.getData().replace("\\", ".")
        ));
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Statement copy() {
        return new JUseStatement(libName);
    }

    @Override
    public Address address() {
        return address;
    }
}
