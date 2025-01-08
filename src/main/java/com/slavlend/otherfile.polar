use 'helloworld.polar'
#
A.a()

func pipe() = {
    back('hello ')
}

func pipe2(first, second) = {
    back(first+second)
}

pipe() |> pipe2(' world!') |> put()
#
a = A.a
class Callback(c) = {
    func get_callback() = {
        put('callback:')
        this.c()
    }
    func ret() = {
        back(this);
    }
}

new Callback(a).get_callback()
a = new Callback(a).ret()