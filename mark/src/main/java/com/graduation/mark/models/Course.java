package com.graduation.mark.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    int year;
    @Column(name = "season")
    int season;
    @Column(name = "name")
    String name;
    @Column(name = "part")
    String part;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Exam> exams=new HashSet<>();

    public Course() {
    }

    public Course(int year, int season, String name, String part) {
        this.year = year;
        this.season = season;
        this.name = name;
        this.part = part;
    }

    public Course(Long id, int year, int season, String name, String part) {
        this.id = id;
        this.year = year;
        this.season = season;
        this.name = name;
        this.part = part;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

}

