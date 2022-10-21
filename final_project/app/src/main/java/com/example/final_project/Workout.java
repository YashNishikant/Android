package com.example.final_project;

public class Workout {

    private long time;
    private String name;
    private long distance;
    private boolean display;

    public Workout(String n, long s, long d, boolean disp){
        time = s;
        distance = d;
        name = n;
        display = disp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int Time) {
        this.time = Time;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDisplay(){
        return display;
    }

}
