class Files() = {
    mod func read(name) = {
        file_writer = reflect 'com.slavlend.Libraries.files'
        @back(@file_writer.read(name))
    }

    mod func write(name, obj) = {
        file_writer = reflect 'com.slavlend.Libraries.files'
        @file_writer.write(name, @string(obj))
    }

    mod func files(path) = {
        file_writer = reflect 'com.slavlend.Libraries.files'
        @back(@file_writer.files(path))
    }
}