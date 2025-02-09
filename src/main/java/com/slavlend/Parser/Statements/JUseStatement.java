package com.slavlend.Parser.Statements;

import com.slavlend.App;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Expressions.TextExpression;
import com.slavlend.PolarClassLoader;
import com.slavlend.PolarLogger;
import com.slavlend.Vm.VmJvmClasses;
import lombok.Getter;
import lombok.Setter;

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
    @Setter
    private Address address = App.parser.address();

    // конструктор
    public JUseStatement(TextExpression libName) {
        // имя
        this.libName = libName;
    }

    @Override
    public Statement copy() {
        return new JUseStatement(libName);
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        // зачем нам компилить juse стэйтмент,
        // просто грузим в JVM
        String fullPath = App.parser.getEnvironmentPath() + "\\" + libName.getData();
        VmJvmClasses.define(
                new PolarClassLoader(fullPath).defineJvmClass(libName.getData().replace("\\", "."))
        );
    }
}
