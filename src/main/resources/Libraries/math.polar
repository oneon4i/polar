class Math() = {
    mod PI = 3.141592
    mod E = 2.718281
    mod reflected = reflect 'com.slavlend.Libraries.math'
    
    mod func pow(a, b) = {
        @back(@Math.reflected.pow(a, b))
    }

    mod func sqrt(a) = {
        @back(@Math.reflected.sqrt(a))
    }

    mod func cbrt(a) = {
        @back(@Math.reflected.cbrt(a))
    }

    mod func sin(a) = {
        @back(@Math.reflected.sin(a))
    }

    mod func cos(a) = {
        @back(@Math.reflected.cos(a))
    }

    mod func tan(a) = {
        @back(@Math.reflected.tan(a))
    }

    mod func asin(a) = {
        @back(@Math.reflected.asin(a))
    }

    mod func acos(a) = {
        @back(@Math.reflected.acos(a))
    }

    mod func atan(a) = {
        @back(@Math.reflected.atan(a))
    }

    mod func atan2(a, b) = {
        @back(@Math.reflected.atan2(a, b))
    }

    mod func log(a) = {
        @back(@Math.reflected.log(a))
    }

    mod func log10(a) = {
        @back(@Math.reflected.log10(a))
    }

    mod func exp(a) = {
        @back(@Math.reflected.exp(a))
    }

    mod func to_rad(a) = {
        @back(@Math.reflected.to_rad(a))
    }

    mod func to_deg(a) = {
        @back(@Math.reflected.to_deg(a))
    }

    mod func hypot(a, b) = {
        @back(@Math.reflected.hypot(a, b))
    }

    mod func sinh(a, b) = {
        @back(@Math.reflected.sinh(a, b))
    }

    mod func cosh(a, b) = {
        @back(@Math.reflected.cosh(a, b))
    }

    mod func tanh(a, b) = {
        @back(@Math.reflected.tanh(a, b))
    }

    mod func factorial(a) = {
        i = 1
        r = 1

        while (i <= a) {
            r *= i
            i += 1
        }

        @back(r)
    }

    mod func round(a) = {
        @back(@Math.reflected.round(a))
    }

    mod func abs(a) = {
        @back(@Math.reflected.abs(a))
    }
}