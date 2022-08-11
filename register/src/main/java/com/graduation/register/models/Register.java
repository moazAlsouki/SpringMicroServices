package com.graduation.register.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registers")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "std_id")
    int stdId;
    @Column(name = "year")
    int year;
    @Column(name = "exam_number")
    int examNumber;
    @Column(name = "section_id")
    int sectionId;
    @Column(name = "suspend")
    int suspend;
    @Column(name = "note")
    String  note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "std_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Student student;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Section section;
    @OneToMany(mappedBy = "register", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Course> courses=new HashSet<>();


    public Register() {
    }

    public Register(int std_id, int year, int examNumber, int suspend, String note) {
        this.stdId = std_id;
        this.year = year;
        this.examNumber = examNumber;
        this.suspend = suspend;
        this.note = note;
    }

    public Register(Long id, int std_id, int year, int examNumber, int suspend, String note) {
        this.id = id;
        this.stdId = std_id;
        this.year = year;
        this.examNumber = examNumber;
        this.suspend = suspend;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(int examNumber) {
        this.examNumber = examNumber;
    }

    public int getSuspend() {
        return suspend;
    }

    public void setSuspend(int suspend) {
        this.suspend = suspend;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

}
