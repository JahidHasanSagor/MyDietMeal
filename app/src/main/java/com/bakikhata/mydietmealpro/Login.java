package com.bakikhata.mydietmealpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    ImageView image;
    TextView slogan ,logoText;
    TextInputLayout username, password;
    Button btnLogIn, btnSignUp, forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        image = findViewById(R.id.logoImage);
        logoText = findViewById(R.id.logoText);
        slogan = findViewById(R.id.slogan);

        username = findViewById(R.id.loginUserName);
        password = findViewById(R.id.loginPassword);

        btnLogIn = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnNewUser);
        forgetPassword = findViewById(R.id.btnForgotPassword);

    }

    private Boolean validateUserName(){
        String userVal = username.getEditText().getText().toString();
        if(userVal.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String passVal = Objects.requireNonNull(password.getEditText()).getText().toString();
        if(passVal.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void logIn(View view){
        if (!validateUserName() || !validatePassword()){
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = Objects.requireNonNull(username.getEditText()).getText().toString();
        final String userEnteredPassword = Objects.requireNonNull(password.getEditText()).getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    username.setError("null");
                    username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)){

                        username.setError("null");
                        username.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("phone", phoneFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                        finish();
                    }
                    else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No such user exist");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void signUp(View view){
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        // Shared Animation
        //Pair[] pairs = new Pair[7];
        //pairs[0] = new Pair<View,String>(image, "logo_image");
        //pairs[1] = new Pair<View,String>(logoText, "logo_text");
        //pairs[2] = new Pair<View,String>(slogan, "slogan");
        //pairs[3] = new Pair<View,String>(username, "username");
        //pairs[4] = new Pair<View,String>(password, "password");
        //pairs[5] = new Pair<View,String>(logIn, "login");
        // pairs[6] = new Pair<View,String>(signUp, "login_signup");
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        //ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
        //startActivity(intent, activityOptions.toBundle());
        // }

    }

}