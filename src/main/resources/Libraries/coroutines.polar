use 'lib.tasks'
use 'lib.reflection'

# yield #
class Yield(value) {}

# coro wait #
class CoroWait(time) {
}

# coroutine #
class Coroutine(fn) {
    func init() {
        this.instructions = []
        this.instructions = this.instructions.of(this.fn.getInstructions())
        this.ip = 0
        this.scope = this.fn.makeScope(__VM__)
    }

    func run() {
        safe {
            for (i = this.ip, i < this.instructions.size()) {
                instr = this.instructions.get(i)
                if (Reflection.class_name(instr) != 'VmInstrRet') {
                    instr.run(__VM__, this.scope)
                }
                i += 1
                this.ip = i
            }
            return true
        } handle (coro_raise) {
            this.ip += 1
            return false
        }
        return true
    }
}

# coroutines buffer #
class Coroutines() {
    mod coro_buffer = []
    mod coro_task = nil
    mod is_running = false

    mod func start_coro_loop() {
        # ... #
        Coroutines.is_running = true
        Coroutines.coro_task = Tasks.exec(lambda() -> {
            while (Coroutines.is_running) {
                for_delete = []
                each(coro, Coroutines.coro_buffer) {
                    _coro = coro.run()
                    put('ret: ' + string(_coro))
                    if (_coro) {
                        for_delete.add(coro)
                    }
                }
                Coroutines.coro_buffer.del_all(for_delete)
            }
        }, [])
    }

    mod func end_coro_loop() {
        Coroutines.is_running = false
    }

    mod func run(fn) {
        # ... #
        coro = new Coroutine(fn)
        Coroutines.coro_buffer.add(
            coro
        )
        return coro
    }
}