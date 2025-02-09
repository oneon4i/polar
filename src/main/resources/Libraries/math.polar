class Math() = {
    mod Pi = 3.1415926535897
    mod Infinity = 1/0
    mod NegativeInfinity = -1/0
    mod NaN = 0/0
    mod E = 2.718
    mod Ln2 = 0.6931
    mod Ln10 = 2.3026
    mod Log2E = 1.4427
    mod Log10E = 0.4343
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Math'
    mod func sin(a) = {
        return(Math.reflected.sin(a))
    }
    mod func cos(a) = {
        return(Math.reflected.cos(a))
    }
    mod func tan(a) = {
        return(Math.reflected.tan(a))
    }
    mod func atan(a) = {
        return(Math.reflected.atan(a))
    }
}