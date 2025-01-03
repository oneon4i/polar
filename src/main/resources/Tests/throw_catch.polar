class A(a) = {

}

try {
    throw(new A(5))
} catch (catchable) {
    put('caught: <<' + catchable + '>>')
}