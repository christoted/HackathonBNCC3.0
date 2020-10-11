package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.nontoninaja.Model.MyTicket;

public class TransactionSummary extends AppCompatActivity {

    TextView id_txtView_TransactionSummary_eventName;
    TextView id_txtView_TransactionSummary_eventCategory;
    TextView id_txtView_TransactionSummary_eventDate;
    TextView id_txtView_TransactionSummary_eventTime;
    TextView id_txtView_TransactionSummary_eventLocation;
    TextView id_txtView_TransactionSummary_adminFee;

    Button id_btn_TransactionSummary_btnOrder;

    MyTicket myTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_summary);

        initItems();
    }

    private void initItems() {
        id_txtView_TransactionSummary_eventName.findViewById(R.id.id_txtView_TransactionSummary_eventName);
        id_txtView_TransactionSummary_eventCategory.findViewById(R.id.id_txtView_TransactionSummary_eventCategory);
        id_txtView_TransactionSummary_eventDate.findViewById(R.id.id_txtView_TransactionSummary_eventDate);
        id_txtView_TransactionSummary_eventTime.findViewById(R.id.id_txtView_TransactionSummary_eventTime);
        id_txtView_TransactionSummary_eventLocation.findViewById(R.id.id_txtView_TransactionSummary_eventLocation);
        id_txtView_TransactionSummary_adminFee.findViewById(R.id.id_txtView_TransactionSummary_adminFee);

        id_btn_TransactionSummary_btnOrder.findViewById(R.id.id_btn_TransactionSummary_btnOrder);


        Intent intent = getIntent();
        myTicket =  intent.getParcelableExtra("myTicket");

        id_txtView_TransactionSummary_eventName.setText(myTicket.getTxtTitle());
        id_txtView_TransactionSummary_eventCategory.setText(myTicket.getTxtCategory());
        id_txtView_TransactionSummary_eventDate.setText(myTicket.getTxtDate());
        id_txtView_TransactionSummary_eventTime.setText(myTicket.getTxtTime());
        id_txtView_TransactionSummary_eventLocation.setText(myTicket.getTxtLocation());

    }
}