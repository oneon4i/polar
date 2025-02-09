use 'lib.strings'
use 'lib.array'

# Output result #
func output_result(val) = {
    put(val)
}

# Тест 1: Проверка работы с числовыми значениями #
func get_num() = {
    return(10)
}

func multiply_by_two(num) = {
    return(num * 2)
}

func add_five(num, need) = {
    return(num * need)
}

get_num() |> multiply_by_two() |> add_five(5) |> output_result()

# Тест 2: Проверка работы с текстовыми значениями #
func get_text() = {
    return('Hello')
}

func append_world(text, adds) = {
    return(text + adds)
}

func to_uppercase(text) = {
    return(Strings.upper(text))
}

get_text() |> append_world(' World') |> to_uppercase() |> output_result()

# Тест 3: в выражениях #

func get_first_num(exp) = {
    return(5*exp)
}

func get_second_num(exp) = {
    return(3*exp)
}

get_first_num(2) |> get_second_num() |> put()
put(get_first_num(2) |> get_second_num())