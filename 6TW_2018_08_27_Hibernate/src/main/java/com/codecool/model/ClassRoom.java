package com.codecool.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "classrooms")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Expose
    private String className;

    @ManyToMany(mappedBy = "classRooms", cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Expose
    private Set<Mentor> mentors = new HashSet<>();

    @OneToMany(mappedBy = "classRoom", cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Expose
    private List<Student> studentsList = new ArrayList<>();

    public ClassRoom() {}

    public ClassRoom(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public Set<Mentor> getMentors() {
        return mentors;
    }

    public int getId() {
        return id;
    }

    public void addStudent(Student student) {

        studentsList.add(student);
        student.setClass(this);
    }

    public void addMentor(Mentor mentor) {
        this.mentors.add(mentor);
        mentor.addClass(this);
    }

    public void deleteMentor(Mentor mentor) {
        this.mentors.remove(mentor);
    }
}
