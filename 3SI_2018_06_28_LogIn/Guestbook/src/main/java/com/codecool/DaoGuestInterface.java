package com.codecool;

import java.sql.ResultSet;

public interface DaoGuestInterface {
    void addNewEntry(String name, String message);
    ResultSet getEntries();
}
