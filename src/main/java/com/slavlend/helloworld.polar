#func test(c) = {
    back('greetings' + c)
}

a = 'Hello world!'
put(test(a))#

func plus(a, b) = {
    if (a > 5) {
        put('first cond')
    }
    elif (a == 5) {
        put('second cond')
    }
    else {
        put('a need be > 5')
    }
}

put(plus(5, 2))

#
METHOD (A,B){
    load(A)
    load(B)
    arith()
    ret
}

push(5)
push(2)
CALL[put](call[plus]())
#