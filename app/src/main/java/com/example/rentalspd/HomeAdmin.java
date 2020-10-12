package com.example.rentalspd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeAdmin extends AppCompatActivity {
    Button us,tr,sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        us = findViewById(R.id.user);
        tr = findViewById(R.id.transaksi);
        sp = findViewById(R.id.sepeda);

        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdmin.this,ListdataActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}