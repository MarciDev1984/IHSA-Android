package com.example.ihsastable.data.model;

public class User {
    private int Id;
    private String Username;
    private String FirstName;
    private String LastName;

    public User(){}
    public User(final int Id, final String Username, final String FirstName, final String LastName){
        this.Id = Id;
        this.Username = Username;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    public String getUsername() {
        return this.Username;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public int getId() {
        return this.Id;
    }
}
