package com.codecool;


import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
        server.createContext("/index", new GuestBookHandler());
        server.createContext("/data", new Data());
        server.createContext("/guestbook.js", new Static());
        server.createContext("/main.css", new Static());
        server.setExecutor(null);
        server.start();
        System.out.println("Connected");
    }
}
