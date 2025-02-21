use 'lib.tasks'

some_task = Tasks.exec(lambda() -> {
    sleep(1000)
    put('Hello!')
}, [])

put('parallel')
some_task.join()
put('sync')
