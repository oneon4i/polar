package com.slavlend.Parser.Expressions;


import com.slavlend.App;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Operator;
import com.slavlend.Vm.Instructions.VmInstrArith;
import lombok.Getter;

/*
Арифмитическое выражение
 */
@Getter
public class ArithmeticExpression implements Expression {
    // правая и левая стороны
    private final Expression r, l;
    // оператор
    private final Operator operator;
    // адресс
    private final Address address = App.parser.address();

    @Override
    public Address address() {
        return address;
    }

    @Override
    public void compile() {
        r.compile();
        l.compile();
        Compiler.code.visitInstr(new VmInstrArith(address.convert(), operator));
    }

    // конструктор
    public ArithmeticExpression(Expression r, Operator operator, Expression l) {
        this.r = r; this.operator = operator; this.l = l;
    }

}
