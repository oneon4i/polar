package com.slavlend.Libraries.httpserver;

// ответ
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

    // геттеры
    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }
}
