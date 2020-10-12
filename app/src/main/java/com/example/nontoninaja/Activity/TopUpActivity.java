package com.example.nontoninaja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nontoninaja.R;
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

public class TopUpActivity extends AppCompatActivity implements View.OnClickListener {

    CardView topUpButton, topUp100K, topUp150K, topUp200K, topUp250K;
    EditText editText;
    String textAmount;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    String userId;

    EditText profileName, profileEmail, profilePhoneNumber, profileAddress;
    String _PHONE, _ADDRESS, _NAME, _EMAIL;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;


    int saldo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();
        Log.d("HALO_TEST", "onCreate: curr user is " + userId);

      databaseReference = FirebaseDatabase.getInstance().getReference("users");
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.getString("email") != null){
                    _NAME = (documentSnapshot.getString("name"));
                    Log.d("test12345", "onEvent: " + documentSnapshot.getString("name"));
                     _EMAIL = (documentSnapshot.getString("email"));
                    _PHONE = (documentSnapshot.getString("phone"));
                    _ADDRESS = (documentSnapshot.getString("address"));
//                    _SALDO = documentSnapshot.getString("saldo");
                }
            }
        });

        editText = findViewById(R.id.id_topUp_activity_enterAmount);
        textAmount = editText.getText().toString();
        topUpButton = findViewById(R.id.id_activity_topUp_btn_processed);
        topUp100K = findViewById(R.id.id_topUp_100K);
        topUp150K = findViewById(R.id.id_topUp_150K);
        topUp200K = findViewById(R.id.id_topUp_200K);
        topUp250K = findViewById(R.id.id_topUp_250K);

        topUp100K.setOnClickListener(this);
        topUp150K.setOnClickListener(this);
        topUp200K.setOnClickListener(this);
        topUp250K.setOnClickListener(this);


        topUpButton.setOnClickListener(this);
    }

    private void pushData() {

        Map<String, Object> user = new HashMap<>();
        String saldos = String.valueOf(saldo);
        user.put("name", _NAME);
        user.put("phone", _PHONE);
        user.put("address", _ADDRESS);
        user.put("email", _EMAIL);
        user.put("saldo", saldos);

        userId = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);

        documentReference
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("12345", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("123455", "Error writing document", e);
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if ( view == topUp100K) {
            saldo = 100000;
            editText.setText(saldo+"");
        } else if ( view == topUp150K) {
            saldo = 150000;
            editText.setText(saldo+"");
        } else if ( view == topUp200K) {
            saldo = 200000;
            editText.setText(saldo+"");
        } else if ( view == topUp250K) {
            saldo = 250000;
            editText.setText(saldo+"");
        }

        if ( view == topUpButton) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            pushData();
            Log.d("HALO_TEST_BAWAH", "onCreate: curr user is " + userId);
        }
    }
}