#                       #
# Пример                #
# Генератор примеров 🐍 #
use 'lib.random'

# пример #
class ProblemData(num, num2, operator, result) {
    func get_result() {
        back(this.result)
    }

    func to_string() {
        back(this.num + ' ' + this.operator + ' ' + this.num2 + ' = ' + this.result)
    }
}

# генератор примеров #
class ProblemGenerator() {
    mod func generate() {
        func eval(a, b, o) {
            match(o) {
                case ('+') {
                    back(a + b)
                }
                case ('-') {
                    back(a - b)
                }
                case ('*') {
                    back(a * b)
                }
                case ('/') {
                    back(a / b)
                }
            }
        }
        a = Random.random_number(0, 100, true)
        b = Random.random_number(1, 100, true)
        operators = ['+', '-', '*', '/']
        o = Random.choice(operators)
        back(new ProblemData(a, b, o, eval(a, b, o)))
    }
}

put(ProblemGenerator.generate().to_string())