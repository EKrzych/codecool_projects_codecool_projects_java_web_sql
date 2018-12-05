package com.codecool.faniUMLa.Queststore.model.users;

public class Mentor extends User{

    public Mentor(int accountID, String firstName, String lastName, String email, String phoneNumber) {
        super(accountID, firstName, lastName, email, phoneNumber);
        super.setAccess(UserAccess.MENTOR);
    }

    public String getMentorDetails() {
        return "ID_User: " + getIdUser() + ", " + getFirstName() + " " + getLastName() + ", "+ getEmail() + ", Phone: " + getPhoneNumber();
    }
}
