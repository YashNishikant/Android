package com.example.final_project;

public class Workout {

    private long secondsSpent;
    private String name;
    private long metersdistance;

    public Workout(String n, long s, long d){
        secondsSpent = s;
        metersdistance = d;
        name = n;
    }

    public long getSecondsSpent() {
        return secondsSpent;
    }

    public void setSecondsSpent(int secondsSpent) {
        this.secondsSpent = secondsSpent;
    }

    public long getDistance() {
        return metersdistance;
    }

    public void setDistance(int distance) {
        this.metersdistance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
