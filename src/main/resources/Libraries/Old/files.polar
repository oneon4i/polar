class Files() = {
    mod reflected = reflect 'com.slavlend.Libraries.files'
    mod func read(name) = {
        back(Files.reflected.read(name))
    }

    mod func write(name, obj) = {
        Files.reflected.write(name, string(obj))
    }

    mod func files(path) = {
        back(Files.reflected.get_files(path))
    }
}