package com.slavlend.Libraries.httpserver;

import java.util.ArrayList;
import java.util.List;

// реквесты
public class Request {
    // доступные типы запросов
    private final String[] types = {
            "get",
            "post",
            "put",
            "delete"
    };
    // тип запроса
    private final String type;
    // тело запроса
    private final String body;

    // конструктор
    public Request(String type, String body) {
        this.type = type;
        this.body = body;
    }

    // геттеры
    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }
}
