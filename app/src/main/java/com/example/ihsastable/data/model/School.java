package com.example.ihsastable.data.model;

public class School {
    private int Id;
    private String SchoolName;
    private String StateCode;
    private double Latitude;
    private double Longitude;
    private int Region;
    private int Zone;
    private int NumRiders;
    private boolean AnchorSchool;

    public School(){}

    public School(int Id, String schoolName, String stateCode, double latitude, double longitude, int region, int zone, int numRiders, boolean anchorSchool) {
        this.Id = Id;
        SchoolName = schoolName;
        StateCode = stateCode;
        Latitude = latitude;
        Longitude = longitude;
        Region = region;
        Zone = zone;
        NumRiders = numRiders;
        AnchorSchool = anchorSchool;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public String getStateCode() {
        return StateCode;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public int getRegion() {
        return Region;
    }

    public int getZone() {
        return Zone;
    }

    public int getNumRiders() {
        return NumRiders;
    }

    public boolean isAnchorSchool() {
        return AnchorSchool;
    }

    public int getId() {
        return Id;
    }
}
