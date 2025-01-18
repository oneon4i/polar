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
#
class Test() = {
    func init() = {
        this.b = 0
    }
}

test = new Test()
test.b += 1
put(test.b)#
#
name = scan('Введите имя.')
age = scan('Введите возраст.')

for (i = 0, i < 100) {
	put('Отправляю данные в ЦРУ...')
	i += 1
}

a = {'name': name, 'age': age}
put('Данные в ЦРУ: (name:' + a.get('name') + ', age:' + a.get('age') + ')')
put('Сейчас за вами выедут копы...')

isRed = false
for (i = 0, i < 10000000) {
	if (isRed) {
		put('🟥🟦')
		isRed = false
	} else {
		put('🟦🟥')
		isRed = true
	}
	i += 1
}#
#
a = [1,2,3]
each(b,a) {
    put('iter')
    put(b)
}#