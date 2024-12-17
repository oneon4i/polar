use 'lib.http'
use 'lib.map'

class HttpServer(port) = {
    func init() = {
        this.javaServer = reflect 'com.slavlend.Libraries.httpserver.httpserver'
    }

    func start() = {
        # запуск сервера #
        @this.javaServer.setup(this.port)
    }

    func route(path, function) = {
        # роутим функцию на путь для сервера #
        @this.javaServer.route(path, function)
    }
}

class HttpResponse(code, body) = {
}