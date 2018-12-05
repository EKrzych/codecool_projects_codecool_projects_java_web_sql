package codecool.com.DAO;

import codecool.com.person.Applicant;
import codecool.com.person.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicantsDAO implements DAOInterfaceApplicants {
    private final String SEARCH_BY_CAROL = "SELECT first_name || ' ' || last_name AS full_name, phone_number \n" +
                                        "FROM applicants\n" +
                                        "WHERE first_name='Carol';";

    private final String SEARCH_BY_EMAIL = "SELECT first_name || ' ' || last_name AS full_name, phone_number \n" +
                                    "FROM applicants\n" +
                                    "WHERE email LIKE '%@adipiscingenimmi.edu';";

    private final String CREATE_MARKUS_SCHAFFARZYK = "INSERT INTO applicants (first_name, last_name, phone_number, email, application_code)\n" +
                            "VALUES('Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com', 54823);";

    private final String UPDATE_JEMIMA = "UPDATE applicants\n" +
                            "SET phone_number='003670/223-7459'\n" +
                            "WHERE first_name='Jemima' AND last_name='Foreman';";

    private final String JEMIMA_PHONE = "SELECT phone_number\n" +
                                "FROM applicants\n" +
                                "WHERE first_name='Jemima' AND last_name='Foreman';";

    private final String DELETE_MARISEU = "DELETE FROM applicants\n" +
            "WHERE email LIKE '%@mauriseu.net'; ";

    private final String ALL_APPLICANTS_QUERY = "SELECT id, first_name, last_name FROM applicants;";

    public ArrayList<String> getCarolNumber() {
        return getFullNameAndPhoneSearchBY(SEARCH_BY_CAROL);
    }

    public ArrayList<String> getPhoneByEmail() {
        return getFullNameAndPhoneSearchBY(SEARCH_BY_EMAIL);
    }

    private ArrayList<String> getFullNameAndPhoneSearchBY(String string) {
        ArrayList<String> applicantsName = new ArrayList<>();
        ResultSet rs = executeQuery(string);
        StringBuilder sb;
        try {
            while (rs.next()) {
                sb = new StringBuilder();
                String fullName = rs.getString("full_name");
                String phoneNumber = rs.getString("phone_number");
                sb.append(fullName + " " + phoneNumber + " ");
                applicantsName.add(sb.toString());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicantsName;
    }

    public void createNewApplicantSchaffarzyk() {
        updateQuery(CREATE_MARKUS_SCHAFFARZYK);
    }

    public void updateJemima() {
        updateQuery(UPDATE_JEMIMA);
    }

    public void deleteStudent() {
        updateQuery(DELETE_MARISEU);
    }

    public ArrayList<Person> getAllSearchBY(String string) {
        ArrayList<Person> fullData = new ArrayList<>();
        ResultSet rs = executeQuery(prepareQuery(string));
        try {
            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                Integer application_code = Integer.valueOf(rs.getString("application_code"));
                fullData.add(new Applicant(first_name, last_name,phone_number,email,application_code));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullData;
    }

    private String prepareQuery(String word) {
        String query = "SELECT * \n" +
                "FROM applicants \n" +
                "WHERE (SELECT first_name||last_name||phone_number||email||application_code) LIKE "
                + "'%" + word + "%'";
        return query;
    }

    public void addNewApplicant(Applicant applicant) {
        String applicantToAdd =  prepareAddAplicantQuery(applicant);
        updateQuery(applicantToAdd);
    }

    private String prepareAddAplicantQuery(Applicant applicant) {
        String query = "INSERT INTO applicants (first_name, last_name, phone_number, email, application_code)\n" +
                "VALUES('" + applicant.getFirst_name() +"','"
                + applicant.getLast_name()+"', '"
                + applicant.getPhone_number() + "','"
                + applicant.getEmail() + "',"
                + applicant.getApplication_code()  + ");";
        return query;
    }

    public ArrayList<String> getAllApplicantsFirstAndLast() {
        ArrayList<String> applicantIDName = new ArrayList<>();
        ResultSet rs = executeQuery(ALL_APPLICANTS_QUERY);
        StringBuilder sb;
        try {
            while (rs.next()) {
                sb = new StringBuilder();
                String id = rs.getString("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                sb.append(id + ". " + firstName + " " + lastName + " ");
                applicantIDName.add(sb.toString());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicantIDName;
    }


    public ArrayList<String> getPhoneNumber(){
        ArrayList<String> phoneNumbers = new ArrayList<>();
        ResultSet rs = executeQuery(JEMIMA_PHONE);
        StringBuilder sb;
        try {
            while (rs.next()) {
                sb = new StringBuilder();
                String phone_number = rs.getString("phone_number");
                sb.append(phone_number);
                phoneNumbers.add(sb.toString());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneNumbers;
    }

    public ArrayList<Person> getIfExistApplicant(String id) {
        return getApplicantByID(id);
    }

    public void updateApplicant(String collumn, String word, String id) {
        String applicantToUpdate = prepareUpdatedApplicant(collumn, word, id);
        updateQuery(applicantToUpdate);
    }

    private String prepareUpdatedApplicant(String column, String word, String id) {
        return "UPDATE applicants SET " + column + "='" +  word + "'\n" +
                "WHERE id='" + id + "';";
    }

    public ArrayList<Person> getApplicantByID(String string) {
        ArrayList<Person> fullData = new ArrayList<>();
        ResultSet rs = executeQuery(prepareQueryID(string));

        try {
            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                Integer application_code = Integer.valueOf(rs.getString("application_code"));
                fullData.add(new Applicant(first_name, last_name, phone_number, email, application_code));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullData;
    }

    private String prepareQueryID(String word) {
        String query = "SELECT * \n" +
                "FROM applicants \n" +
                "WHERE id=" + word + " ;";
        return query;
    }

}
