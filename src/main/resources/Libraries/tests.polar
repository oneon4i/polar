use 'lib.array'

class Tests() = {
    mod func test(array) = {
        put('starting tests')
        put('')

        index = 0
        while (index < array.size()) {
            # getting function #
            _f = array.get(index)
            # putting current test #
            put('test: ' + _f)
            # calling #
            _f()
            # index ++ #
            index += 1
        }

        put('')
        put('tests ended')
    }
}