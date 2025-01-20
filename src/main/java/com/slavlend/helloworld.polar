use 'lib.console'
use 'lib.str'

func hello_world_test() = {
    put('Hello World!')
    put('Test passed')
}

func variable_test() = {
    a = 1
    assert (a == 1)
    put('Test passed')
}

func convert_test() = {
    a = 1
    b = true
    c = '1'

    assert(Polar.name(string(1)) == 'string')
    assert(Polar.name(string(b)) == 'string')
    assert(Polar.name(num(c)) == 'num')

    put('Test passed')
}

func for_statement_continue_test() = {
    value = 0

    for (i = 0, i < 5) {
        if (i == 4) {
            i += 1
            next
        }

        value += 1
        i += 1
    }

    assert(value == 4)
    put('Test passed')
}

func if_test() = {
    a = 5
    b = 4 + 1

    if ((a == b)) {
        put('Test passed')
    }
    else {
        assert(1 == 2)
    }
}

func each_statement() = {
    lst = [5, 3, 1]
    each (elem, lst) {
        put(elem)
        next
    }
    put('Test passed')
}

func nested_test() = {
    total_sum = 0

    lst = [1, 2, 3]
    each (outer, lst) {  # внешний цикл each #
        if (outer == 2) {
            next  # пропустить outer = 2 #
        }
        for (i = 0, i < 5) { # вложенный цикл for #
            if (i == 4) {
                break  # завершить for, когда i = 4 #
            }
            inner_sum = 0
            number = 0
            while (number < i) { # вложенный цикл while #
                number = number + 1
                inner_sum = inner_sum + number
            }

            total_sum = total_sum + inner_sum
            i += 1
        }
    }

    assert(total_sum == 20)
    put('Test passed')
}

func while_test() = {
    index = 0

    while (index < 5) {
        index += 1
    }

    assert(index == 5)
    put('Test passed')
}

func optimization_test() = {
    a = 2 + 2 * 2

    assert(a == 6)
    put('Test passed')
}

func float_math_test() = {
    a = 5.1122324
    a = a + 1.78

    assert(a == 6.892232)
    put('Test passed')
}

class A()  = {
    func init() = {
        put('Test passed')
    }
}

func len_test() = {
    length = len('aaaa')
    assert(length == 4)
    put('Test passed')
}

func test_format() = {
    name = 'Вячеслав'
    age = 13
    formatted = Str.format('Привет, {0.0}, возрастом в {1.0} лет', [name, string(age)])
    assert(formatted == 'Привет, Вячеслав, возрастом в 13.0 лет')
    put('Test passed')
}

hello_world_test()
variable_test()
convert_test()
for_statement_continue_test()
each_statement()
nested_test()
while_test()
optimization_test()
float_math_test()
len_test()