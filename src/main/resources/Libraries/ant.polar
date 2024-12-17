class Ant() = {
    mod func name(o) = {
        antReflect = reflect 'com.slavlend.Libraries.ant'

        @back(@antReflect.name(o))
    }

    mod func from(name, args) = {
        antReflect = reflect 'com.slavlend.Libraries.ant'

        @back(@antReflect.from(name, args))
    }
}