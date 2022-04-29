package com.example.gps;

public class Position {

    private String address;
    private double time;

    public Position(String address, double time){
        this.address = address;
        this.time = time;
    }

    public double getTime(){
        return time;
    }
    public void additionalTime(double x){
        time = x;
    }
    public String getAddress(){
        return address;
    }
}
