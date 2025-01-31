juse 'com\slavlend\Abc.class'
use 'lib.console'
use 'lib.str'
use 'lib.math'

func hello_world_test() = {
    put('Hello World!')
    put('Test passed')
}

func variable_test() = {
    a = 1
    assert (a == 1)
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
        # put(elem) #
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

func juse_test() = {
    reflected = reflect 'com.slavlend.Abc'
    assert(reflected.testRuntimeJuse() == 'Juse works perfect!')
}

func infinity_test() = {
    # Проверка значений Infinity и NegativeInfinity #
    assert(1 / 0 == Math.Infinity)
    assert(-1 / 0 == Math.NegativeInfinity)
    assert(Math.Infinity > 1000)
    assert(Math.NegativeInfinity < -1000)

    # Операции с Infinity #
    assert(Math.Infinity + 1000 == Math.Infinity)
    assert(Math.Infinity - 1000 == Math.Infinity)
    assert(Math.Infinity * 2 == Math.Infinity)
    assert(Math.Infinity / 2 == Math.Infinity)

    # Операции с NegativeInfinity #
    assert(Math.NegativeInfinity + 1000 == Math.NegativeInfinity)
    assert(Math.NegativeInfinity - 1000 == Math.NegativeInfinity)
    assert(Math.NegativeInfinity * 2 == Math.NegativeInfinity)
    assert(Math.NegativeInfinity / 2 == Math.NegativeInfinity)

    # Взаимодействие Infinity и NegativeInfinity #
    assert(Math.Infinity + Math.NegativeInfinity != Math.NaN)
    assert(Math.NegativeInfinity + Math.Infinity != Math.NaN)
    assert(Math.Infinity - Math.Infinity != Math.Infinity)
    assert(Math.NegativeInfinity - Math.NegativeInfinity != Math.NegativeInfinity)

    # Проверка NaN #
    assert(0 / 0 != 0 / 0)  # NaN не равно самому себе #
    assert((0 / 0 > 0 or 0 / 0 < 0) == false)  # NaN не больше и не меньше любого числа #
    put('Test passed')
}

func lambda_test() = {
    a = lambda(x) -> {
        back('hello world')
    }

    assert(a('name') == 'hello world')
    put('Test passed')
}

func test_repeat() = {
    a = 0
    repeat(15) {
        a += 1
    }
    assert(a == 15)
    put('Test passed')
}

func math_module_test() = {
    put(Math.sin(10) == -0.5440211)
    put(Math.cos(10) == -0.8390715)
}

hello_world_test()
variable_test()
for_statement_continue_test()
if_test()
each_statement()
nested_test()
while_test()
optimization_test()
float_math_test()
len_test()
juse_test()
infinity_test()
lambda_test()
test_repeat()
math_module_test()