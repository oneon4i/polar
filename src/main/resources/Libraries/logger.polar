class Logger() = {
    mod func log(msg) = {
        put('📗: ' + msg)
    }

    mod func warn(msg) = {
        put('📙: ' + msg)
    }

    mod func error(msg) = {
        put('📕: ' + msg)
    }
}