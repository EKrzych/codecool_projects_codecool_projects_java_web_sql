package com.codecool;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String [] args) {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
            server.createContext("/login", new Login());
            server.createContext("/logout", new Logout());
            server.createContext("/main.css", new Static());
            server.setExecutor(null);
            server.start();
            System.out.println("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}