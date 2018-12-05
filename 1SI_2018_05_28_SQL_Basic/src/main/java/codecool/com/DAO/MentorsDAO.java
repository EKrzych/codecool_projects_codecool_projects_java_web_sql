package codecool.com.DAO;

import codecool.com.person.Mentor;
import codecool.com.person.Person;

import java.sql.*;
import java.util.ArrayList;

public class MentorsDAO implements DAOInterfaceMentors {
    private String allMentorsQuerry = "SELECT id, first_name, last_name FROM mentors;";
    private String mentorsMicsolcNick = "SELECT nick_name \n" +
                                        "FROM mentors \n" +
                                        "WHERE city='Miskolc';";


    public ArrayList<String> getAllMentorsFirstAndLast() {
        ArrayList<String> mentorsIDName = new ArrayList<>();
        ResultSet rs = executeQuery(allMentorsQuerry);
        StringBuilder sb;
        try {
            while (rs.next()) {
                sb = new StringBuilder();
                String id = rs.getString("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                sb.append(id + ". " + firstName + " " + lastName + " ");
                mentorsIDName.add(sb.toString());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorsIDName;
    }

    public ArrayList<String> getNickMiscolc() {
        ArrayList<String> mentorsName = new ArrayList<>();
        ResultSet rs = executeQuery(mentorsMicsolcNick);
        StringBuilder sb;
        try {
            while (rs.next()) {
                sb = new StringBuilder();
                String nick = rs.getString("nick_name");
                sb.append(nick);
                mentorsName.add(sb.toString());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorsName;
    }

    public ArrayList<Person> getAllSearchBY(String string) {
        ArrayList<Person> fullData = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = prepareQuery(string).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String nick_name = rs.getString("nick_name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                String city = rs.getString("city");
                Integer favourite_number = Integer.valueOf(rs.getString("favourite_number"));
                fullData.add(new Mentor(first_name, last_name, phone_number, email, nick_name, city, favourite_number));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullData;
    }

    private PreparedStatement prepareQuery(String word) {
        Connection c = connect();
        PreparedStatement saveStatement = null;
        String query = "SELECT * \n" +
                "FROM mentors \n" +
                "WHERE (SELECT first_name||last_name||nick_name||phone_number||email||city||favourite_number) LIKE ?";
        try {
            saveStatement = c.prepareStatement(query);
            saveStatement.setString( 1, "%"+word+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saveStatement;
    }

    public void addNewMentor(Mentor mentor) {
        String mentorToAdd =  prepareAddMentorQuery(mentor);
        updateQuery(mentorToAdd);
    }

    private String prepareAddMentorQuery(Mentor mentor) {
        String query = "INSERT INTO mentors (first_name, last_name, phone_number, email, nick_name, city, favourite_number)\n" +
                "VALUES('" + mentor.getFirst_name() +"','"
                + mentor.getLast_name()+"', '"
                + mentor.getPhone_number() + "','"
                + mentor.getEmail() + "','"
                + mentor.getNick_name() + "','"
                + mentor.getCity() + "',"
                +mentor.getFavourite_number() + ");";
        return query;
    }

    public ArrayList<Person> getIfExistMentor(String id) {
        return getMentorByID(id);
    }

    private ArrayList<Person> getMentorByID(String string) {
        ArrayList<Person> fullData = new ArrayList<>();
        ResultSet rs = executeQuery(prepareQueryID(string));

        try {
            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String nick_name = rs.getString("nick_name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                String city = rs.getString("city");
                Integer favourite_number = Integer.valueOf(rs.getString("favourite_number"));
                fullData.add(new Mentor(first_name, last_name, phone_number, email, nick_name, city, favourite_number));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullData;
    }

    private String prepareQueryID(String word) {
        String query = "SELECT * \n" +
                "FROM mentors \n" +
                "WHERE id=" + word + " ;";
        return query;
    }

    public void updateMentor(String collumn, String word, String id) {
        String mentorToUpdate = prepareUpdatedMentor(collumn, word, id);
        updateQuery(mentorToUpdate);
    }

    private String prepareUpdatedMentor(String collumn, String word, String id) {
        return "UPDATE mentors SET " + collumn + "='" +  word + "'\n" +
                "WHERE id='" + id + "';";
    }
}
