package com.example.ihsastable.data.model;

public class Horse {
    private String HorseName;
    private int Age;
    private double Height;
    private double Weight;
    private String Color;
    private String Description;
    // spur options are: Optional Rowel, Optional, (need to check for the rest)
    private String Spurs;
    private int MinimumWeight;

    public Horse(){}

    public Horse(String horseName, int age, double height, double weight, String color, String description, String spurs, int minimumWeight) {
        HorseName = horseName;
        Age = age;
        Height = height;
        Weight = weight;
        Color = color;
        Description = description;
        Spurs = spurs;
        MinimumWeight = minimumWeight;
    }

    public String getHorseName() {
        return HorseName;
    }

    public int getAge() {
        return Age;
    }

    public double getHeight() {
        return Height;
    }

    public double getWeight() {
        return Weight;
    }

    public String getColor() {
        return Color;
    }

    public String getDescription() {
        return Description;
    }

    public String getSpurs() {
        return Spurs;
    }

    public int getMinimumWeight() {
        return MinimumWeight;
    }
}
