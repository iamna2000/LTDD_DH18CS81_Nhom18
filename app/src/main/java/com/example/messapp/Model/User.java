package com.example.messapp.Model;

public class User {
    public User(String username, String id, String imageURL) {
        this.username = username;
        this.id = id;
        this.imageURL = imageURL;
    }

    public User(){

    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private String id;
    private String imageURL;


}
