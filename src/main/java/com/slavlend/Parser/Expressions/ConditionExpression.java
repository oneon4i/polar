package com.slavlend.Parser.Expressions;

import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Operator;
import com.slavlend.Vm.Instructions.VmInstrCondOperator;
import lombok.Getter;
import lombok.Setter;

/*
Эксперешен кондишен - возвращает бул
 */
@Getter
public class ConditionExpression implements Expression {
    // правая и левая стороны
    private final Expression r, l;
    // оператор
    private final Operator o;
    // адресс
    private final Address address = App.parser.address();

    // конструктор
    public ConditionExpression(Expression l, Operator o, Expression r) {
        this.l = l;
        this.r = r;
        this.o = o;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        l.compile();
        r.compile();
        Compiler.code.visitInstr(new VmInstrCondOperator(address.convert(), o));
    }
}
