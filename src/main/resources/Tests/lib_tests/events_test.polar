use 'lib.events'

func test_fn(args) {
    put('on start')
}

onStart = new Event()
onStart.subscribe(test_fn)
onStart.invoke([])