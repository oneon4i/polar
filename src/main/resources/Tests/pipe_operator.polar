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

func add_five(num, need) = {
    back(num * need)
}

get_num() |> multiply_by_two() |> add_five(5) |> output_result()

# Тест 2: Проверка работы с текстовыми значениями #
func get_text() = {
    back('Hello')
}

func append_world(text, adds) = {
    back(text + adds)
}

func to_uppercase(text) = {
    back(Str.upper(text))
}

get_text() |> append_world(' World') |> to_uppercase() |> output_result()