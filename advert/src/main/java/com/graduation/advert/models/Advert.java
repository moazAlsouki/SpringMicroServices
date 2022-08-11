package com.graduation.advert.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "publisher")
    String publisher;
    @Column(name = "time")
    Date time;
    @Column(name = "expired")
    Date expired;
    @Column(name = "title")
    String title;
    @Column(name = "subject")
    String subject;

    @OneToMany(mappedBy = "advert", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Photo> photos=new HashSet<>();


    @ManyToMany
    @JoinTable(name="adv_fac",joinColumns = @JoinColumn(name="adv_id"),inverseJoinColumns =  @JoinColumn(name="fac_id"))
    @JsonIgnore
    Set<Faculty> faculties=new HashSet<>();


    public Advert() {
    }

    public Advert(String publisher, Date time, Date expired, String title, String subject) {
        this.publisher = publisher;
        this.time = time;
        this.expired = expired;
        this.title = title;
        this.subject = subject;
    }

    public Advert(long id, String publisher, Date time, Date expired, String title, String subject) {
        this.id = id;
        this.publisher = publisher;
        this.time = time;
        this.expired = expired;
        this.title = title;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAdvert() {
        return title;
    }

    public void setAdvert(String advert) {
        this.title = advert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "publisher='" + publisher + '\'' +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
