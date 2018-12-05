package com.codecool;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class App 
{
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(null); // creates a default executor
        server.start();
        server.createContext("/", new MyServer());
        System.out.println("Connected!");
    }
}
