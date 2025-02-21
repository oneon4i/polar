class Tasks() = {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Threads' []
    mod func exec(function, args) = {
        return Tasks.reflected.start(function, args)
    }
    mod func async(function, args) = {
        return Tasks.reflected.async(function, args)
    }
}