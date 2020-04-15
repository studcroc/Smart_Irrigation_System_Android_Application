package com.example.zero2one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class smartControlSystem extends AppCompatActivity {

    Switch sw;
    TextView temp_text_view,moist_text_view;
    ProgressBar temp_spinner,moist_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_control_system);
        temp_text_view = (TextView)findViewById(R.id.temperature_text_view);
        moist_text_view = (TextView)findViewById(R.id.moisture_text_view);
        sw = (Switch)findViewById(R.id.water_pump_switch);
        temp_spinner = (ProgressBar)findViewById(R.id.temperature_spinner);
        moist_spinner = (ProgressBar)findViewById(R.id.moisture_spinner);
    }
    // TO INFLATE THE OPTIONS MENU LAYOUT IN firstActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }
    // TO IMPLEMENT WORKING TO EACH ITEM IN OPTIONS MENU
    // i.e ON CLICKING THEM, WHAT TO BE DONE
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.watering_history_menu_item:
                //TODO IMPLEMENT showWateringHistory() METHOD
                showWateringHistory();
                break;
            case R.id.settings_menu_item:
                //TODO IMPLEMENT showSettings() METHOD
                Toast.makeText(this,"Clicked Settings",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void showWateringHistory() {
        Intent i  = new Intent(this,WateringHistory.class);
        startActivity(i);
    }

    // TO OPEN AlarmManagerActivity
    public void openAlarmManager(View view) {
        Intent i = new Intent(this,AlarmManagerActivity.class);
        startActivity(i);
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRootRef = database.getReference("water-pump-status");
    DatabaseReference myTemperatureRef = database.getReference("temperature");
    DatabaseReference myMoistureRef = database.getReference("moisture-status");
    {
        myTemperatureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("DEBUG_MESSAGE: ",value);
                temp_spinner.setVisibility(View.INVISIBLE);
                temp_text_view.setText(value+"° C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myMoistureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("DEBUG_MESSAGE: ",value);
                moist_spinner.setVisibility(View.INVISIBLE);
                moist_text_view.setText(value.toUpperCase());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("DEBUG_MESSAGE: ",value);
               if(value.equals("ON"))
                {
                    sw.setChecked(true);
                    Boolean btn_state = sw.isChecked();
                    Log.d("BUTTON_STATE: ",btn_state.toString());
                }
                else
                {
                    sw.setChecked(false);
                    Boolean btn_state = sw.isChecked();
                    Log.d("BUTTON_STATE: ",btn_state.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void openWaterPump(View view) {
        if(sw.isChecked())
        {
            myRootRef.setValue("ON");
        }
        else
        {
            myRootRef.setValue("OFF");
        }
    }
}
