safe {
    raise('exception')
}
handle(e) {
    put('handled: ' + e)
}