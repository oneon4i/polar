#                       #
# Пример                #
# Генератор примеров 🐍 #
use 'lib.random'

# пример #
class ProblemData(num, num2, operator, result) {
    func get_result() {
        return(this.result)
    }

    func to_string() {
        return(this.num + ' ' + this.operator + ' ' + this.num2 + ' = ' + this.result)
    }
}

# генератор примеров #
class ProblemGenerator() {
    mod func generate() {
        func eval(a, b, o) {
            match(o) {
                case ('+') {
                    return(a + b)
                }
                case ('-') {
                    return(a - b)
                }
                case ('*') {
                    return(a * b)
                }
                case ('/') {
                    return(a / b)
                }
            }
        }
        a = Random.number(0, 100, true)
        b = Random.number(1, 100, true)
        operators = ['+', '-', '*', '/']
        o = Random.choice(operators)
        return(new ProblemData(a, b, o, eval(a, b, o)))
    }
}

put(ProblemGenerator.generate().to_string())