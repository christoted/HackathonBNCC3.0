package com.example.nontoninaja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nontoninaja.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class ProfileDetailActivity extends AppCompatActivity {

    ImageView imageView;
    EditText profileName, profileEmail, profilePhoneNumber, profileAddress;
    Button btnSave, btnUpdate, btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
    String emailGoogle, profileGoogle;
    String _PHONE, _ADDRESS, _NAME, _EMAIL, _SALDO;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        Log.d("Profile Detail Activity ", "onCreate: curr user is " + userID);
        init();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            profileGoogle = acct.getDisplayName();
            emailGoogle = acct.getEmail();

            Log.d("DISPLAYED", "Name: " + profileGoogle);
            Log.d("DISPLAYED", "EMAIL: " + emailGoogle);
        }

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.getString("name") != null){
                    profileName.setText(documentSnapshot.getString("name"));
                    profileEmail.setText(documentSnapshot.getString("email"));
                    profilePhoneNumber.setText(documentSnapshot.getString("phone"));
                    profileAddress.setText(documentSnapshot.getString("address"));
                    _SALDO = documentSnapshot.getString("saldo");
                }else{
                    profileName.setText(profileGoogle);
                    profileEmail.setText(emailGoogle);
                }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                firebaseAuth.signOut();
//                Intent i = new Intent(ProfileDetailActivity.this, LoginActivity.class);
//                startActivity(i);
//                finish();
                logoutGoogle(v);
            }
        });
    }

    public void logoutGoogle(final View view){
        FirebaseAuth.getInstance().signOut();
        GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                .signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileDetailActivity.this, "Signout Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(){
        _NAME = profileName.getText().toString().trim();
        _ADDRESS = profileAddress.getText().toString().trim();
        _PHONE = profilePhoneNumber.getText().toString().trim();
        _EMAIL = profileEmail.getText().toString().trim();
        Map<String, Object> user = new HashMap<>();
        user.put("name", _NAME);
        user.put("phone", _PHONE);
        user.put("address", _ADDRESS);
        user.put("email", _EMAIL);
        user.put("saldo", _SALDO);

        firebaseFirestore.collection("users").document(userID).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileDetailActivity.this, "Succeed Update data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void init(){
        profileName = findViewById(R.id.id_activityProfileDetail_name);
        profileEmail = findViewById(R.id.id_activityProfileDetail_email);
        profilePhoneNumber = findViewById(R.id.id_activityProfileDetail_phoneNumber);
        profileAddress = findViewById(R.id.id_activityProfileDetail_address);
        btnSave = findViewById(R.id.id_btn_activityProfileDetail_save);
        btnLogout = findViewById(R.id.id_btn_activityProfileDetail_logout);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        profileEmail.setEnabled(false);
    }
}