package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nontoninaja.Activity.PaymentActivity;
import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.Model.MyTicketWithQty;

import java.text.NumberFormat;
import java.util.Locale;

public class TransactionSummary extends AppCompatActivity implements View.OnClickListener {

    TextView id_txtView_TransactionSummary_eventName;
    TextView id_txtView_TransactionSummary_eventCategory;
    TextView id_txtView_TransactionSummary_eventDate;
    TextView id_txtView_TransactionSummary_eventTime;
    TextView id_txtView_TransactionSummary_eventLocation;
    TextView id_txtView_TransactionSummary_adminFee;
    Button btnBack;


    CardView id_btn_TransactionSummary_btnOrder;

    MyTicket myTicket;

    //Regular
    ImageView btnMinusReg, btnPlusReg;
    TextView  tvNumberReg;


    //VIP
    ImageView btnMinusVIP, btnPlusVIP;
    TextView  tvNumberVIP;
    //VVIP
    ImageView btnMinusVVIP, btnPlusVVIP;
    TextView  tvNumberVVIP;

    int countReg = 0;
    int countVIP = 0;
    int countVVIP = 0;
    int countTotal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initItems();
        tvNumberReg.setText(countReg+"");
        tvNumberVIP.setText(countVIP+"");
        tvNumberVIP.setText(countVVIP+"");

        if ( countTotal == 0) {
            id_txtView_TransactionSummary_adminFee.setText("");
        } else {

            id_txtView_TransactionSummary_adminFee.setText(countTotal+"");
        }
    }

    private void initItems() {
        btnMinusReg = findViewById(R.id.id_btn_summary_btn_minus_reguler);
        btnPlusReg = findViewById(R.id.id_btn_summary_btn_plus_reguler);
        btnMinusVIP = findViewById(R.id.id_btn_summary_btn_minus_vip);
        btnPlusVIP = findViewById(R.id.id_btn_summary_btn_plus_vip);
        btnMinusVVIP = findViewById(R.id.id_btn_summary_btn_minus_vvip);
        btnPlusVVIP = findViewById(R.id.id_btn_summary_btn_plus_vvip);
        tvNumberReg = findViewById(R.id.id_tv_summary_count_number_regular);
        tvNumberVIP = findViewById(R.id.id_tv_summary_count_number_vip);
        tvNumberVVIP = findViewById(R.id.id_tv_summary_count_number_vvip);
        btnBack = findViewById(R.id.id_btn_activity_summary_back);

        id_txtView_TransactionSummary_eventName = findViewById(R.id.id_txtView_TransactionSummary_eventName);
        id_txtView_TransactionSummary_eventCategory = findViewById(R.id.id_txtView_TransactionSummary_eventCategory);
        id_txtView_TransactionSummary_eventDate = findViewById(R.id.id_txtView_TransactionSummary_eventDate);
        id_txtView_TransactionSummary_eventTime = findViewById(R.id.id_txtView_TransactionSummary_eventTime);
        id_txtView_TransactionSummary_eventLocation = findViewById(R.id.id_txtView_TransactionSummary_eventLocation);



        id_txtView_TransactionSummary_adminFee = findViewById(R.id.id_txtView_TransactionSummary_adminFee);
        id_btn_TransactionSummary_btnOrder = findViewById(R.id.id_cardView_summary_order);


        id_btn_TransactionSummary_btnOrder.setOnClickListener(this);

        btnMinusReg.setOnClickListener(this);
        btnPlusReg.setOnClickListener(this);
        btnMinusVIP.setOnClickListener(this);
        btnPlusVIP.setOnClickListener(this);
        btnMinusVVIP.setOnClickListener(this);
        btnPlusVVIP.setOnClickListener(this);


        Intent intent = getIntent();
        myTicket =  intent.getParcelableExtra("myTicket");

        id_txtView_TransactionSummary_eventName.setText(myTicket.getTxtTitle());
        id_txtView_TransactionSummary_eventCategory.setText(myTicket.getTxtCategory());
        id_txtView_TransactionSummary_eventDate.setText(myTicket.getTxtDate());
        id_txtView_TransactionSummary_eventTime.setText(myTicket.getTxtTime());
        id_txtView_TransactionSummary_eventLocation.setText(myTicket.getTxtLocation());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    void plusRegulerNumber() {
        countReg++;
        tvNumberReg.setText(countReg +"");
        countTotal+=500000;
        updateTextViewTotal();
    }

    void minusRegulerNumber() {
        if ( countReg > 0) {
            countReg--;
            tvNumberReg.setText(countReg +"");
            if ( countTotal == 0) {
                updateTextViewTotal();
            } else {
                countTotal-=500000;
                updateTextViewTotal();
            }
        }
    }

    void plusVIPNumber() {
        countVIP++;
        tvNumberVIP.setText(countVIP+"");
        countTotal+=600000;
        updateTextViewTotal();
    }

    void minusVIPNumber(){
        if ( countVIP > 0) {
            countVIP--;
            tvNumberVIP.setText(countVIP+"");
            if ( countTotal == 0) {
                updateTextViewTotal();
            } else {
                countTotal-=600000;
                updateTextViewTotal();
            }
        }
    }

    void plusVVIPNumber() {
        countVVIP++;
        tvNumberVVIP.setText(countVVIP+"");
        countTotal+=750000;
        updateTextViewTotal();
    }

    void minusVVIPNumber(){
        if ( countVVIP > 0) {
            countVVIP--;
            tvNumberVVIP.setText(countVVIP+"");
            if ( countTotal == 0) {
                updateTextViewTotal();
            } else {
                countTotal-=750000;
                updateTextViewTotal();
            }

        }
    }
    private void updateTextViewTotal()
    {
        if(countTotal ==0)
        {
            id_txtView_TransactionSummary_adminFee.setText("");
        }
        else
        {

            id_txtView_TransactionSummary_adminFee.setText("Rp "+NumberFormat.getNumberInstance(Locale.FRANCE).format(countTotal));
        }
    }

    @Override
    public void onClick(View view) {

        if ( view == btnMinusReg) {
            minusRegulerNumber();
        } else if ( view == btnPlusReg) {
            plusRegulerNumber();
        } else if ( view == btnMinusVIP) {
//            Toast.makeText(this, "BEBEk", Toast.LENGTH_SHORT).show();
            minusVIPNumber();
        } else if ( view == btnPlusVIP) {
//            Toast.makeText(this, "BEBEk", Toast.LENGTH_SHORT).show();
            plusVIPNumber();
        } else if ( view == btnMinusVVIP) {
            minusVVIPNumber();
        } else if ( view == btnPlusVVIP) {
            plusVVIPNumber();
        }

        if ( view == id_btn_TransactionSummary_btnOrder) {
            if( countReg+countVIP+countVVIP > 0) {
//                Toast.makeText(this, "Order Success!", Toast.LENGTH_SHORT).show();

                MyTicketWithQty myTicketWithQty = new MyTicketWithQty(myTicket, countReg, countVIP, countVVIP);
                Log.d("120",countReg+"");
                Intent intent = new Intent(TransactionSummary.this, PaymentActivity.class);
                intent.putExtra("totalPrice",countTotal);
                intent.putExtra("myTicketWithQty", myTicketWithQty);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Please add your ticket quantity", Toast.LENGTH_SHORT).show();
            }
        }

    }
}