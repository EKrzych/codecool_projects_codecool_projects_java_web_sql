package com.codecool.DAOFactory;

import com.codecool.model.*;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

public class StudentDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public StudentDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }


    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }


    public void addStudent(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student.getDetails());
        entityManager.persist(student);
        transaction.commit();
    }


    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }


    public List<Student> getAllStudents() {
        Session session = (Session) entityManager.getDelegate();
        List studentsList = session.createCriteria(Student.class).list();
        return (List<Student>) studentsList;
    }
    public void updateStudent(Student student, Map<String, String> updatedValues) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(changeStudentData(student, updatedValues));
        transaction.commit();
    }


    public void deleteStudent(int id) {
        Student student = getStudent(id);
        PersonDetails personDetails = student.getDetails();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(personDetails);
        entityManager.remove(student);
        transaction.commit();
    }


    private Student changeStudentData(Student student, Map<String, String> updatedValues) {
        for (String key: updatedValues.keySet()) {
            String updatedValue = updatedValues.get(key);
            if (key.equals("name")) {
                student.getDetails().setName(updatedValue);
            } else if (key.equals("email")) {
                student.getDetails().setEmail(updatedValue);
            } else if (key.equals("phoneNumber")) {
                student.getDetails().setPhoneNumber(updatedValue);
            } else if (key.equals("personalMentor")) {
                int mentorId = Integer.parseInt(updatedValue);
                Mentor mentor = new MentorDAO(entityManagerFactory).getMentor(mentorId);
                student.setPersonalMentor(mentor);
            } else if (key.equals("classroom")) {
                int classRoomId = Integer.parseInt(updatedValue);
                ClassRoom classRoom = new ClassRoomDAO(entityManagerFactory).getClassRoom(classRoomId);
                student.setClass(classRoom);
            }
        }
        return student;
    }


    public void close() {
        entityManager.close();
    }


    public void open() {
        entityManager = entityManagerFactory.createEntityManager();
    }
}
