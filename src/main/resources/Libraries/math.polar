class Math() = {
    mod PI = 3.141592
    mod E = 2.718281

    mod func pow(a, b) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.pow(a, b))
    }

    mod func sqrt(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.sqrt(a))
    }

    mod func cbrt(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.cbrt(a))
    }

    mod func sin(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.sin(a))
    }

    mod func cos(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.cos(a))
    }

    mod func tan(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.tan(a))
    }

    mod func asin(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.asin(a))
    }

    mod func acos(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.acos(a))
    }

    mod func atan(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.atan(a))
    }

    mod func atan2(a, b) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.atan2(a, b))
    }

    mod func log(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.log(a))
    }

    mod func log10(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.log10(a))
    }

    mod func exp(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.exp(a))
    }

    mod func to_rad(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.to_rad(a))
    }

    mod func to_deg(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.to_deg(a))
    }

    mod func hypot(a, b) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.hypot(a, b))
    }

    mod func sinh(a, b) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.sinh(a, b))
    }

    mod func cosh(a, b) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.cosh(a, b))
    }

    mod func tanh(a, b) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.tanh(a, b))
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
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.round(a))
    }

    mod func abs(a) = {
        mathReflect = reflect 'com.slavlend.Libraries.math'
        @back(@mathReflect.abs(a))
    }
}