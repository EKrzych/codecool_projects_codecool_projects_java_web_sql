package com.codecool.servlets;

import com.codecool.DAOFactory.MentorDAO;
import com.codecool.model.Language;
import com.codecool.model.Mentor;
import com.codecool.model.PersonDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.ManyToOne;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name="mentors",
        urlPatterns = {"/mentors/*"})
public class MentorServlet extends HttpServlet {
    private static MentorDAO mentorDAO;

    public MentorServlet(MentorDAO mentorDAO) {
        MentorServlet.mentorDAO = mentorDAO;
    }

    public MentorServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();
        String employeeJsonString = getJsonStringMentors(pathInfo);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id = getMentorId(pathInfo);
        mentorDAO.deleteMentor(id);
    }

    private int getMentorId(String pathInfo) {
        return Integer.valueOf(pathInfo.split("/")[1]);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        PersonDetails personDetails = new PersonDetails(name, email, phone);

        Mentor mentor = new Mentor(personDetails, new ArrayList<>());
        mentorDAO.addMentor(mentor);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameters = request.getParameterMap();
        String pathInfo = request.getPathInfo();
        int id = getMentorId(pathInfo);
        Mentor mentor = mentorDAO.getMentor(id);
        changeMentorsData(mentor, parameters);
        mentorDAO.editMentor(mentor);
    }

    private void changeMentorsData(Mentor mentor, Map<String, String[]> updatedValues) {
        for (String key: updatedValues.keySet()) {
            String updatedValue = updatedValues.get(key)[0];
            switch (key) {
                case "name":
                    mentor.getDetails().setName(updatedValue);
                    break;
                case "email":
                    mentor.getDetails().setEmail(updatedValue);
                    break;
                case "phoneNumber":
                    mentor.getDetails().setPhoneNumber(updatedValue);
                    break;
            }
        }
    }

    private String getJsonStringMentors(String pathInfo) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        if (pathInfo == null) {
            return gson.toJson(mentorDAO.getAllMentors());
        } else {
            int id = getMentorId(pathInfo);
            Mentor mentor = mentorDAO.getMentor(id);
            return gson.toJson(mentor);
        }
    }
}
