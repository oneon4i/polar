use 'lib.tasks'

repeat(5) {
    val = Tasks.async(lambda() -> {
        sleep(1000)
        return 123
    }, [])
    put(val.get())
}