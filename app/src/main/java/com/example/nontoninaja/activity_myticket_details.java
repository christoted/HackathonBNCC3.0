package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nontoninaja.Model.MyTicketWithQty;

public class activity_myticket_details extends AppCompatActivity {

    ImageView imageView;
    TextView eventName,eventDate,eventTimeStart,eventTimeEnd;
    Button back,joinConcert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myticket_details);

        imageView = findViewById(R.id.imageView7);
        eventName = findViewById(R.id.eventName_myTicketDetails);
        eventDate = findViewById(R.id.dateEvent_myTicketDetails);
        eventTimeStart = findViewById(R.id.StartTime_myTicketDetails);
        eventTimeEnd = findViewById(R.id.EndTime_myTicketDetails);
        back = findViewById(R.id.button);
        joinConcert = findViewById(R.id.btnJoinConcert);

        final Intent intent = getIntent();
        final MyTicketWithQty myTicketWithQty = intent.getParcelableExtra("myTicket");

        Glide.with(this)
                .load(myTicketWithQty.getTicket().getImgEvent())
                .into(imageView);
        eventName.setText(myTicketWithQty.getTicket().getTxtTitle());
        eventTimeStart.setText(myTicketWithQty.getTicket().getTxtTime().substring(0,5));
        eventTimeEnd.setText(myTicketWithQty.getTicket().getTxtTime().substring(8,13));

        joinConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity_myticket_details.this,ConcertActivity.class);
                intent1.putExtra("concertData",myTicketWithQty);
                startActivity(intent1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}