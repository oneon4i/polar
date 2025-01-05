use 'lib.time'

start = Time.get_ns()
total = 0
for (i = 0, i < 1000000) {
    total += 1
    i += 1
}
end = Time.get_ns()
put('execution time: ' + string(end-start) ' ns')