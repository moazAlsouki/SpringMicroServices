package com.graduation.advert.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "f_uri")
    String furi;


    @ManyToMany(mappedBy = "faculties")
    @JsonIgnore
    Set<Advert> adverts=new HashSet<>();
    public Faculty() {
    }

    public Faculty(String name, String furi) {
        this.name = name;
        this.furi = furi;
    }

    public Faculty(long id, String name, String furi) {
        this.id = id;
        this.name = name;
        this.furi = furi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuri() {
        return furi;
    }

    public void setFuri(String furi) {
        this.furi = furi;
    }

    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }
}
