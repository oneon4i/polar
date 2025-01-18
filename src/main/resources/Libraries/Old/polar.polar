class Polar() = {
    mod reflected = reflect 'com.slavlend.Libraries.polar'
    mod func name(o) = {
        back(Polar.reflected.name(o))
    }

    mod func from(name, args) = {
        back(Polar.reflected.from(name, args))
    }
}