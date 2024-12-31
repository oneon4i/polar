use 'lib.array'

class Str() = {
    mod reflected = reflect 'com.slavlend.Libraries.str'
    mod func replace(str, what, to) = {
        back(Str.reflected.replace(str, what, to))
    }
    mod func split(str, delim) = {
        back(Str.reflected.split(str, delim))
    }
    mod func at(str, index) = {
        back(Str.reflected.at(str, index))
    }
    mod func format(str, arr) = {
        formatted = str
        for (i = 0, i < arr.size()) {
            formatted = Str.replace(formatted, '{' + string(i) + '}', arr.get(i))
            i += 1
        }
        back(formatted)
    }
    mod func upper(str) = {
        back(Str.reflected.upper(str))
    }
    mod func lower(str) = {
        back(Str.reflected.lower(str))
    }
}