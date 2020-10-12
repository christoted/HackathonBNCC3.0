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

public class ConcertActivity extends AppCompatActivity {

    ImageView imageView;
    TextView eventName;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);

        imageView = findViewById(R.id.imageView8);
        eventName = findViewById(R.id.textView14);
        btnBack = findViewById(R.id.btnBackInConcertActivity);

        Intent intent = getIntent();
        final MyTicketWithQty myTicketWithQty = intent.getParcelableExtra("concertData");

        Glide.with(this)
                .load(myTicketWithQty.getTicket().getImgEvent())
                .into(imageView);
        eventName.setText(myTicketWithQty.getTicket().getTxtTitle());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}