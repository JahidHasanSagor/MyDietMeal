package com.bakikhata.mydietmealpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MakeSubscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_subscription);
    }

    public void seePackage(View view) {
        Intent intent = new Intent(this, Packages.class);
        startActivity(intent);
    }
}