package com.example.ihsastable.data.model;

public class User {
    private int Id;
    private String Username;
    private String FirstName;
    private String LastName;

    public User(){}
    public User(int Id, String Username, String FirstName, String LastName){
        this.Id = Id;
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

    public int getId() {
        return Id;
    }
}
