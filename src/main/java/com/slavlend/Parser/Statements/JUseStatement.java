package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.TextExpression;
import com.slavlend.Polar.Logger.PolarLogger;
import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
        try {
            URL url = new File(fullPath).toURI().toURL();
            URL[] urls = new URL[]{url};
            new URLClassLoader(urls);
        } catch (MalformedURLException e) {
            PolarLogger.exception("Cannot Load Jvm Class: " + fullPath + ", e: " + e.getMessage(), address);
        }
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
