use 'lib.telegram'
use 'lib.array'
use 'lib.random'
use 'lib.tasks'
use 'lib.map'

class RandomResult() = {
    mod func result() = {
        rnd = new Random()
        @back('Результат ❄️: ' + @string(@rnd.number(0, 100)))
    }
}

class Handler() = {
    func init() = {
        this.tg = new Telegram()
        this.array = new Array()
        # квизы в реальном времени #
        # выглядят так: {'user_id': {'correct': ...}}#
        this.quizes = {}
        this.new_line = '                                                                 '
    }

    func a(chat_id) = {
        times = 0

        while (times != 5) {
            @this.tg.send_message(chat_id, 'Треда')
            @sleep(500)
            times += 1
        }
    }

    func threads(chat_id) = {
        @Tasks.exec(this, this.a, [chat_id])
    }

    func start_bot() = {
        @put('starting bot')
        @this.tg.start('5742807524:AAH3oScbSFmqLQQgo6r6xRRaF6S6DgAPkl4')
        @this.tg.on_message(this, this.on_message)
        @this.tg.on_quiz_answer(this, this.on_quiz_answer)
    }

    func on_quiz_answer(chat_id, correct) = {
        if (correct == @this.quizes.get(chat_id).get('correct')) {
            @this.tg.send_message(chat_id, 'Правильно! ✅')
        }
        else {
            @this.tg.send_message(chat_id, 'Не правильно! ❌')
        }
    }

    func on_message(chat_id, msg) = {
        if (msg == '/start') {
            if (@this.array.contains(chat_id) == false) {
                @this.array.add(chat_id)
                @this.tg.send_message(chat_id, 'Вы зарегестрированы успешно! 🏗️')
            }
            else {
                @this.tg.send_message(chat_id, 'Вы уже зарегестрировались! 🍧')
            }
        }
        if (msg == '/registered') {
            @this.tg.send_message(chat_id, 'Список зарегестрированных ' + this.new_line + ' пользователей (' + @string(@this.array.size()) + ')! ⏲️' + this.new_line + @this.array.to_string())
        }
        if (msg == '/warning') {
            @warning('Warning test')
            @this.tg.send_message(chat_id, 'Варнинг ⚠️!')
        }
        if (msg == '/random') {
            if (@this.array.contains(chat_id) == true) {
                @this.tg.send_message(chat_id, @RandomResult.result())
            }
            else {
                @this.tg.send_message(chat_id, 'Нельзя получить число не зарегестри-' + this.new_line + 'ровавшись через /start 🐊')
            }
        }
        if (msg == '/threads') {
            if (@this.array.contains(chat_id) == true) {
                @this.threads(chat_id)
            }
            else {
                @this.tg.send_message(chat_id, 'Нельзя получить треды не зарегестри-' + this.new_line + 'ровавшись через /start 🧵')
            }
        }
        if (msg == '/poll') {
            if (@this.array.contains(chat_id) == true) {
                @this.tg.send_quiz(chat_id, 'Тестовый вопрос', 1, ['A', 'B', 'C'])
                @this.quizes.add(chat_id, {'correct': 1})
            }
            else {
                @this.tg.send_message(chat_id, 'Нельзя получить квиз не зарегестри-' + this.new_line + 'ровавшись через /start 🧵')
            }
        }
    }
}

handler = new Handler()
@handler.start_bot()


# тест вложенных функций #
#
class E() = {
    func init() = {
        this.a = 5
    }
}

class D() = {
    func print() = {
        e = new E()
        @back(e)
    }
}

class C() = {
    func init() = {
        this.a = new D()
    }
}

class B() = {
    func b() = {
        @back(new C())
    }
}

class A() = {
    func init() = {
        this.a = new B()
    }
}

a = new A()
@put(@a.a.b().a.print().a)
#
#
a = 5
@put(@string(a) + ' before')
a += 5
@put(@string(a) + ' part 1')
a /= 3
@put(@string(a) + ' part 2')
a *= 3
@put(@string(a) + ' part 3')
a -= 7
@put(@string(a) + ' after')
#
#
class A() = {
    func a() = {
        @back(5)
    }
}

class B() = {
    mod func a() = {
        _a = new A()
        @back(_a)
    }
}

@put(@B.a().a())#
# тест вложенных функций #
#class A() = {
    func a() = {
        func b() = {
            func c() = {
                @back(4)
            }

            @back(@c())
        }

        @put(@b())
    }
}

a = new A()
@a.a()#

# egg - F:\egg lang\language\src\main\java\com\slavlend\test.eg ##
class A(text) = {
    func init() = {
        this.b = '88005553535'
    }

    func a() = {
        @back('11111')
    }
}

a = @new A('testik').a()
@put(new A('testik').b)
#