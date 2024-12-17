class HttpRequestType() = {
    mod GET = 'GET'
    mod POST = 'POST'
    mod PUT = 'PUT'
    mod DELETE = 'DELETE'
}

class Http() = {
    mod func send(url, type, headers) = {
        http_reflected = reflect 'com.slavlend.Libraries.http'
        @back(@http_reflected.send(url, type, headers))
    }
}