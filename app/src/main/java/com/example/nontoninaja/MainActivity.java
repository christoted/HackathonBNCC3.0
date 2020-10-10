package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.example.nontoninaja.Adapter.AdapterMyTicket;
import com.example.nontoninaja.Model.MyTicket;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private AdapterMyTicket adapterMyTicket;
//    private String[] txtTitle;
//    private String[] txtContent;
//    private String[] txtDate;
//    private String[] txtTime;
//    private String[] txtType;
//    private TypedArray imgContent;
//    private TypedArray imgDate;
//    private TypedArray imgTime;
//    private TypedArray imgType;
//
//    private RecyclerView recyclerView;
//
//    public ArrayList<MyTicket> ticketArrayList = new ArrayList<>();

//    public void init(){
//        txtTitle = getResources().getStringArray(R.array.data_text_title);
//        txtContent = getResources().getStringArray(R.array.data_text_content);
//        txtDate = getResources().getStringArray(R.array.data_date);
//        txtTime = getResources().getStringArray(R.array.data_time);
//        txtType = getResources().getStringArray(R.array.data_type);
//        imgContent = getResources().obtainTypedArray(R.array.data_img_content);
//        imgDate = getResources().obtainTypedArray(R.array.data_img_date);
//        imgTime = getResources().obtainTypedArray(R.array.data_img_time);
//        imgType = getResources().obtainTypedArray(R.array.data_img_type);
//
//    }
//
//    public void addItem() {
//        if ( ticketArrayList.size() != 0) {
//            return;
//        } else {
//            for ( int i = 0; i < txtTitle.length; i++) {
//                MyTicket myTicket = new MyTicket();
//
//                myTicket.setImgContent(imgContent.getResourceId(i,-1));
//                myTicket.setImgDate(imgDate.getResourceId(i, -1));
//                myTicket.setImgTime(imgTime.getResourceId(i,-1));
//                myTicket.setImgType(imgType.getResourceId(i, -1));
//                myTicket.setTxtContent(txtContent[i]);
//                myTicket.setTxtDate(txtDate[i]);
//                myTicket.setTxtTime(txtTime[i]);
//                myTicket.setTxtTitle(txtTitle[i]);
//                myTicket.setTxtType(txtType[i]);
//
//                ticketArrayList.add(myTicket);
//
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.id_rv);
//
//        init();
//        addItem();
//
//        adapterMyTicket = new AdapterMyTicket(this, ticketArrayList);
//        recyclerView.setAdapter(adapterMyTicket);



    }

}