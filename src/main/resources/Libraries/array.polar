class OBJArray() = {
    func init() = {
        this.javaArr = reflect 'com.slavlend.Libraries.array'
    }

    func add(elem) = {
        @this.javaArr.add(elem)
    }

    func del(elem) = {
        @this.javaArr.del(elem)
    }

    func get(index) = {
        @back(@this.javaArr.get(index))
    }

    func to_string() = {
        @back(@this.javaArr.toString())
    }

    func size() = {
        @back(@this.javaArr.size())
    }

    func indexOf(elem) = {
        @back(@num(@this.javaArr.indexOf(elem)))
    }

    func contains(elem) = {
        @back(@this.javaArr.contains(elem))
    }

    func dumps() = {
        @back(@this.javaArr.dumps())
    }
}

class Array() = {
    func init() = {
        this.arr = new OBJArray()
    }

    func add(elem) = {
        @this.arr.add(elem)
        # @put('adding element') #
    }

    func del(elem) = {
        @this.arr.del(elem)
        # @put('deleting element') #
    }

    func get(index) = {
        @back(@this.arr.get(index))
    }

    func to_string() = {
        str = '['
        index = 0.0

        while (index < @this.size()) {
            if (index + 1 < @this.size()) {
                if (@this.get(index) is Map) {
                    str = str + @string(@this.get(index).to_string()) + ', '
                    index = index + 1
                } elif (@this.get(index) is Array) {
                    str = str + @string(@this.get(index).to_string()) + ', '
                    index = index + 1
                } else {
                    str = str + @string(@this.get(index)) + ', '
                    index = index + 1
                }
            }
            else {
                if (@this.get(index) is Map) {
                    str = str + @string(@this.get(index).to_string())
                    index = index + 1
                } elif (@this.get(index) is Array) {
                    str = str + @string(@this.get(index).to_string()) + ', '
                    index = index + 1
                }  else {
                    str = str + @string(@this.get(index))
                    index = index + 1
                }
            }
        }

        str = str + ']'
        @back(str)
    }

    func dumps() = {
        @back(@this.arr.dumps())
    }

    func size() = {
        @back(@num(@this.arr.size()))
    }

    func contains(elem) = {
        @back(@this.arr.contains(elem))
    }
}
