package com.example.ihsastable.data.model;

import java.util.HashMap;
import java.util.Map;

public class Coach extends User {
    private int SchoolId;

    public Coach(){}

    public Coach(final int Id, final String Username, final String FirstName, final String LastName, final int schoolId) {
        super(Id, Username, FirstName, LastName);
        this.SchoolId = schoolId;
    }

    public int getSchoolId() {
        return this.SchoolId;
    }
}
