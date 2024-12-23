class Time() = {
    mod reflected = reflect 'com.slavlend.Libraries.time'
    mod func get_ns() = {
        @back(@Time.reflected.get_ns())
    }
    mod func get_ms() = {
        @back(@Time.reflected.get_ms())
    }
    mod func get_sec() = {
        @back(@Time.reflected.get_sec())
    }
}