use 'lib.tests'

a = 1
b = 2

# global vars tests #
func test_a() = {
    assert(a+b == 3)
    @put('Test passed')
}

# locals vars tests #
func test_b() = {
    c = 12
    d = 5
    assert(c+d == 17)
    @put('Test passed')
}

# class vars tests #
class ForTest(a) = {
    func init() = {
        this.b = 3
    }
}

func test_c() = {
    test = new ForTest(4)
    assert(test.b+test.a == 7)
    @put('Test passed')
}

# multi test #
func test_d() = {
    test = new ForTest(4)
    c = 10
    assert(test.a+test.b+c == 17)
    @put('Test passed')
}

@Tests.test([test_a, test_b, test_c, test_d])
