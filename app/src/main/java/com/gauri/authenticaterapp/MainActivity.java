package com.gauri.authenticaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//here logout user from firebase and send to login Activity
        Intent intent = new Intent(MainActivity.this,Login.class);
        startActivity(intent);
        finish();
    }
}
