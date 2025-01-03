use 'lib.str'
use 'lib.array'

# Output result #
func output_result(val) = {
    put(val)
}

# Тест 1: Проверка работы с числовыми значениями #
func get_num() = {
    back(10)
}

func multiply_by_two(num) = {
    back(num * 2)
}

func add_five(num) = {
    back(num + 5)
}

get_num() |> multiply_by_two() |> add_five() |> output_result()

# Тест 2: Проверка работы с текстовыми значениями #
func get_text() = {
    back('Hello')
}

func append_world(text) = {
    back(text + ' World')
}

func to_uppercase(text) = {
    back(Str.upper(text))
}

get_text() |> append_world() |> to_uppercase() |> output_result()