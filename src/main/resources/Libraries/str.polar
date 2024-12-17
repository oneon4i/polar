use 'lib.array'

class Str() = {
    mod func replace(str, what, to) = {
        reflectedStr = reflect 'com.slavlend.Libraries.str'
        @back(@reflectedStr.replace(str, what, to))
    }
    mod func split(str, delim) = {
        reflectedStr = reflect 'com.slavlend.Libraries.str'
        @back(@reflectedStr.split(str, delim))
    }
    mod func at(str, index) = {
        reflectedStr = reflect 'com.slavlend.Libraries.str'
        @back(@reflectedStr.at(str, index))
    }
    mod func format(str, arr) = {
        formatted = str
        for (i = 0, i < @arr.size()) {
            formatted = @Str.replace(formatted, '{' + @string(i) + '}', @arr.get(i))
            i += 1
        }
        @back(formatted)
    }
    mod func upper(str) = {
        reflectedStr = reflect 'com.slavlend.Libraries.str'
        @back(@reflectedStr.upper(str))
    }
    mod func lower(str) = {
        reflectedStr = reflect 'com.slavlend.Libraries.str'
        @back(@reflectedStr.lower(str))
    }
}