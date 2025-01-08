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
a()