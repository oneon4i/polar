class Random() = {
    func init() = {
        this.javaRandom = reflect 'java.util.Random'
    }

    func number(min, max) = {
        back(this.javaRandom.nextInt(min, max))
    }
}


#
random = new Random()
num = random.number(0, 125)
put(num)
#