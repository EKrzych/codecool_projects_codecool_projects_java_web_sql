package com.codecool.servlets;

import com.codecool.DAOFactory.ClassRoomDAO;
import com.codecool.DAOFactory.MentorDAO;
import com.codecool.DAOFactory.StudentDAO;
import com.codecool.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@WebServlet(name="students",
        urlPatterns = {"/students/*"})
public class StudentServlet extends HttpServlet {

    private static StudentDAO studentDAO;

    public StudentServlet(StudentDAO dao) {
        studentDAO = dao;
    }

    public StudentServlet() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
//        populateDb(studentDAO);

        if (pathInfo != null) {
            int studentId = parseStudentId(pathInfo);
            studentDAO.open();
            String jsonStudent = getJSONStudent(studentDAO.getStudent(studentId)).toString();

            response.setHeader("Content-Type", "application/json");

            response.getWriter().write(jsonStudent);

        } else {
            response.setHeader("Content-Type", "application/json");

            response.getWriter().write(getJSONStudents());

        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
//        populateDb(studentDAO);
        if (pathInfo == null) {
            Student student = createStudent(request);

            studentDAO.open();
            studentDAO.addStudent(student);
            studentDAO.close();

            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(getJSONStudent(student));
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            int studentId = parseStudentId(pathInfo);

            studentDAO.open();
            Student student = studentDAO.getStudent(studentId);
            studentDAO.updateStudent(student, getUpdatedValues(request));
            studentDAO.close();

            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(getJSONStudent(student));

        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            int studentId = parseStudentId(pathInfo);
            studentDAO.open();
            studentDAO.deleteStudent(studentId);
            studentDAO.close();
        }
    }

    private Student createStudent(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        int personalMentorId = Integer.parseInt(request.getParameter("personalMentor"));
//        int classroomId = Integer.parseInt(request.getParameter("classroom"));
        EntityManagerFactory factory = studentDAO.getEntityManagerFactory();

        Mentor mentor = new MentorDAO(factory).getMentor(personalMentorId);
//        ClassRoom classRoom = new ClassRoomDAO(factory).getClassRoom(classroomId);
        PersonDetails userDetails = new PersonDetails(name, email, phoneNumber);
        Student student = new Student(userDetails, mentor);
//        student.setClass(classRoom);
        return student;
    }



    private Map<String, String> getUpdatedValues(HttpServletRequest request) {
        Map<String, String> updatedValues = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            updatedValues.put(paramName, request.getParameter(paramName));
        }
        return updatedValues;
    }

    private int parseStudentId(String pathInfo) {
        String[] splittedPathInfo = pathInfo.split("/");
        List<String> clearSplittedPathInfo = clearSplittedPathInfo(splittedPathInfo);
        return Integer.parseInt(clearSplittedPathInfo.get(0));
    }

    private List<String> clearSplittedPathInfo(String[] pathInfo) {
        List<String> clearPathInfo = new ArrayList<>();
        for (String pathElement: pathInfo) {
            if (!pathElement.equals("")) {
                clearPathInfo.add(pathElement);
            }
        }
        return clearPathInfo;
    }

    private String getJSONStudents() {
        studentDAO.open();
        List<Student> studentList = studentDAO.getAllStudents();
        studentDAO.close();
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(studentList);
    }

    private String getJSONStudent(Student student) {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(student);
    }
}
