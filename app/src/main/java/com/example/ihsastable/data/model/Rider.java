package com.example.ihsastable.data.model;

import java.util.ArrayList;
import java.util.List;

public class Rider extends User {
    private int RiderId;
    private int Points;
    private int Position;
    private double AveragePointsPerRide;
    // private int ManagedBy; //Uses CoachId
    // private int Class;  // Uses Class id
    private int PlaysFor; // Uses SchoolId

    public Rider() {
    }

    public Rider(int Id, String Username, String FirstName, String LastName, int riderId, int points, int position, double averagePointsPerRide, int playsFor) {
        super(Id, Username, FirstName, LastName);
        RiderId = riderId;
        Points = points;
        Position = position;
        AveragePointsPerRide = averagePointsPerRide;
        PlaysFor = playsFor;
    }

    public int getRiderId() {
        return RiderId;
    }

    public int getPoints() {
        return Points;
    }

    public int getPosition() {
        return Position;
    }

    public double getAveragePointsPerRide() {
        return AveragePointsPerRide;
    }


    public int getPlaysFor() {
        return PlaysFor;
    }
}
