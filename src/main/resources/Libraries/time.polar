class Time() {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Time' []
    mod func now_mills() {
        return Time.reflected.now_mills()
    }
    mod func now_mills_offset(val) {
        return Time.reflected.now_mills_offset(val)
    }
    mod func now_seconds() {
        return Time.reflected.now_seconds()
    }
    mod func from_unix(unix) {
        return Time.reflected.from_unix(unix)
    }
    mod func to_unix(unix) {
        return Time.reflected.to_unix(unix)
    }
    mod func is_greater(left, right) {
        return Time.reflected.is_greater(left, right)
    }
    mod func is_less(left, right) {
        return Time.reflected.is_less(left, right)
    }
}