class Files() {
    mod reflected = reflect 'com.slavlend.Compiler.Libs.PolarFiles' []
    mod func read_bytes(path) {
        return Files.reflected.read_bytes(path)
    }
    mod func read_text(path) {
        return Files.reflected.read_text(path)
    }
    mod func write_bytes(path, bytes) {
        Files.reflected.write_bytes(path, bytes)
    }
    mod func write_text(path, text) {
        Files.reflected.write_text(path, text)
    }
}