class System() {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.System' []
    mod func exit(code) {
        System.reflected.exit(code)
    }
}