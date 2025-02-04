class Random() = {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Random'

    mod func random_number(origin, bound, integer) = {
        back(Random.reflected.randomNumber(origin, bound, integer))
    }

    mod func choice(arr) = {
        rndInBounds = lambda(arr) -> {
            back(Random.random_number(0, arr.size(), true))
        }
        back(arr.get(rndInBounds(arr)))
    }
}
