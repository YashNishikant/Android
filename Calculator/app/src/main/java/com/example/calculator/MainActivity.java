package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button add;
    Button sub;
    Button div;
    Button mult;
    Button clear;
    Button equals;
    TextView t;
    String equation;

    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.numberDisplay);

        one = findViewById(R.id.one);
        one.setOnClickListener(this);
        two = findViewById(R.id.two);
        two.setOnClickListener(this);
        three = findViewById(R.id.three);
        three.setOnClickListener(this);
        four = findViewById(R.id.four);
        four.setOnClickListener(this);
        five = findViewById(R.id.five);
        five.setOnClickListener(this);
        six = findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight = findViewById(R.id.eight);
        eight.setOnClickListener(this);
        nine = findViewById(R.id.nine);
        nine.setOnClickListener(this);
        add = findViewById(R.id.Add);
        add.setOnClickListener(this);
        sub = findViewById(R.id.Subtract);
        sub.setOnClickListener(this);
        div = findViewById(R.id.Divide);
        div.setOnClickListener(this);
        mult = findViewById(R.id.Multiply);
        mult.setOnClickListener(this);
        zero = findViewById(R.id.zero);
        zero.setOnClickListener(this);
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(this);
        equals = findViewById(R.id.equals);
        equals.setOnClickListener(this);
        t.setText("0");
        equation = "";

    }

    public void onClick(View view){

        setTextToButton(view, one, t, "1");
        setTextToButton(view, two, t, "2");
        setTextToButton(view, three, t, "3");
        setTextToButton(view, four, t, "4");
        setTextToButton(view, five, t, "5");
        setTextToButton(view, six, t, "6");
        setTextToButton(view, seven, t, "7");
        setTextToButton(view, eight, t, "8");
        setTextToButton(view, nine, t, "9");
        setTextToButton(view, add, t, "+");
        setTextToButton(view, sub, t, "-");
        setTextToButton(view, div, t, "/");
        setTextToButton(view, mult, t, "*");
        setTextToButton(view, zero, t, "0");
        setClear(view, clear, t);
        calculationAlgorithm(view, equals, t);
    }

    public void setTextToButton(View v, Button b, TextView txtview, String number){
        if(v.getId() == b.getId()){
            txtview.setText(equation + number);
            equation = equation + number;
        }
    }

    public void setClear(View v, Button c, TextView txtview){
        if(v.getId() == c.getId()){
            equation = "";
            txtview.setText("0");
            error = false;
        }
    }

    public void calculationAlgorithm(View v, Button b, TextView txtview){
        double total = 0;
        String s = "";

        if(v.getId() == b.getId()) {
            StringTokenizer tokenizer = new StringTokenizer(equation, "+-/*", true);

            while (tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                //s = s + currentToken;
                //test.setText(s);

                try {
                    if(currentToken.equals("*")) {
                        total = total * Integer.parseInt(tokenizer.nextToken());

                    }
                    else if(!currentToken.equals("*") && !currentToken.equals("+") && !currentToken.equals("-") && !currentToken.equals("/")){
                        total = Integer.parseInt(currentToken);
                    }
                }
                catch(Exception e){
                    txtview.setText("ERROR");
                    error = true;
                    break;
                }

                try {
                    if(currentToken.equals("/")) {
                        total = (total / Integer.parseInt(tokenizer.nextToken()));
                    }
                    else if(!currentToken.equals("*") && !currentToken.equals("+") && !currentToken.equals("-") && !currentToken.equals("/")){
                        total = Integer.parseInt(currentToken);
                    }
                }
                catch(Exception e){
                    txtview.setText("ERROR");
                    error = true;
                    break;
                }

                try {
                    if(currentToken.equals("+")) {
                        total = total + Integer.parseInt(tokenizer.nextToken());

                    }
                    else if(!currentToken.equals("*") && !currentToken.equals("+") && !currentToken.equals("-") && !currentToken.equals("/")){
                        total = Integer.parseInt(currentToken);
                    }
                }
                catch(Exception e){
                    txtview.setText("ERROR");
                    error = true;
                    break;
                }


                try {
                    if(currentToken.equals("-")) {
                        total = total - Integer.parseInt(tokenizer.nextToken());

                    }
                    else if(!currentToken.equals("*") && !currentToken.equals("+") && !currentToken.equals("-") && !currentToken.equals("/")){
                        total = Integer.parseInt(currentToken);
                    }
                }
                catch(Exception e){
                    txtview.setText("ERROR");
                    error = true;
                    break;
                }

            }

            if(!error) {
                txtview.setText(Double.toString(total));
                if(Double.toString(total).equals("Infinity")) {
                    txtview.setText("ERROR");
                    error = true;
                }
            }
        }
    }
}