package com.codecool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsers implements DAOUsersInterface {
    Connection connection;

    public DAOUsers(Connection connection) {
        this.connection = connection;
    }

    public boolean isLoginPasswordValid(String userLogin, String password) {
        ResultSet rs = null;
        String match = "SELECT name_surname FROM usersCC WHERE user_name=? AND usersCC.password=?";
        PreparedStatement query;
        try {
            query = connection.prepareStatement(match);
            query.setString(1, userLogin);
            query.setString(2, password);
            rs = query.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        boolean nonEmptySet = false;
        try {
            nonEmptySet = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nonEmptySet;
    }
}
