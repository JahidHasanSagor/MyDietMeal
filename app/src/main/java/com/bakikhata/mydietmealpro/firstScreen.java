package com.bakikhata.mydietmealpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

public class firstScreen extends AppCompatActivity {

    TextInputLayout txtHeight, txtWeight,txtAge, txtGender;
    Button btnSeeDietChart;

    float height, weight;
    int age;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        txtHeight = findViewById(R.id.height );
        txtWeight = findViewById(R.id.weight );
        txtAge = findViewById(R.id.age );
        txtGender = findViewById(R.id.gender );
        btnSeeDietChart = findViewById(R.id.btnDietChart);

        //Getting user input
        height = Float.parseFloat(Objects.requireNonNull(txtHeight.getEditText()).getText().toString());
        age = Integer.parseInt(Objects.requireNonNull(txtAge.getEditText()).getText().toString());
        weight = Float.parseFloat(Objects.requireNonNull(txtWeight.getEditText()).getText().toString());
        gender = Objects.requireNonNull(txtGender.getEditText()).getText().toString();

        btnSeeDietChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String opinion = calBMI(height,weight, age, gender);
                double bmrValue = calBMR(height, weight, age, gender);
                Toast.makeText(getApplicationContext(), opinion, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(firstScreen.this, Login.class);
                intent.putExtra("BMI", opinion);
                intent.putExtra("BMR", bmrValue);
                startActivity(intent);
                finish();

            }
        }); //End of seeDietChart onclick method

    } //End of onCreate method
    public String  calBMI(float height, float weight, int age, String gender){
        String opinion;
        ArrayList<String> bmiValues = new ArrayList<String>();
        double bmi = weight / (Math.pow(height,2));
        if(bmi < 15){
            opinion = "Very severely underweight";
            //bmiValues = {bmi, opinion};
        }
        else if(bmi >= 15 && bmi <16){
            opinion = "Severely underweight";
        }
        else if(bmi >= 16 && bmi <=18.4){
            opinion = "Underweight";
        }
        else if(bmi >= 18.5 && bmi <=24.9){
            opinion = "Normal";
        }
        else if(bmi >= 25 && bmi <=29.9){
            opinion = "Overweight";
        }
        else if(bmi >=30 ){
            opinion = "Obesity";
        }

        else{
            opinion = "Please input valid age, height or weight";
        }
        return opinion;
    }

    public double calBMR(float height, float weight, int age, String gender){
        double basalMetabolicRate = 0;

        if (gender.equals("Male")){
            basalMetabolicRate = 66.47 + (13.75 * weight) +(5.003 * (height*100)) - (6.755 * age);
        }
        else if (gender.equals("Female")){
            basalMetabolicRate = 655.1 + (9.563 * weight) + (1.85 * (height* 100)) - (4.676 * age);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
        }
        return basalMetabolicRate;
    }
} //End of FirstScreen Class