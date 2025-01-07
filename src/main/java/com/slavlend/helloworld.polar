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
#
class Texter(text) = {
    func print() = {
        put(this.text)
    }
}

if (0 == 0 and 1 == 1) {
    a = new Texter('hello world!')
    a.print()
}
#
#
for (i = 0, i < 1000000) {
    i = i + 1
    put('hello world')
    put(i)
}
##
i = 0
while (i < 1000000) {
    i = i + 1
    put('Hello world')
    put(i)
}#
#
a = scan('enter your name: ')
put('Hello, ' + a) ##
for (i = 0, i < 1000000) {
    i = i + 1
    put('Hello world!')
    put(i)
}##
arr = reflect 'com.slavlend.Compiler.Libs.Array'
arr.add(1)
arr.add(2)
arr.add(3)
put(arr.get(1))##
arr = [1, 12, 27]
put(arr.get(2))#
map = {'key': 2, 'key2': 9}
put(map.get('key2'))
a = put('123')
put(a)