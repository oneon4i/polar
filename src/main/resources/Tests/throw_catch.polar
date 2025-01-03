class A(a) = {

}

try {
    error('hello')
} catch (catchable) {
    put('caught: <<' + catchable + '>>')
}