package com.example.nontoninaja.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.Model.MyTicketWithQty;
import com.example.nontoninaja.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    MyTicketWithQty myTicketWithQty;
    CardView cardReguler,cardVip,cardVvip;
    TextView eventTitle,eventCategory,eventDate,eventTime;
    FirebaseAuth firebaseAuth;

    TextView priceReguler,priceVip,priceVvip,qtyReguler,qtyVip,qtyVvip,tvtotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        firebaseAuth = FirebaseAuth.getInstance();

        Log.d("Nama", firebaseAuth.getCurrentUser().getDisplayName());
        
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
        Log.d("122",myTicketWithQty.getCountVip()+"");

        eventTitle.setText(ticket.getTxtTitle());
        eventTime.setText(ticket.getTxtTime());
        eventDate.setText(ticket.getTxtDate());
        eventCategory.setText(ticket.getTxtCategory());

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

    }
}