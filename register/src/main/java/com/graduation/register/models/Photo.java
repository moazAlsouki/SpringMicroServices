package com.graduation.register.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stdId")
    int std_id;
    @Column(name = "description")
    String description;
    @Column(name = "picture")
    String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stdId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Student student;

    public Photo() {
    }

    public Photo(int std_id, String description, String picture) {
        this.std_id = std_id;
        this.description = description;
        this.picture = picture;
    }

    public Photo(Long id, int std_id, String description, String picture) {
        this.id = id;
        this.std_id = std_id;
        this.description = description;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
