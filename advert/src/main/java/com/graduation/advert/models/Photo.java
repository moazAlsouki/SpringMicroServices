package com.graduation.advert.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "adv_id")
    int advertId;
    @Column(name = "description")
    String description;
    @Column(name = "picture")
    String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adv_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Advert advert;

    public Photo() {
    }

    public Photo(int advertId, String description, String picture) {
        this.advertId = advertId;
        this.description = description;
        this.picture = picture;
    }

    public Photo(Long id, int advertId, String description, String picture) {
        this.id = id;
        this.advertId = advertId;
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

    public int getAdvertId() {
        return advertId;
    }

    public void setAdvertId(int advertId) {
        this.advertId = advertId;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
