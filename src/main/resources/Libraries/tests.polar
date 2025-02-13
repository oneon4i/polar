class Tests() {
    mod func test(function) {
        put('Running test: ' + string(function.getName()))
        function()
        put('Test passed!')
    }
}