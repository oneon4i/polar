class Telegram() = {
    func init() = {
        this.javaGram = reflect 'com.slavlend.Libraries.telegram'
    }

    func start(token) = {
        @this.javaGram.start(token)
    }

    func on_message(obj, link) = {
        @this.javaGram.on_message(obj, link)
    }

    func on_quiz_answer(obj, link) = {
        @this.javaGram.on_quiz_answer(obj, link)
    }

    func send_message(chat_id, text) = {
        @this.javaGram.send_message(chat_id, text)
    }

    func send_message_markup(chat_id, text, markup) = {
        @this.javaGram.send_message(chat_id, text)
    }

    func send_quiz(chat_id, question, right, list) = {
        @this.javaGram.send_quiz(chat_id, question, right, list)
    }
}

#
tg = new Telegram()
@tg.start('5742807524:AAH3oScbSFmqLQQgo6r6xRRaF6S6DgAPkl4')
#