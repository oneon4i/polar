package com.slavlend.Libraries.httpserver;

import lombok.Getter;

// ответ
@Getter
public class Response {
    // код ответа
    private final int code;
    // тело ответа
    private final String body;

    // конструктор
    public Response(int code, String body) {
        this.code = code;
        this.body = body;
    }
}
