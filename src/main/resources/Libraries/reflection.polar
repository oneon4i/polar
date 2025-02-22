class Reflection() {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.PolarReflection' []

    mod func class_name(o) {
        return Reflection.reflected.class_name(o)
    }
}