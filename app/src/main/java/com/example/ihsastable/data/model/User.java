package com.example.ihsastable.data.model;

public class User {
    private String Username;
    private String FirstName;
    private String LastName;

    public User(){}
    public User(String Username, String FirstName, String LastName){
        this.Username = Username;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    public String getUsername() {
        return Username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
}
