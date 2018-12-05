package com.codecool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DAOguestBook  implements DaoGuestInterface {
    Connection connection;

    public DAOguestBook(Connection connection) {
        this.connection = connection;
    }

    public void addNewEntry(String name, String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String newEntry = "INSERT INTO entries(nick_name, user_message, message_date) VALUES (?, ?, ?)";
        PreparedStatement query;
        try {
            query = connection.prepareStatement(newEntry);
            query.setString(1, name);
            query.setString(2, message);
            query.setString(3, dateFormat.format(date));
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEntries() {
        ResultSet rs = null;
        String allEntries = "SELECT * FROM entries";
        PreparedStatement query;
        try {
            query = connection.prepareStatement(allEntries);
            rs = query.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
