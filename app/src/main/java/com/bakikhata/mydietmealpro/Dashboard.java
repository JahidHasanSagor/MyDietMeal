package com.bakikhata.mydietmealpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {

    private String name, phone;
    private TextView dashboardUsername, dashboardPhoneNumber;
    private CardView cardDietChart, cardMakeSubscription, cardPayment,
            cardConsultWithNutritionist, cardUpdatePackage, cardCancelSubscription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dashboardUsername = findViewById(R.id.dashboardUserName);
        dashboardPhoneNumber = findViewById(R.id.dashboardUserPhone);

        //Getting value from login activity
        name = Objects.requireNonNull(getIntent().getStringExtra("name"));
        phone = Objects.requireNonNull(getIntent().getStringExtra("phone"));

        dashboardUsername.setText(name);
        dashboardPhoneNumber.setText(phone);

        //Card View
        cardDietChart = findViewById(R.id.cardDietChart);
        cardMakeSubscription = findViewById(R.id.cardMakeSubscription);
        cardConsultWithNutritionist = findViewById(R.id.cardConsultNutritionist);
        cardPayment = findViewById(R.id.cardPayment);
        cardUpdatePackage = findViewById(R.id.cardUpdatePackage);
        cardCancelSubscription = findViewById(R.id.cardCancelSubscription);

    }//End of onCreate method

    public void cardViewClick(View view)  {
        Intent intent;

        switch (view.getId()) {
            case R.id.cardDietChart:
                intent = new Intent(this, DietChart.class);
                startActivity(intent);
                break;
            case R.id.cardMakeSubscription:
                intent = new Intent(this, MakeSubscription.class);
                startActivity(intent);
                break;
            case R.id.cardConsultNutritionist:
                intent = new Intent(this, ConsultWithNutritionist.class);
                startActivity(intent);
                break;
            case R.id.cardPayment:
                intent = new Intent(this, Payment.class);
                startActivity(intent);
                break;
            case R.id.cardUpdatePackage:
                intent = new Intent(this, Packages.class);
                startActivity(intent);
                break;
            case R.id.cardCancelSubscription:
                intent = new Intent(this, CancelSubscription.class);
                startActivity(intent);
                break;
            default:break;
        }//End of switch statement
    } //End of cardViewClick method



}//End of Dashboard Class