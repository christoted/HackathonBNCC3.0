package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nontoninaja.Model.MyTicket;
import com.google.firebase.auth.FirebaseAuth;

import java.text.NumberFormat;
import java.util.Locale;

public class TicketDetailActivity extends AppCompatActivity {

    ImageView id_imgView_TicketDetail_eventImg;
    TextView id_tv_TicketDetail_eventName;
    TextView id_tv_TicketDetail_eventCategory;
    TextView id_tv_TicketDetail_eventPrice;
    TextView id_tv_TicketDetail_eventDate;
    TextView id_tv_TicketDetail_eventTime;
    TextView id_tv_TicketDetail_eventDesc;
    TextView id_tv_TicketDetail_eventLocation;

    Button id_btn_TicketDetail_addTicket;
    Button id_btn_TicketDetail_btnBack;
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        Intent intent = getIntent();
        initItems(intent);
    }

    private void initItems(Intent intent) {

        id_imgView_TicketDetail_eventImg = findViewById(R.id.id_img_TicketDetail_eventImage);
        id_tv_TicketDetail_eventName = findViewById(R.id.id_txtView_TicketDetail_eventName);
        id_tv_TicketDetail_eventCategory = findViewById(R.id.id_txtView_TicketDetail_ticketCategory);
        id_tv_TicketDetail_eventDesc = findViewById(R.id.id_txtView_TicketDetail_eventDescription);
        id_tv_TicketDetail_eventLocation = findViewById(R.id.id_txtView_TicketDetail_eventLocation);
        id_tv_TicketDetail_eventPrice = findViewById(R.id.id_txtView_TicketDetail_eventPrice);
        id_btn_TicketDetail_addTicket = findViewById(R.id.id_btn_TicketDetail_btnBuy);
        id_tv_TicketDetail_eventDate = findViewById(R.id.id_txtView_TicketDetail_eventDate);
        id_tv_TicketDetail_eventTime = findViewById(R.id.id_txtView_TicketDetail_eventTime);
        id_btn_TicketDetail_btnBack = findViewById(R.id.id_btn_TicketDetail_btnBack);




      final MyTicket myTicket = intent.getParcelableExtra("myTicket");
        Log.d("Tes gambar",""+myTicket.getImgEvent());
        Glide.with(TicketDetailActivity.this)
                .load(myTicket.getImgEvent())
                .into(id_imgView_TicketDetail_eventImg);

        id_tv_TicketDetail_eventName.setText(myTicket.getTxtTitle());
        id_tv_TicketDetail_eventCategory.setText(myTicket.getTxtCategory());
        id_tv_TicketDetail_eventDesc.setText(myTicket.getTxtDescription());
        id_tv_TicketDetail_eventLocation.setText(myTicket.getTxtLocation());
        id_tv_TicketDetail_eventPrice.setText("Rp "+NumberFormat.getNumberInstance(Locale.FRANCE).format(new Double(myTicket.getTxtPriceReguler()))+" - Rp "+NumberFormat.getNumberInstance(Locale.FRANCE).format(new Double(myTicket.getTxtPriceVVIP())) );
        id_tv_TicketDetail_eventTime.setText(myTicket.getTxtTime());
        id_tv_TicketDetail_eventDate.setText(myTicket.getTxtDate());

        id_btn_TicketDetail_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        id_btn_TicketDetail_addTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TicketDetailActivity.this, TransactionSummary.class);
                intent1.putExtra("myTicket",myTicket);
                startActivity(intent1);
//                if(mAuth.getCurrentUser() != null)
//                {
//                    Intent intent1 = new Intent(TicketDetailActivity.this, TransactionSummary.class);
//                    intent1.putExtra("myTicket",myTicket);
//                }else{
//                    redirectToRegisterLogin();
//                }
            }
        });
//        id_rv_TicketDetail_rvItems.setLayoutManager(new LinearLayoutManager(this));
        //id_rv_TicketDetail_rvItems.setAdapter(adapter);

    }
    private void redirectToRegisterLogin()
    {
      //  Intent intent = new Intent(TicketDetailActivity.this,RegisterActivity.class);
     //   startActivity(intent);
    }
}