class HttpRequestType() = {
    mod GET = 'GET'
    mod POST = 'POST'
    mod PUT = 'PUT'
    mod DELETE = 'DELETE'
}

class Http() = {
    mod reflected = reflect 'com.slavlend.Libraries.http'
    mod func send(url, type, headers) = {
        @back(@Http.reflected.send(url, type, headers))
    }
}