package com.codecool.DAOFactory;

import com.codecool.model.Language;
import com.codecool.model.Mentor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MentorDAO {
    private EntityManager entityManager;

    public MentorDAO(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Mentor getMentor(int id) {
        return entityManager.find(Mentor.class, id );
    }

    @SuppressWarnings("unchecked")
    public List<Mentor> getAllMentors() {
        return entityManager.createQuery( "SELECT m FROM Mentor m" )
                .getResultList();
    }

    public void addMentor(Mentor mentor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mentor.getDetails());
        for (Language language: mentor.getLanguages()) {
            entityManager.persist(language);
        }
        entityManager.persist(mentor);
        transaction.commit();
    }

    public Mentor editMentor(Mentor mentor) {
        return entityManager.merge(mentor);
    }

    public void deleteMentor(Mentor mentor) {
        EntityTransaction transaction = entityManager.getTransaction();
        mentor.getClassRooms().forEach(classRoom -> classRoom.deleteMentor(mentor));
        mentor.getStudents().forEach(student -> student.setPersonalMentor(null));
        transaction.begin();
        entityManager.remove(mentor);
        transaction.commit();
    }

    public void deleteMentor(int id) {
        deleteMentor(getMentor(id));
    }
}
