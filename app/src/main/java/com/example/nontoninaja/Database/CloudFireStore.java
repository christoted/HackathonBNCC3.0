package com.example.nontoninaja.Database;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nontoninaja.Model.MyTicket;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CloudFireStore  {
    public static ArrayList<MyTicket> ticketArrayList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference concertsCollectionRef = db.collection("concerts");

    private DocumentSnapshot mLastQueriedDocument;

    public void readConcert(Context context) {

        Query concertsQuery;

        if ( mLastQueriedDocument != null) {
            concertsQuery = concertsCollectionRef.startAfter(mLastQueriedDocument);
        } else {
            concertsQuery = concertsCollectionRef;
        }

        concertsQuery
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ticketArrayList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                           //     Log.d("success", document.getId() + " => " + document.getData().get("title"));
                                MyTicket myTicket = document.toObject(MyTicket.class);
                                myTicket.setTxtTitle(document.getData().get("title").toString());
                                myTicket.setTxtTime(document.getData().get("time").toString());
                                myTicket.setTxtLocation(document.getData().get("location").toString());
                                myTicket.setTxtCategory(document.getData().get("category").toString());

                                if ( task.getResult().size() != 0) {
                                    mLastQueriedDocument = task.getResult().getDocuments().get(task.getResult().size()-1);
                                }

                                ticketArrayList.add(myTicket);
                                Log.d("size", "onComplete: " + ticketArrayList.size());
                            }

                        } else {
                            Log.w("error", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
