package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Logger.PolarLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
Библиотека для работы с HTTP
 */
public class http {
    // отправляем реквест
    public PolarValue send(PolarValue _url, PolarValue type, PolarValue headers) {
        try {
            // URL для HTTP-запроса
            URL url = new URL(_url.asString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Устанавливаем метод
            connection.setRequestMethod(type.asString());


            // хедеры
            connection.setRequestProperty("Content-Type", "application/json");
            map _map = (map) headers.asObject().classValues.get("map").asReflected().o;

            for (PolarValue key : _map.valuesMap.keySet()) {
                connection.setRequestProperty(key.asString(), _map.valuesMap.get(key).asString());
            }

            // Получаем ответ
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // возвращаем
            return new PolarValue(response.toString());
        } catch (Exception e) {
            PolarLogger.exception("Http Exception (Java): " + e.getCause().getMessage(), _url.instantiateAddress);
            return null;
        }
    }
}
