package com.movie.app.model;

import javax.persistence.*;

@Entity
@Table(name="movies",schema = "heroku_bf512bfaa10427f")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="year")
    private Integer year;

    @Column(name="catogery")
    private String catogery;

    @Column(name="language")
    private String language;

    @Column(name="actor")
    private String actor;

    @Column(name="description")
    private String description;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCatogery() {
        return catogery;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
