package com.codecool.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "mentors")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.MERGE)
    @Expose
    private PersonDetails details;

    @OneToMany(fetch = FetchType.EAGER)
    @Expose
    private List<Language> languages;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<ClassRoom> classRooms = new HashSet<>();

    @OneToMany(mappedBy = "personalMentor", cascade = CascadeType.MERGE)
    private List<Student> students = new ArrayList<>();

    public Mentor() {
    }

    public Mentor(PersonDetails details, List<Language> languages) {
        this.details = details;
        this.languages = languages;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
    public void addClass(ClassRoom classRoom) {
        this.classRooms.add(classRoom);
    }

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails personDetails) {
        details = personDetails;
    }

    public Set<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public List<Student> getStudents() {
        return students;
    }
}
