package com.example.nontoninaja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPhoneNumber, etEmail, etPassword;
    Button btnRegister;
    TextView tvLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        if(firebaseAuth.getCurrentUser() != null){
            Toast.makeText(RegisterActivity.this, "Account ald loggedin", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,phonenumber,email,password;
                String validateEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                username = etUsername.getText().toString().trim();
                phonenumber = etPhoneNumber.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString();

                if(username.equals("")) {
                    etUsername.setError("Username required!");
                    return;
                }else{
                    if(username.length() < 3){
                        etUsername.setError("Username too short!");
                        return;
                    }
                }
                if(phonenumber.equals("")){
                    etPhoneNumber.setError("Phone number required !");
                    return;
                }
                if(phonenumber.length() < 10 || phonenumber.length() > 12){
                    etPhoneNumber.setError("10 - 12 number");
                    return;
                }
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


                /*
                * INPUT DATA HERE FIREBASE
                * if(udah masuk ke firebase -> intent ke login atau langsung masuk nih gan? )
                * */

                //REGISTER USER IN FIREBASE
                registerFirebase(email,password);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    void init(){
        etUsername = findViewById(R.id.id_et_activityregister_username);
        etPhoneNumber = findViewById(R.id.id_et_activityregister_phoneNumber);
        etEmail = findViewById(R.id.id_et_activityregister_email);
        etPassword = findViewById(R.id.id_et_activityregister_password);
        btnRegister = findViewById(R.id.id_btn_activityregister_register);
        tvLogin = findViewById(R.id.id_tv_activityregister_login);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void registerFirebase(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}