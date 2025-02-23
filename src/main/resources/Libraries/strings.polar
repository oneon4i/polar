class Strings() = {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Strings' []
    mod func replace(str, what, to) = {
        return(Strings.reflected.replace(str, what, to))
    }
    mod func split(str, delim) = {
        return(Strings.reflected.split(str, delim))
    }
    mod func at(str, index) = {
        return(Strings.reflected.at(str, index))
    }
    mod func format(str, arr) = {
        formatted = str
        for (i = 0, i < arr.size()) {
            formatted = Strings.replace(formatted, '{' + string(i) + '}', arr.get(i))
            i += 1
        }
        return(formatted)
    }
    mod func upper(str) = {
        return(Strings.reflected.upper(str))
    }
    mod func lower(str) = {
        return(Strings.reflected.lower(str))
    }
    mod func is_letter(str) = {
        return Strings.reflected.is_letter(str)
    }
    mod func is_number(str) = {
        return Strings.reflected.is_number(str)
    }
}