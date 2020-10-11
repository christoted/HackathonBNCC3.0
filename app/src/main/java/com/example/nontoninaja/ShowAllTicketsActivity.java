package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.nontoninaja.Adapter.AdapterShowAll;
import com.example.nontoninaja.Model.MyTicket;

import java.util.ArrayList;

public class ShowAllTicketsActivity extends AppCompatActivity {

    RecyclerView id_rv_ShowAllTickets_rvAllTickets;
    ArrayList<MyTicket> myTicketArrayList;
    AdapterShowAll adapterShowAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_tickets);

        Intent intent = getIntent();
        myTicketArrayList = intent.getParcelableArrayListExtra("listTickets");
        id_rv_ShowAllTickets_rvAllTickets = findViewById(R.id.id_rv_ShowAllTickets_rvAllTickets);
        adapterShowAll = new AdapterShowAll(ShowAllTicketsActivity.this,myTicketArrayList);
        id_rv_ShowAllTickets_rvAllTickets.setLayoutManager(new LinearLayoutManager(ShowAllTicketsActivity.this,LinearLayoutManager.HORIZONTAL,false));
        id_rv_ShowAllTickets_rvAllTickets.setAdapter(adapterShowAll);
    }
}