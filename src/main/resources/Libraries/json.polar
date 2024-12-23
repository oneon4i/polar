class Json() = {
    mod reflected = reflect 'com.slavlend.Libraries.json'
    mod func read(text) = {
        @back(@Json.reflected.read(text))
    }

    mod func dumps(data) = {
        @back(@Json.reflected.dumps(data))
    }
}