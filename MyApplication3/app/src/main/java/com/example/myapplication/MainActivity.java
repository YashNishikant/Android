package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    TextView test;
    String equation;
    ArrayList operators2 = new ArrayList();
    ArrayList operators = new ArrayList();
    double total = 0;

    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.numberDisplay);

        test = findViewById(R.id.testing);

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
            total = 0;
        }
    }

    public void calculationAlgorithm(View v, Button b, TextView txtview){

        String newstring = "";

        StringTokenizer operatorlist = new StringTokenizer(equation, "+-/*", true);
        while(operatorlist.hasMoreTokens()) {
            operators.add(operatorlist.nextToken());
        }

        for(int i = 0; i < operators.size()-1; i++){
            if(operators.get(i).equals("*")){
                double temptotal = 0;
                temptotal = Double.parseDouble(operators.get(i-1).toString()) * Double.parseDouble(operators.get(i+1).toString());
                total += temptotal;

                operators2.set(operators2.size()-1, temptotal);

            }
            if(operators.get(i).equals("/")){
                double temptotal2 = 0;
                temptotal2 = Double.parseDouble(operators.get(i-1).toString()) / Double.parseDouble(operators.get(i+1).toString());
                total += temptotal2;

                operators2.set(operators2.size()-1, temptotal2);

            }
            else{
                operators2.add(operators.get(i));
            }
        }

        for(int i = 0; i < operators2.size()-1; i++){
            if(operators.get(i).equals("+")){
                total += Double.parseDouble(operators.get(i-1).toString()) * Double.parseDouble(operators.get(i+1).toString());
            }
            if(operators.get(i).equals("-")){
                total += Double.parseDouble(operators.get(i-1).toString()) * Double.parseDouble(operators.get(i+1).toString());
            }
        }

        t.setText(Double.toString(total));
    }

}