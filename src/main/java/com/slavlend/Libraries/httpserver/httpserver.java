package com.slavlend.Libraries.httpserver;

import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Colors;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Libraries.json;
import com.slavlend.Parser.Statements.FunctionStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Библиотека предоставляющая возможность создания
http-сервера. Представляет собой враппер вокруг
просто http-сервера.
 */
public class httpserver {
    // хэндлер запросов
    private final Map<String, FunctionStatement> requestHandlerList = new HashMap<>();
    // json
    private final json json = new json();

    // помещение хэндлера
    public void route(PolarValue path, PolarValue func) {
        if (func.isFunc()) {
            requestHandlerList.put(path.asString(), func.asFunc());
        }
        else {
            PolarLogger.Crash("Http Server Error. Not A Function: ", func.instantiateAddress);
        }
    }

    // функция старта сервера
    public void setup(int port) {
        // поток
        Thread thread = new Thread() {
            @Override
            public void run() {
                // стэк
                Storage.getInstance().threadInit();
                // сервер
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    // выводим сообщение о старте сервера
                    System.out.println(Colors.ANSI_GREEN + "╭──────────────────────────╮");
                    System.out.println("│ 🛸 Server Started at: " + String.valueOf(serverSocket.getInetAddress().toString()) + ":" + String.valueOf(serverSocket.getLocalPort()));
                    System.out.println("╰──────────────────────────╯" + Colors.ANSI_RESET);
                    // запускаем его
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        startServer(clientSocket);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // запускаем поток
        thread.start();
    }

    private void startServer(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream())) {

            String requestLine = reader.readLine();
            if (requestLine == null) return;

            String[] parts = requestLine.split(" ");
            String method = parts[0];
            String path = parts[1];

            Request request = new Request(path, method);
            FunctionStatement handler = requestHandlerList.get(path);

            // Чтение заголовков
            Map<String, String> headers = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] headerParts = line.split(": ", 2);
                if (headerParts.length == 2) {
                    headers.put(headerParts[0], headerParts[1]);
                }
            }

            // Чтение тела (если есть)
            StringBuilder bodyBuilder = new StringBuilder();
            if ("POST".equalsIgnoreCase(method)) {
                String contentLengthHeader = headers.get("Content-Length");
                if (contentLengthHeader != null) {
                    int contentLength = Integer.parseInt(contentLengthHeader);
                    char[] bodyChars = new char[contentLength];
                    reader.read(bodyChars, 0, contentLength);
                    bodyBuilder.append(bodyChars);
                }
            }

            if (handler != null) {
                ArrayList<PolarValue> parameters = new ArrayList<>();
                String _json = "{'method': '" + method + "'," + "'request':'" + bodyBuilder + "'}";
                parameters.add(json.read(new PolarValue(_json)));
                PolarObject _response = handler.call(null, parameters).asObject();
                Response response = new Response(_response.classValues.get("code").asNumber().intValue(), _response.classValues.get("body").asString());
                writer.println("HTTP/1.1 " + response.getCode() + " OK");
                writer.println("Content-Type: text/plain");
                writer.println("Content-Length: " + response.getBody().length());
                writer.println();
                writer.println(response.getBody());
            } else {
                writer.println("HTTP/1.1 404 Not Found");
                writer.println("Content-Length: 0");
                writer.println();
            }

            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
