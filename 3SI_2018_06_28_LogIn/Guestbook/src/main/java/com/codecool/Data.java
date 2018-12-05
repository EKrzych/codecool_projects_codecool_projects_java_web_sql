package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Data implements HttpHandler {
    DaoGuestInterface daoGuest;
    @Override
    public void handle(HttpExchange httpExchange) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connect();
        this.daoGuest = new DAOguestBook(connection);

        String response = "";
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")) {
            System.out.println("In data get");

            ResultSet result = daoGuest.getEntries();
            JSONArray json = new JSONArray();

            try {
                while (result.next()) {
                    try {

                        String name = result.getString("nick_name");
                        String message = result.getString("user_message");
                        String message_date = result.getString("message_date");

                        JSONObject obj = new JSONObject();
                        obj.put("nick_name", name);
                        obj.put("user_message", message);
                        obj.put("message_date", message_date);
                        json.put(obj);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                response = json.toString();

                try {
                    httpExchange.sendResponseHeaders(200, response.length());
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
