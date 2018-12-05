package com.codecool.DAOFactory;

import com.codecool.model.ClassRoom;
import com.codecool.model.Mentor;
import com.codecool.model.Student;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ClassRoomDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ClassRoomDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void addClassRoom(ClassRoom classRoom) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(classRoom);
        transaction.commit();
    }

    public List<ClassRoom> getAllClasses () {
        Session session = (Session) entityManager.getDelegate();
        List classRoomList = session.createCriteria(ClassRoom.class).list();
        return (List<ClassRoom>) classRoomList;
    }

    public ClassRoom getClassRoom(int id) {
        ClassRoom classRoom = entityManager.find(ClassRoom.class, id);
        System.out.println("size students" + classRoom.getStudentsList().size());
        return classRoom;
    }

    public void deleteClassRoom(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        ClassRoom classRoom = getClassRoom(id);
        classRoom.getStudentsList().forEach(student -> student.setClass(null));
        classRoom.getMentors().forEach(mentor -> mentor.getClassRooms().remove(classRoom));
        transaction.begin();
        entityManager.remove(classRoom);
        transaction.commit();
    }

    public void editClassRoomName(int classRoomId, String name) {
        ClassRoom classRoom = getClassRoom(classRoomId);
        classRoom.setClassName(name);
        editClassroom(classRoom);
    }

    public void addStudent(int classRoomId, int studentID) {
        Student student = new StudentDAO(entityManagerFactory).getStudent(studentID);
        if (student.getClassRoom() != null) {
            student.getClassRoom().getStudentsList().remove(student);
            editClassroom(student.getClassRoom());
        }
        ClassRoom classRoom = getClassRoom(classRoomId);
        classRoom.addStudent(student);

        editClassroom(classRoom);
    }

    public void addMentor(int classRoomId, int mentorId) {
        Mentor mentor = new MentorDAO(entityManagerFactory).getMentor(mentorId);
        ClassRoom classRoom = getClassRoom(classRoomId);
        classRoom.addMentor(mentor);
        editClassroom(classRoom);
    }

    private void editClassroom(ClassRoom classRoom) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(classRoom);
        transaction.commit();
    }





}
