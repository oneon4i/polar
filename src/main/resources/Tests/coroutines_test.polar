use 'lib.coroutines'

Coroutines.start_coro_loop()

repeat (10) {
    Coroutines.run(lambda()->{
        put('before')
        raise new CoroWait(5000)
        put('after')
    })
}