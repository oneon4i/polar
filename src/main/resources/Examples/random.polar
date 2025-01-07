use 'lib.random'

random = new Random()
rnd_value = @random.number(1, 100)

while (1 == 1) {
    @put('Guess the number between 1 and 100 🛸 !')
    input = @scan('Enter number...')
    if (@num(input) == rnd_value) {
        @put('Guessed! 💡')
    } else {
        @put('Wrong number! The answer was... ' + @string(rnd_value) + '! 🚨')
    }
    rnd_value = @random.number(1, 100)
}