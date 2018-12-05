package com.codecool;

import com.codecool.DAOFactory.MentorDAO;
import com.codecool.DAOFactory.ClassRoomDAO;
import com.codecool.DAOFactory.StudentDAO;
import com.codecool.servlets.ClassroomServlet;
import com.codecool.servlets.MentorServlet;
import com.codecool.servlets.StudentServlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class App implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("codecoolPU");
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.addServlet("mentors", new MentorServlet(new MentorDAO(factory)));
        servletContext.addServlet("students", new StudentServlet(new StudentDAO(factory)));
        servletContext.addServlet("classrooms", new ClassroomServlet(new ClassRoomDAO(factory)));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
