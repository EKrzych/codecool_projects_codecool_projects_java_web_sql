package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GuestBookHandler implements HttpHandler {
    DaoGuestInterface daoGuest;
    @Override
    public void handle (HttpExchange httpExchange) throws IOException {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connect();
        this.daoGuest = new DAOguestBook(connection);

        String response = "";
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")) {
            System.out.println("in get");
            GuestBookHandler guestBookHandler = new GuestBookHandler();
            response= guestBookHandler.getFile("index.html");
        }
        if(method.equals("POST")) {
            System.out.println("in post");
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);
            String name = String.valueOf(inputs.get("name"));
            String message = String.valueOf(inputs.get("message"));

            daoGuest.addNewEntry(name, message);
            //After submitting form refresh and display page
            GuestBookHandler guestBookHandler = new GuestBookHandler();
            response= guestBookHandler.getFile("index.html");

        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String getFile(String filepath) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
