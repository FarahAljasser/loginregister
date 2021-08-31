package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    TextInputEditText textInputLayoutFullname, textInputLayoutEmail, textInputLayoutphone, textInputLayoutPassword;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textInputLayoutFullname = findViewById(R.id.fullname);
        textInputLayoutEmail = findViewById(R.id.email);
        textInputLayoutphone = findViewById(R.id.phone);
        textInputLayoutPassword = findViewById(R.id.password);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);


        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String fullname, email, phone, password;
                fullname = String.valueOf(textInputLayoutFullname.getText());
                email = String.valueOf(textInputLayoutEmail.getText());
                phone = String.valueOf(textInputLayoutphone.getText());
                password = String.valueOf(textInputLayoutPassword.getText());

                if(!fullname.equals("") && !phone.equals("") && !email.equals("") && !password.equals("")){
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[4];
                        field[0] = "fullname";
                        field[1] = "email";
                        field[2] = "phone";
                        field[3] = "password";
                        String[] data = new String[4];
                        field[0] = "fullname";
                        field[1] = "email";
                        field[2] = "phone";
                        field[3] = "password";
                        PutData putData = new PutData("http://localhost/dashboard/LogIn-SignUp/signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Sign Up Success")){
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
                }else {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}