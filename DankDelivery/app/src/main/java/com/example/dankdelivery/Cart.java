package com.example.dankdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Check");
        Button checkout_btn = (Button)findViewById(R.id.button_Buy);
        checkout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openStrainInformation();
            }
        });
    }
    private void openStrainInformation() {
        Intent intent = new Intent(Cart.this, OrderPlaced.class);
        startActivity(intent);
    }

}
