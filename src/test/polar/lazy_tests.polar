class LazyTest() {
    mod a = b
}
b = 5
e = new LazyTest()
put(e.a)