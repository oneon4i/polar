class Strings() = {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Str'
    mod func replace(str, what, to) = {
        return(Str.reflected.replace(str, what, to))
    }
    mod func split(str, delim) = {
        return(Str.reflected.split(str, delim))
    }
    mod func at(str, index) = {
        return(Str.reflected.at(str, index))
    }
    mod func format(str, arr) = {
        formatted = str
        for (i = 0, i < arr.size()) {
            formatted = Str.replace(formatted, '{' + string(i) + '}', arr.get(i))
            i += 1
        }
        return(formatted)
    }
    mod func upper(str) = {
        return(Str.reflected.upper(str))
    }
    mod func lower(str) = {
        return(Str.reflected.lower(str))
    }
}