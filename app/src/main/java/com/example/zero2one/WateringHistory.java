package com.example.zero2one;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WateringHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    customAdapter adapter ;
    //Making DEMO Data
    static ArrayList<WateringData> wateringData = new ArrayList<WateringData>();
    {
        for(int i = 0; i < 10; i++)
        {
            WateringData tmp = new WateringData();
            tmp.setDate_day((i+10)+"-04-2020");
            tmp.setOn_time((i+1)+":00");
            tmp.setOff_time((i+2)+":00");
            wateringData.add(tmp);
        }
    }
    /*
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference wateringDataRef = database.getReference("watering-history");
    DatabaseReference dateRef = database.getReference("watering-history/date");
    DatabaseReference onTimeRef = database.getReference("watering-history/on-time");
    DatabaseReference offTimeRef = database.getReference("watering-history/off-time");
    {
        final WateringData tmp = new WateringData();
        wateringDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tmp.setDate_day(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tmp.setDate_day(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        onTimeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tmp.setOn_time(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        offTimeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tmp.setOff_time(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        wateringData.add(tmp);
    }

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watering_history);
        recyclerView = (RecyclerView)findViewById(R.id.history_recycler_view);
        adapter = new customAdapter(this,wateringData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
