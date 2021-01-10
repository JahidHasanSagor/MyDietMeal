package com.bakikhata.mydietmealpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regUsername, regFullName, regEmail, regPhone, regPassword;
    Button btnSignUp, btnRegToLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regFullName = findViewById(R.id.fullName);
        regUsername = findViewById(R.id.userName);
        regEmail = findViewById(R.id.email);
        regPhone = findViewById(R.id.phone);
        regPassword = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnRegToLogin = findViewById(R.id.btnRegToLogin);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("user");

    }

    private Boolean validateName(){
        String nameVal = regFullName.getEditText().getText().toString();
        if(nameVal.isEmpty()){
            regFullName.setError("Field cannot be empty");
            return false;
        }else {
            regFullName.setError(null);
            regFullName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUserName(){
        String userVal = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(userVal.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if (userVal.length() >= 15){
            regUsername.setError("Username too long");
            return false;
        }else if(!userVal.matches(noWhiteSpace)){
            regUsername.setError("White spaces are not allowed");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String emailVal = regEmail.getEditText().getText().toString();
        String emailPatter = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailVal.isEmpty()){
            regEmail.setError ("Field cannot be empty");
            return false;
        }
        else if(!emailVal.matches(emailPatter)){
            regEmail.setError("Invalid email address");
            return false;
        }
        else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhone(){
        String phoneVal = regPhone.getEditText().getText().toString();
        if(phoneVal.isEmpty()){
            regPhone.setError("Field cannot be empty");
            return false;
        }else {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String passVal = regPassword.getEditText().getText().toString();
        String passwordCriteria =  "^" +
                //"(?=.*[0-9])" +
                //"(?=.*[a-z])"+
                //"(?=.*[A-Z])"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#$%^&+=])"+
                "(?=\\s+$)"+
                ".{4,}"+
                "$";

        if(passVal.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!passVal.matches(passwordCriteria)){
            regPassword.setError("Password is too weak");
            return false;
        }
        else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void signUp(View view){

        if (!validateName() || !validateUserName() || !validateEmail() || !validatePhone() ){ //|| !validatePassword()
            return;
        }

        String name = regFullName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phone = regPhone.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username,email, phone, password);
        reference.child(username).setValue(helperClass);
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void signupToLogin(View view){
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        finish();
    }
}