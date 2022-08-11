package com.graduation.register.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Register> registers=new HashSet<>();
    public Section() {
    }

    public Section(String name) {
        this.name = name;
    }

    public Section(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
