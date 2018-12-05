package com.codecool;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Login implements HttpHandler {
    DAOUsersInterface daoUser;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        String method = httpExchange.getRequestMethod();
        Headers headers = httpExchange.getRequestHeaders();
        List<String> cookieList = headers.get("Cookie");

        String response = "";

        if(method.equals("GET")) {
            if(cookieList==null) {
                response = new Login().getFile("Login.html");
            } else {
                for(String cookieString : cookieList) {
                    HttpCookie cookie = HttpCookie.parse(cookieString).get(0);
                    if(cookie.getName().equals("sessionId")) {
                        if(cookie.getValue().equals("NONVALIDSESSION")) {
                            response = new Login().getFile("Login.html");
                        }
                        if(cookie.getValue().equals("123")) {
                            response = new Logout().getFile("Logout.html");
                        }
                    }
                }
            }
        }

        if(method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);
            String login = inputs.get("login").toString();
            String password = inputs.get("password").toString();


            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.connect();
            this.daoUser = new DAOUsers(connection);

            if(daoUser.isLoginPasswordValid(login, password)) {
                HttpCookie cookie = new HttpCookie("sessionId", "123");
                httpExchange.getResponseHeaders().set("Set-Cookie", cookie.toString());
                response = new Logout().getFile("Logout.html");
            } else {
                response = new Login().getFile("Login.html");
            }
        }


        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    String getFile(String filepath) {
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