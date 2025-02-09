class Random() = {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Random' []

    mod func number(origin, bound, integer) = {
        return(Random.reflected.randomNumber(origin, bound, integer))
    }

    mod func choice(arr) = {
        rndInBounds = lambda(arr) -> {
            return(Random.number(0, arr.size(), true))
        }
        return(arr.get(rndInBounds(arr)))
    }
}
