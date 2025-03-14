class Map() = {
    func init() = {
        this.map = reflect 'com.slavlend.Libraries.map'
    }

    func add(key, value) = {
        if (this.map.contains(key) == false) {
            this.map.add(key, value)
        }
    }

    func del(key) = {
        if (this.map.contains(key) == true) {
            this.map.del(key)
            # put('adding element to map') #
        }
    }

    func contains(key) = {
        back(this.map.contains(key))
    }

    func get(key) = {
        back(this.map.get(key))
    }

    func to_string() = {
        back(this.map.to_string())
    }

    func dumps() = {
        back(this.map.dumps())
    }

    func size() = {
        back(this.map.size())
    }

    func keys() = {
        back(this.map.keys())
    }
}
