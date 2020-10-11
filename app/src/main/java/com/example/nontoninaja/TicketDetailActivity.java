package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nontoninaja.Model.MyTicket;
import com.google.firebase.auth.FirebaseAuth;

public class TicketDetailActivity extends AppCompatActivity {

    ImageView id_imgView_TicketDetail_eventImg;
    TextView id_tv_TicketDetail_eventName;
    TextView id_tv_TicketDetail_eventCategory;
    TextView id_tv_TicketDetail_eventPrice;
    TextView id_tv_TicketDetail_eventDate;
    TextView id_tv_TicketDetail_eventTime;
    TextView id_tv_TicketDetail_eventDesc;
    TextView id_tv_TicketDetail_eventLocation;

    TextView id_tv_TicketDetail_ticketQty;
    Button id_btn_TicketDetail_addTicket;
    Button id_btn_TicketDetail_plusQty;
    Button id_btn_TicketDetail_minusQty;
    RecyclerView id_rv_TicketDetail_rvItems;
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();


    String eventName,eventCategory,eventDesc,eventLocation,eventPrice,eventDate,eventTime;
    int eventImgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        Intent intent = getIntent();
        initItems(intent);
    }

    private void initItems(Intent intent) {

        id_imgView_TicketDetail_eventImg.findViewById(R.id.id_img_TicketDetail_eventImage);
        id_tv_TicketDetail_eventName.findViewById(R.id.id_txtView_TicketDetail_eventName);
        id_tv_TicketDetail_eventCategory.findViewById(R.id.id_txtView_TicketDetail_ticketCategory);
        id_tv_TicketDetail_eventDesc.findViewById(R.id.id_txtView_TicketDetail_eventDescription);
        id_tv_TicketDetail_eventLocation.findViewById(R.id.id_txtView_TicketDetail_eventLocation);
        id_tv_TicketDetail_eventPrice.findViewById(R.id.id_txtView_TicketDetail_eventPrice);
        id_btn_TicketDetail_addTicket.findViewById(R.id.id_btn_TicketDetail_btnBuy);
        id_tv_TicketDetail_eventDate.findViewById(R.id.id_txtView_TicketDetail_eventDate);
        id_tv_TicketDetail_eventTime.findViewById(R.id.id_txtView_TicketDetail_eventTime);
        id_rv_TicketDetail_rvItems.findViewById(R.id.id_rv_TicketDetail_rvItems);


        eventName = intent.getStringExtra("eventName");
        eventCategory = intent.getStringExtra("eventCategory");
        eventDesc = intent.getStringExtra("eventDesc");
        eventLocation = intent.getStringExtra("eventLocation");
        eventPrice = intent.getStringExtra("eventPrice");
        eventImgFile = intent.getIntExtra("eventImg",0);
        eventDate = intent.getStringExtra("eventDate");
        eventTime = intent.getStringExtra("eventTime");
        final MyTicket myTicket = new MyTicket(eventName,eventCategory,eventDate,eventTime,eventDesc,eventImgFile,eventLocation);

        id_imgView_TicketDetail_eventImg.setImageResource(eventImgFile);
        id_tv_TicketDetail_eventName.setText(eventName);
        id_tv_TicketDetail_eventCategory.setText(eventCategory);
        id_tv_TicketDetail_eventDesc.setText(eventDesc);
        id_tv_TicketDetail_eventLocation.setText(eventLocation);
        id_tv_TicketDetail_eventPrice.setText(eventPrice);
        id_tv_TicketDetail_eventTime.setText(eventTime);
        id_tv_TicketDetail_eventDate.setText(eventDate);

        id_btn_TicketDetail_addTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser() != null)
                {
                    Intent intent1 = new Intent(TicketDetailActivity.this, TransactionSummary.class);
                    intent1.putExtra("myTicket", (Parcelable) myTicket);

                }else{
                    redirectToRegisterLogin();
                }
            }
        });
        id_rv_TicketDetail_rvItems.setLayoutManager(new LinearLayoutManager(this));
        id_rv_TicketDetail_rvItems.setAdapter(adapter);

    }
    private void redirectToRegisterLogin()
    {
        Intent intent = new Intent(TicketDetailActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}