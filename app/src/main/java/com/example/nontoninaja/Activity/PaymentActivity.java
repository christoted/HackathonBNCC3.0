package com.example.nontoninaja.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.Model.MyTicketInventory;
import com.example.nontoninaja.Model.MyTicketWithQty;
import com.example.nontoninaja.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.NumberFormat;
import java.util.Locale;

import javax.annotation.Nullable;

public class PaymentActivity extends AppCompatActivity {

    MyTicketWithQty myTicketWithQty;
    CardView cardReguler,cardVip,cardVvip;
    TextView eventTitle,eventCategory,eventDate,eventTime;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;
    EditText profileName, profilePhoneNumber, profileEmail, profileAddress;
    String userID, profileGoogle, emailGoogle;
    CardView cardViewPay;
    EditText recipientEmail;
    CheckBox checkBox;

    TextView priceReguler,priceVip,priceVvip,qtyReguler,qtyVip,qtyVvip,tvtotalPrice;
    Button back;

    private void getCurrData(){

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            profileGoogle = acct.getDisplayName();
            emailGoogle = acct.getEmail();

            Log.d("DISPLAYED", "Name: " + profileGoogle);
            Log.d("DISPLAYED", "EMAIL: " + emailGoogle);
        }

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
                }else{
                    profileName.setText(profileGoogle);
                    profileEmail.setText(emailGoogle);
                }

            }
        });
    }

    private void initData(){
        profileName = findViewById(R.id.id_activity_payment_name);
        profileEmail = findViewById(R.id.id_activity_payment_email);
        profilePhoneNumber = findViewById(R.id.id_activity_payment_number);
        profileAddress = findViewById(R.id.id_activity_payment_address);
        back = findViewById(R.id.id_btn_activity_payment_back);
        cardViewPay = findViewById(R.id.cardViewPay);
        recipientEmail = findViewById(R.id.emailRecipient);
        checkBox = findViewById(R.id.id_checkBox_activity_payment_buyMyself);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initData();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        Log.d("Nama", firebaseAuth.getCurrentUser().getDisplayName());
        getCurrData();


        cardReguler = findViewById(R.id.cardView4);
        cardVip = findViewById(R.id.cardView5);
        cardVvip = findViewById(R.id.cardView6);

        priceReguler = findViewById(R.id.id_txtView_activity_payment_regPrice);
        priceVip = findViewById(R.id.id_txtView_activity_payment_VIPPrice);
        priceVvip = findViewById(R.id.id_txtView_activity_payment_VVIPPrice);
        tvtotalPrice = findViewById(R.id.id_txtView_activity_payment_totalPAY);

        eventTitle = findViewById(R.id.id_txtView_activity_payment_title);
        eventCategory = findViewById(R.id.id_txtView_activity_payment_category);
        eventDate = findViewById(R.id.id_tv_activity_payment_calendar);
        eventTime = findViewById(R.id.id_tv_activity_payment_time);


        qtyReguler = findViewById(R.id.id_txtView_activity_payment_countReg);
        qtyVip = findViewById(R.id.id_txtView_activity_payment_countVIP);
        qtyVvip = findViewById(R.id.id_txtView_activity_payment_countVVIP);


        Intent intent = getIntent();
        myTicketWithQty = intent.getParcelableExtra("myTicketWithQty");
        MyTicket ticket = myTicketWithQty.getTicket();

        eventTitle.setText(ticket.getTxtTitle());
        eventTime.setText(ticket.getTxtTime());
        eventDate.setText(ticket.getTxtDate());
        eventCategory.setText(ticket.getTxtCategory());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recipientEmail.setVisibility(View.GONE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    recipientEmail.setVisibility(View.VISIBLE);
                }
                else{
                    recipientEmail.setVisibility((View.INVISIBLE));
                    recipientEmail.setVisibility((View.GONE));
                }
            }
        });
        checkBox = findViewById(R.id.id_checkBox_activity_payment_buyMyself);
        recipientEmail = findViewById(R.id.emailRecipient);

        if(myTicketWithQty.getCountReguler() == 0)
        {
            cardReguler.setVisibility(View.GONE);
        }
        else
        {
            qtyReguler.setText(myTicketWithQty.getCountReguler()+"x");
            priceReguler.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(new Double(myTicketWithQty.getCountReguler()*500000)));
        }

        if(myTicketWithQty.getCountVip() ==0)
        {
            cardVip.setVisibility(View.GONE);
        }
        else
        {
            qtyVip.setText(myTicketWithQty.getCountVip()+"x");
            priceVip.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(new Double(myTicketWithQty.getCountVip()*600000)));
        }

        if (myTicketWithQty.getCountVvip() == 0 )
        {
            cardVvip.setVisibility(View.GONE);
        }
        else
        {
            qtyVvip.setText(myTicketWithQty.getCountVvip()+"x");
            priceVvip.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(new Double(myTicketWithQty.getCountVvip()*750000)));
        }
        Double totalPrice = new Double(intent.getIntExtra("totalPrice",0));
        tvtotalPrice.setText("Rp "+ NumberFormat.getNumberInstance(Locale.FRANCE).format(totalPrice));

        cardViewPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentActivity.this,MainActivity.class);
                MyTicketInventory.myTicketInventory.add(myTicketWithQty);
                Toast.makeText(getApplicationContext(),"Transaction Success",Toast.LENGTH_SHORT).show();
//                intent1.putExtra("myTicketInventory",myTicketWithQty);
                startActivity(intent1);
                finish();
                finish();
                finish();
            }
        });
    }
}