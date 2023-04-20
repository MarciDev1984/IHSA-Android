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

    public School(final int Id, final String schoolName, final String stateCode, final double latitude, final double longitude, final int region, final int zone, final int numRiders, final boolean anchorSchool) {
        this.Id = Id;
        this.SchoolName = schoolName;
        this.StateCode = stateCode;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.Region = region;
        this.Zone = zone;
        this.NumRiders = numRiders;
        this.AnchorSchool = anchorSchool;
    }

    public String getSchoolName() {
        return this.SchoolName;
    }

    public String getStateCode() {
        return this.StateCode;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public int getRegion() {
        return this.Region;
    }

    public int getZone() {
        return this.Zone;
    }

    public int getNumRiders() {
        return this.NumRiders;
    }

    public boolean isAnchorSchool() {
        return this.AnchorSchool;
    }

    public int getId() {
        return this.Id;
    }
}
