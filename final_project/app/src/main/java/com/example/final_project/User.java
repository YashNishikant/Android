package com.example.final_project;

public class User {

    private String username;
    private String password;

    public User(String u, String p){
        username = u;
        password = p;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
