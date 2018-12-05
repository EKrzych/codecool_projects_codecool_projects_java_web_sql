package com.codecool;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PasswordGenerator {
    private ArrayList<String> upperCase = new ArrayList<>();
    private ArrayList<String> lowerCase = new ArrayList<>();;
    private ArrayList<String> numbers = new ArrayList<>();;
    private ArrayList<String> signsForPassword = new ArrayList<>();

    private boolean upperCaseIn;
    private boolean lowerCaseIn;
    private boolean numbersIn;


    PasswordGenerator(boolean upperCaseIn, boolean lowerCaseIn, boolean numbersIn) {
        generateUpperCaseArr();
        generateLowerCaseArr();
        generateNumbers();

        this.upperCaseIn = upperCaseIn;
        this.lowerCaseIn = lowerCaseIn;
        this.numbersIn = numbersIn;

        generateArrayWithAllSigns();
    }

    String generatePassword(Integer length) {
        if(!(upperCaseIn||lowerCaseIn||numbersIn)) {
            return "EMPTY PASSWORD";
        }
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, signsForPassword.size());
            password.append(signsForPassword.get(randomNum));
        }
        return password.toString();
    }

    private void generateLowerCaseArr() {
        int i = 0;
        for(char j = 'a'; j <= 'z'; i++) {
            lowerCase.add(String.valueOf(j));
            j++;
        }
    }

    private void generateUpperCaseArr() {
        int i = 0;
        for(char j = 'A'; j <= 'Z'; i++) {
            upperCase.add(String.valueOf(j));
            j++;
        }
    }

    private void generateNumbers() {
        for(int i = 0; i <= 9; i++) {
            this.numbers.add(String.valueOf(i));
        }
    }

    private void generateArrayWithAllSigns() {
        if(upperCaseIn) {
            this.signsForPassword.addAll(upperCase);
        }
        if(lowerCaseIn) {
            this.signsForPassword.addAll(lowerCase);
        }
        if(numbersIn) {
            this.signsForPassword.addAll(numbers);
        }
    }
}
