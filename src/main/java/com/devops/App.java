package com.devops;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        server.createContext("/", App::handleRequest);

        server.setExecutor(null);
        server.start();

        System.out.println("Java application started on port 8081");
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        String response = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>AWS 3-Tier DevOps Project</title>
                </head>
                <body>
                    <h1>AWS 3-Tier Java Application</h1>
                    <p>Application is running successfully!</p>
                    <p>Built with Maven.</p>
                </body>
                </html>
                """;

        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, response.length());

        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.close();
    }
}
