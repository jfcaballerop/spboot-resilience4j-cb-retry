package com.circuitbreaker.demo.svc2.model;

public class Movie {

    public Long id;
    public String title;
    public String dir;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDir() {
        return dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public Movie(Long id, String title, String dir) {
        this.id = id;
        this.title = title;
        this.dir = dir;
    }
    public Movie() {
    }

    
}
