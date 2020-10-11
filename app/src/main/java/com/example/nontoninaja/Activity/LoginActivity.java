package com.example.nontoninaja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nontoninaja.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, btnGoogle;
    TextView tvRegister;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                String validateEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if(email.equals("")){
                    etEmail.setError("Email is required");
                    return;
                }else{
                    if(!email.matches(validateEmail)){
                        etEmail.setError("Wrong format !");
                        return;
                    }
                }
                if(password.equals("")){
                    etPassword.setError("Password required !");
                    return;
                }else{
                    if(password.length() < 6){
                        etPassword.setError("Password >= 6");
                        return;
                    }
                }
                Log.d("Email", "onClick: " + email);
                Log.d("Password", "onClick: " + password);
                /*
                * Check condition if any username & password is avail (firebase)
                * Boolean
                *
                *  If boolean is true == intent
                     Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                     startActivity(intent);
                * */

                //authenticate user
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Error !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //google
            }
        });
    }

    void init(){
        etEmail = findViewById(R.id.id_et_activitylogin_email);
        etPassword = findViewById(R.id.id_et_activitylogin_password);
        btnLogin = findViewById(R.id.id_btn_activitylogin_login);
        tvRegister = findViewById(R.id.id_tv_activitylogin_register);
        btnGoogle = findViewById(R.id.id_btn_activitylogin_google);
        firebaseAuth = FirebaseAuth.getInstance();
    }

}