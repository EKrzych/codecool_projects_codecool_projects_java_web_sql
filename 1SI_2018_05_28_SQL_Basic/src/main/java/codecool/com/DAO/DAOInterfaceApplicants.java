package codecool.com.DAO;

import codecool.com.person.Applicant;
import codecool.com.person.Person;

import java.util.ArrayList;

public interface DAOInterfaceApplicants extends DAOQueryInterface {
    ArrayList<String> getCarolNumber();
    ArrayList<String> getPhoneByEmail();
    void createNewApplicantSchaffarzyk();
    void updateJemima();
    ArrayList<String> getPhoneNumber();
    void deleteStudent();
    ArrayList<Person> getAllSearchBY(String string);
    void addNewApplicant(Applicant applicant);
    void updateApplicant(String collumn, String word, String id);
    ArrayList<Person> getIfExistApplicant(String id);
    ArrayList<String> getAllApplicantsFirstAndLast();
}
