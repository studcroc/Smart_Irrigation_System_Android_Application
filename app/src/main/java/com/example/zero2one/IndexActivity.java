package com.example.zero2one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.net.NetworkInterface;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    // TO OPEN activity_smart_control_system WHEN RESPECTIVE BUTTON IS CLICKED
    public void openSmartControlSystemActivity(View view) {
        if(checkNetwok())
        {
            Intent i = new Intent(this,smartControlSystem.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Turn ON Mobile Data",Toast.LENGTH_LONG).show();
        }
    }
    // TO INFLATE THE OPTIONS MENU LAYOUT IN firstActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }
    // TO IMPLEMENT WORKING TO EACH ITEM IN OPTIONS MENU
    // i.e ON CLICKING THEM, WHAT TO BE DONE
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings_item_index_activity:
                //TODO IMPLEMENT showWateringHistory() METHOD
                Toast.makeText(this,"Clicked Settings Icon",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    // TO OPEN activity_weather_forecast WHEN RESPECTIVE BUTTON IS CLICKED
    public void openWeatherForecastActivity(View view) {
        if(checkNetwok())
        {
            Intent i = new Intent(this,WeatherForecastActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Turn ON Mobile Data",Toast.LENGTH_LONG).show();
        }
    }

    public void openDialer() {
        Uri phone_no = Uri.parse("tel:18001801551");
        Intent i = new Intent(Intent.ACTION_DIAL, phone_no);
        startActivity(i);
    }

    public void openHelpMenu(View view) {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set Alert Title
        builder.setTitle("KISHAN CALL CENTRE");
        // Set the message show for the Alert time
        builder.setMessage("Do you want to call ?");
        // Set the positive button with Call name OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                // When the user click Call button then Dialer will open
                openDialer();}
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
    public boolean checkNetwok()
    {
        //Checking network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void openIrrigationNewsActivity(View view) {
        if(checkNetwok())
        {
            Intent i = new Intent(this,IrrigationGuidelinesActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Turn ON Mobile Data",Toast.LENGTH_LONG).show();
        }
    }
}
