package com.graduation.register.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pass_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reg_id")
    int reg_id;
    @Column(name = "course_name")
    String courseName;
    @Column(name = "result")
    int result;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reg_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Register register;

    public Course() {
    }

    public Course(int reg_id, String courseName, int result) {
        this.reg_id = reg_id;
        this.courseName = courseName;
        this.result = result;
    }

    public Course(Long id, int reg_id, String courseName, int result) {
        this.id = id;
        this.reg_id = reg_id;
        this.courseName = courseName;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}
