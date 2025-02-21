class Time() {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Time' []
    mod func now_milliseconds() {
        return Time.reflected.now_milliseconds()
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
}