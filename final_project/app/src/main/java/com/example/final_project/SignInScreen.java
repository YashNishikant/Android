package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SignInScreen extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signInButton;
    Switch signin_register;

    TextView title;

    ArrayList usernodelist = new ArrayList();
    ArrayList usernamelist = new ArrayList();

    Timer timer;
    TimerTask timerthread;

    private boolean register;

    private static String usernametxt;
    private static String passwordtxt;
    public static String key = "c743270j4283cj0982v5j75908j7v245-70864";
    public static String key2 = "6354lkj5634ljk6534jn6345jl-4355436gfd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        MainWorkoutActivity.DataAccess.getUsers();

        timerthread = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        usernodelist.clear();
                        for(int i = 0; i < MainWorkoutActivity.DataAccess.getUsersArray().size(); i++){
                            usernodelist.add(MainWorkoutActivity.DataAccess.getUsersArray().get(i));
                        }

                        createUserNameList();
                    }
                });
            }
        };

        signin_register = findViewById(R.id.id_switch);
        signInButton = findViewById(R.id.id_button_signIn);
        username = findViewById(R.id.id_edittext_username);
        password = findViewById(R.id.id_edittext_password);
        title = findViewById(R.id.id_title);

        signin_register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    register = true;
                    title.setText("Register");
                }
                else{
                    register = false;
                    title.setText("Sign In");
                }
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernametxt = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordtxt = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(usernametxt == null || passwordtxt == null){
                    Toast("Please enter a username and password");
                    return;
                }


                if(usernametxt.equals("") || passwordtxt.equals("")){
                    Toast("Please enter a username and password");
                    return;
                }

                if(usernametxt.contains(" ") || passwordtxt.contains(" ")){
                    Toast("Do Not Use Spaces");
                    return;
                }

                if(usernametxt.contains("-") || passwordtxt.contains("-")){
                    Toast("Do Not Use Dashes");
                    return;
                }

                if(duplicateUsername()){
                    Toast("Username taken");
                    return;
                }

                if(!register && wrongpassword()){
                    Toast("Incorrect Username Or Password");
                    return;
                }

                if(!usernametxt.isEmpty() && !usernametxt.contains(" ") && !passwordtxt.contains(" ")){
                    Intent signOnIn = new Intent(SignInScreen.this, StartScreen.class);

                    if(!register && returningUser(usernodelist, getUserNode())) {
                        signOnIn.putExtra(key, "Welcome Back " + getUsername() + "!");
                        signOnIn.putExtra(key2, false);
                        startActivity(signOnIn);
                    }
                    else if (!returningUser(usernodelist, getUserNode()) && register) {
                        signOnIn.putExtra(key, "New Account Created. Welcome " + getUsername() + "!");
                        signOnIn.putExtra(key2, true);
                        startActivity(signOnIn);
                    }

                }

            }
        });

        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerthread, 0, 10);
    }

    public static void clearlogin(){
        usernametxt = "";
        passwordtxt = "";
    }

    public boolean returningUser(ArrayList users, String name){

        if(register){
            return false;
        }

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).equals(name)){
                return true;
            }
        }
        return false;
    }

    public void createUserNameList(){
        usernamelist.clear();
        for(int i = 0; i < usernodelist.size(); i++){
            usernamelist.add(usernodelist.get(i).toString().substring(0, usernodelist.get(i).toString().indexOf("-")));
        }
    }

    public boolean duplicateUsername(){
        for(int i = 0; i < usernamelist.size(); i++){
            if(register && getUsername().equals(usernamelist.get(i))){
                return true;
            }
        }
        return false;
    }

    public boolean wrongpassword(){
        for(int i = 0; i < usernamelist.size(); i++){
            if(!register && (getUserNode().equals(usernodelist.get(i)))){
                return false;
            }
        }
        return true;
    }

    public static String getUsername(){
        return usernametxt;
    }

    public static String getPassword(){
        return passwordtxt;
    }

    public static String getUserNode(){
        return usernametxt + "-" + passwordtxt;
    }

    void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}