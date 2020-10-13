package com.bignerdranch.android.receivemessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_RECEIVE_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if permission is not granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            // if is not granted check if the user denied
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                // Do nothing as user has denied
            } else {
                // pop up asking for permision
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_RECEIVE_SMS);

            }
        }
    }//onCreate

    //after getting the result of permission request the result will be passed to this metod

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case MY_PERMISSIONS_RECEIVE_SMS:
            {
                //check wether the length of grantREsults is greater than 0 and is equal to PERMISSION_GRANTEd
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // now broadcastreceiver will work in background
                    Toast.makeText(this, "Thanks for permitting!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Well, I can't do anything until you permit me", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}