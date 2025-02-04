# Класс #
class Members() = {
    func init() = {
        this.e = 5
    }

    func val() = {
        back(get_val())
    }

    func get_val() = {
        back(e)
    }
}

put(new Members().val())