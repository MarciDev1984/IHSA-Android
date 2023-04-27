package com.example.ihsastable.data.model;

import java.util.ArrayList;
import java.util.List;

public class Rider extends User {
    private int RiderId;
    private int Points;
    private int Position;
    private int AvgPointsPerRide;

    // private int ManagedBy; //Uses CoachId
    // private int Class;  // Uses Class id
    private int PlaysFor; // Uses SchoolId

    public Rider() {
    }

    public Rider(int Id, String Username, String FirstName, String LastName, int RiderId, int Points, int Position, int AvgPointsPerRide, int PlaysFor) {
        super(Id, Username, FirstName, LastName);
        this.RiderId = RiderId;
        this.Points = Points;
        this.Position = Position;
        this.AvgPointsPerRide = AvgPointsPerRide;
        this.PlaysFor = PlaysFor;
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

    public int getAveragePointsPerRide() {
        return AvgPointsPerRide;
    }


    public int getPlaysFor() {
        return PlaysFor;
    }
}
