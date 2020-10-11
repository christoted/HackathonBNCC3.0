package com.example.nontoninaja.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nontoninaja.Activity.LoginActivity;
import com.example.nontoninaja.Activity.MainActivity;
import com.example.nontoninaja.Activity.ProfileDetailActivity;
import com.example.nontoninaja.Adapter.AdapterMyTicket;
import com.example.nontoninaja.Database.CloudFireStore;
import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.nontoninaja.Database.CloudFireStore.ticketArrayList;


public class HomeFragment extends Fragment {

    CloudFireStore db = new CloudFireStore();
    RecyclerView recyclerView;
    ImageButton imageButton;
    AdapterMyTicket adapterMyTicket;
    ArrayList<MyTicket> ticketArrayLists;
    private void initView(View view)  {
        recyclerView = view.findViewById(R.id.id_rv_fragment_home_yourConcert);
        imageButton = view.findViewById(R.id.id_imgBtn_fragment_home_profilePicture);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
       initView(view);

       initializeView();

     //  db.readConcert(getActivity());

       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       Log.d("12345", "onCreateView: " + recyclerView);

       imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(v.getContext(), ProfileDetailActivity.class));
           }
       });

       return view;
    }

    public void initializeView()   {
        ticketArrayLists = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference concertsCollectionRef = db.collection("concerts");

            Query concertsQuery = concertsCollectionRef;

            concertsQuery
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
//                                ticketArrayList.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    MyTicket myTicket = document.toObject(MyTicket.class);
                                    myTicket.setTxtTitle(document.getData().get("title").toString());
                                    myTicket.setTxtTime(document.getData().get("time").toString());
                                    myTicket.setTxtLocation(document.getData().get("location").toString());
                                    myTicket.setTxtPriceReguler(document.getData().get("priceReguler").toString());
                                    myTicket.setTxtPriceVIP(document.getData().get("priceVIP").toString());
                                    myTicket.setTxtPriceVVIP(document.getData().get("priceVVIP").toString());
//                                    myTicket.setLikes(document.getData().get("likes").toString());
//                                    Log.d("12345", "onComplete: Likes" + document.getData().get("likes").toString());
                                    myTicket.setTxtDate(document.getData().get("date").toString());
                                    myTicket.setImgEvent(document.getData().get("image").toString());
                                    myTicket.setTxtCategory(document.getData().get("category").toString());


                                    ticketArrayLists.add(myTicket);
                                    Log.d("555", "onComplete: ini Home Fragment" + ticketArrayLists.size());
                                }
                                adapterMyTicket = new AdapterMyTicket(getActivity(), ticketArrayLists);
                                recyclerView.setAdapter(adapterMyTicket);
                            } else {
                                Log.w("error", "Error getting documents.", task.getException());
                            }
                        }
                    });

    }
}