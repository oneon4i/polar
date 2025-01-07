#func test(c) = {
    back('greetings' + c)
}

a = 'Hello world!'
put(test(a))#
#
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
#
func fac(n) = {
    if (n == 1) {
        back(1)
    } else {
        back(fac(n - 1)*n)
    }
}

put(fac(2))
##
i = 0
while (i < 1000000) {
    i = i + 1
}
put(i)#

class Texter(text) = {
    func print() = {
        put(this.text)
    }
}

if (0 == 0 and 1 == 1) {
    a = new Texter('hello world!')
    a.print()
}
