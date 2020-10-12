package com.example.nontoninaja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nontoninaja.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, btnGoogle;
    TextView tvRegister;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseFirestore firebaseFirestore;
    int RC_SIGN_IN = 0;
    String emailGoogle, profileGoogle, saldo, address, phonenumber, userID;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(firebaseAuth.getCurrentUser() != null || signInAccount != null){
            Toast.makeText(LoginActivity.this, "Account ald loggedin", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
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

                mLoadingBar.setTitle("Loging in");
                mLoadingBar.setMessage("Please wait, while check your credentials");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();
                inputDataFirebase(email,password);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("215755284466-o530jjodtp7r9r2gf3l17t77ai1ha3b4.apps.googleusercontent.com")
                .requestEmail()
                .build();
// Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
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
        firebaseFirestore = FirebaseFirestore.getInstance();
        mLoadingBar = new ProgressDialog(LoginActivity.this);
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            mLoadingBar.setTitle("Loging in");
            mLoadingBar.setMessage("Please wait, while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
            firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            });
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error meesage : ", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void inputDataFirebase(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Error !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void signInWithGoogle(String email){
//        firebaseAuth.signInWithCredential()
//    }

    public void registerFirebase(final String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("name",profileGoogle);
                    user.put("email",emailGoogle);
                    user.put("phone",phonenumber);
                    user.put("saldo",saldo);
                    user.put("address",address);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Firebase Firestore", "onSuccess: User profile is created " + userID);
                        }
                    });
                    inputDataFirebase(emailGoogle,profileGoogle);
                }else{
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}