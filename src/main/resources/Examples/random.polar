use 'lib.random'

rnd_value = Random.number(1, 100, true)

while (1 == 1) {
    put('Guess the number between 1 and 100 🛸 !')
    input = scan('Enter number...')
    if (number(input) == rnd_value) {
        put('Guessed! 💡')
    } else {
        put('Wrong number! The answer was... ' + string(rnd_value) + '! 🚨')
    }
    rnd_value = Random.number(1, 100, true)
}