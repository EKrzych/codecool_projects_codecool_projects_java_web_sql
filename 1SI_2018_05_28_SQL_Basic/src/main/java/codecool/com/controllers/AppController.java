package codecool.com.controllers;

import codecool.com.DAO.*;
import codecool.com.View;
import codecool.com.person.Applicant;
import codecool.com.person.Mentor;
import org.postgresql.util.PSQLException;

import java.util.Scanner;

public class AppController {
    Scanner sc = new Scanner(System.in);
    View view = new View();
    Mentor mentor;
    Applicant applicant;

    DAOInterfaceMentors mentorDAO;
    DAOInterfaceApplicants applicantsDAO;
    ResetDAO resetDAO;

    public AppController() {
        this.mentorDAO = new MentorsDAO();
        this.applicantsDAO = new ApplicantsDAO();
        this.resetDAO = new ResetDAO();
        resetDAO.resetAndLoad();

    }

    public void handleMenu() {
        String answer;
        String word;
        do {
            view.printMenu();
            answer = askUser("Choose option: ");
            switch(answer) {
                case "1":
                    view.printListStr(mentorDAO.getAllMentorsFirstAndLast());
                    break;
                case "2":
                    view.printListStr(mentorDAO.getNickMiscolc());
                    break;
                case "3":
                    view.printListStr(applicantsDAO.getCarolNumber());
                    break;
                case "4":
                    view.printListStr(applicantsDAO.getPhoneByEmail());
                    break;
                case "5":
                    applicantsDAO.createNewApplicantSchaffarzyk();
                    view.printListPeople(applicantsDAO.getAllSearchBY("54823"));
                    break;
                case "6":
                    applicantsDAO.updateJemima();
                    view.printListStr(applicantsDAO.getPhoneNumber());
                    break;
                case "7":
                    applicantsDAO.deleteStudent();
                    break;
                case "8":
                    word = askUser("Fraze you are looking for: ");
                    view.printListPeople(mentorDAO.getAllSearchBY(word));
                    view.printListPeople(applicantsDAO.getAllSearchBY(word));
                    break;
                case "9":
                    try {
                        handleAddPerson();
                    } catch (NumberFormatException e) {
                        view.printLine("Expected number got string.");
                    }
                    break;
                case "10":
                    handleUpdatePerson();
                    break;
                case "0":
                    System.out.println("Bye Bye");
                    break;
                default:
                    System.out.println("There's no such option");
            }

        } while (!answer.equalsIgnoreCase("0"));
    }

    private String askUser(String message) {
        view.printLine(message);
        return sc.nextLine();
    }

    private void handleAddPerson() {
        String word;
        word = askUser("Add mentor or applicant (m/a)?: ");
        switch (word) {
            case "m":
            case "M":
                mentor = buildMentor();
                mentorDAO.addNewMentor(mentor);
                break;
            case "a":
            case "A":
                applicant = buildApplicant();
                applicantsDAO.addNewApplicant(applicant);
        }
    }

    private void handleUpdatePerson() {
        String word;
        word = askUser("Update mentor or applicant (m/a)?: ");
        switch(word) {
            case "m": case "M":
                view.printListStr(mentorDAO.getAllMentorsFirstAndLast());
                String idMentor = askUser("Which mentor would you like to update?(number): ");
                if(checkIfExistMentor(idMentor)) {
                    handleMentorUpdate(idMentor);
                } else {
                    view.printLine("There's no such id");
                }
                break;
            case "a": case "A":
                view.printListStr(applicantsDAO.getAllApplicantsFirstAndLast());
                String idApplicant = askUser("Which applicant would you like to update?(number): ");
                if(checkIfExistApplicant(idApplicant)) {
                    handleApplicantUpdate(idApplicant);
                } else {
                    view.printLine("There's no such id");
                }
        }
    }

    private Boolean checkIfExistMentor(String id) {
        return mentorDAO.getIfExistMentor(id).size() > 0;
    }

    private Boolean checkIfExistApplicant(String id) {
        return applicantsDAO.getIfExistApplicant(id).size() > 0;
    }

    private Mentor buildMentor() {
        String first_name = askUser("Provide first name:");
        String last_name = askUser("Provide last name:");
        String phone_number = askUser("Provide phone number:");
        String email = askUser("Provide first email:");
        String nick_name = askUser("Provide nick name:");
        String city = askUser("Provide  city:");
        Integer favourite_number = Integer.valueOf(askUser("Provide favourite number:"));
        return new Mentor(first_name, last_name, phone_number, email, nick_name, city, favourite_number);
    }

    private Applicant buildApplicant() {
        String first_name = askUser("Provide first name:");
        String last_name = askUser("Provide lasr name:");
        String phone_number = askUser("Provide phone number:");
        String email = askUser("Provide first email:");
        Integer application_code = Integer.valueOf(askUser("Provide applicant code"));
        return new Applicant (first_name, last_name, phone_number, email, application_code);
    }

    private void handleMentorUpdate(String id) {
        String word;
        String answer;
        String[] column = {"first_name", "last_name", "phone_number", "email", "nick_name", "city", "favourite_number"};
        for(int i = 0; i<column.length; i++) {
            answer = askUser("Would you like to update " + column[i] + "(y/n)?: ");
            if(answer.equalsIgnoreCase("y")) {
                word = askUser("Provide " + column[i] + ": ");
                mentorDAO.updateMentor(column[i], word, id);
            }
        }
    }

    private void handleApplicantUpdate(String id) {
        String word;
        String answer;
        String[] column = {"first_name", "last_name", "phone_number", "email", "application_code"};
        for(int i = 0; i<column.length; i++) {
            answer = askUser("Would you like to update " + column[i] + "(y/n)?: ");
            if(answer.equalsIgnoreCase("y")) {
                word = askUser("Provide " + column[i] + ": ");
                applicantsDAO.updateApplicant(column[i], word, id);
            }
        }
    }

}
