package com.codecool.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @OneToOne
    @Expose
    private PersonDetails details;

    @ManyToOne
    @Expose
    private Mentor personalMentor = null;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(PersonDetails details, Mentor mentor) {
        this.details = details;
        this.personalMentor = mentor;
    }

    public Mentor getPersonalMentor() {
        return personalMentor;
    }

    public void setPersonalMentor(Mentor personalMentor) {
        this.personalMentor = personalMentor;
    }

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails personDetails) {
        details = personDetails;
    }

    public void setClass(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
