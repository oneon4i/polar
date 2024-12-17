class Json() = {
    mod func read(text) = {
        json = reflect 'com.slavlend.Libraries.json'

        @back(@json.read(text))
    }

    mod func dumps(data) = {
        json = reflect 'com.slavlend.Libraries.json'
        @back(@json.dumps(data))
    }
}