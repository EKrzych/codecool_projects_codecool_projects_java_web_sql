package codecool.com;

import codecool.com.person.Person;

import java.util.ArrayList;

public class View {
    String [] menu = {"Print mentor's first and last name",
                        "Print mentors from Miscolc",
                        "Print Carol's full name and phone number",
                        "Print full name of student with email snding like: @adipiscingenimmi.edu",
                        "Create new aplicant Markus Schaffarzyk",
                        "Change Jemina Foreman phone number",
                        "Delete students with email ending @mauriseu.net",
                        "Look for fraze",
                        "Add mentor or applicant",
                        "Update mentor or applicant"};

    public void printMenu() {
        for(int i = 0; i<this.menu.length; i++) {
            System.out.println("(" + (i+1) + ") " + this.menu[i]);
        }
        System.out.println("(0) Exit program" );
    }

    public void printListPeople(ArrayList<Person> someList) {
        for(Person p : someList) {
            System.out.println(p);
        }
    }

    public void printListStr(ArrayList<String> someList) {
        for(String s : someList) {
            System.out.println(s);
        }
    }

    public void printLine(String message) {
        System.out.println(message);
    }
}
