class Time() = {
    mod func get_ns() = {
        reflected = reflect 'com.slavlend.Libraries.time'
        @back(@reflected.get_ns())
    }
    mod func get_ms() = {
        reflected = reflect 'com.slavlend.Libraries.time'
        @back(@reflected.get_ms())
    }
    mod func get_sec() = {
        reflected = reflect 'com.slavlend.Libraries.time'
        @back(@reflected.get_sec())
    }
}