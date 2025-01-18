#safe {
    raise('exception')
}
handle(e) {
    put('handled: ' + e)
}
class Bear() = {
    func init() = {
        this.food = 100
    }

    func feed() = {
        put('Ммм... Вкусно!')
        this.food += 1
    }
}

bear = new Bear()
put(bear.food)
bear.feed()
put(bear.food)

#
#
for (i = 0, i < 1000000) {
    put('Hello world!')
    i += 1
}#

class Test() = {
    func init() = {
        this.b = 0
    }
}

test = new Test()
test.b += 1
put(test.b)