package codecool.com.DAO;

import codecool.com.person.Mentor;
import codecool.com.person.Person;

import java.util.ArrayList;

public interface DAOInterfaceMentors extends DAOQueryInterface {
    ArrayList<String> getAllMentorsFirstAndLast();
    ArrayList<String> getNickMiscolc();
    ArrayList<Person> getAllSearchBY(String word);
    void addNewMentor(Mentor mentor);
    void updateMentor(String collumn, String word, String id);
    ArrayList<Person> getIfExistMentor(String id);

}
