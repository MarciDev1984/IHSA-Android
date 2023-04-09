package com.example.ihsastable.data.model;

import java.util.HashMap;
import java.util.Map;

public class Coach extends User {
    private int SchoolId;

    public Coach(){}

    public Coach(int Id, String Username, String FirstName, String LastName, int schoolId) {
        super(Id, Username, FirstName, LastName);
        SchoolId = schoolId;
    }

    public int getSchoolId() {
        return SchoolId;
    }
}
