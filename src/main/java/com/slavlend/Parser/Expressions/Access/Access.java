package com.slavlend.Parser.Expressions.Access;

import com.slavlend.Polar.PolarValue;

/*
Абстракция доступа (акссеса)
 */
public interface Access {
    abstract PolarValue access(PolarValue previous);
    abstract void setNext(Access access);
    abstract void setLast(Access access);
    abstract boolean hasNext();
    abstract Access getNext();
    abstract void compile();
}
