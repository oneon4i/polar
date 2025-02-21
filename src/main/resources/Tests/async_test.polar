use 'lib.tasks'

repeat(5) {
    Tasks.async(lambda() -> {
        sleep(1000)
        return 123
    }, [])
}