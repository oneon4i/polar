package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Vm.Instructions.VmInstrPush;
import lombok.Getter;
import lombok.Setter;

/*
Текстовый экспрешенн - возвращает строку
 */
@Getter
public class TextExpression implements Expression {
    // данные
    private final String data;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        Compiler.code.visitInstr(new VmInstrPush(address.convert(), data));
    }

    public TextExpression(String data) {
        this.data = data;
    }
}
