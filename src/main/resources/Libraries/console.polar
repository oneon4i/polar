class Console() = {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.Console'
    mod func cls() = {
        Console.reflected.clear()
    }
}