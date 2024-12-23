class Tasks() = {
    mod reflected = reflect 'com.slavlend.Libraries.tasks'
    mod func exec(obj, function, args) = {
        @Tasks.reflected.exec(obj, function, args)
    }
}