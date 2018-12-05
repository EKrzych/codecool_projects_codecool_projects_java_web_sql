package com.codecool;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SimpleHttpServlet extends HttpServlet {
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response) throws IOException {


        response.getWriter().write(new SimpleHttpServlet().getFile("passwordGenerator.html"));
    }

    private String getFile(String filepath) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public void doPost(HttpServletRequest request,
    HttpServletResponse response) throws IOException {
        Integer length = Integer.valueOf(request.getParameter("length"));
        boolean upperCaseIn = request.getParameter("upperCaseIn") != null;
        boolean lowerCaseIn = request.getParameter("lowerCaseIn") != null;
        boolean numbersIn = request.getParameter("numbersIn") != null;

        String password = new PasswordGenerator(upperCaseIn, lowerCaseIn, numbersIn).generatePassword(length);
        String fileContent = new SimpleHttpServlet().getFile("Response.html");
        String responsePassword = fileContent.replaceAll("REPLACE ME", password);
        response.getWriter().write(responsePassword);
    }
}
