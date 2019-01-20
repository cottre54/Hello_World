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
import android.widget.EditText;

public class Address_Page extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign in");

        configureNextButton();

    }
    private void configureNextButton() {
        Button nextButton = (Button) findViewById(R.id.button_FindStrainsNearYou);
        Button sellerButton = (Button) findViewById(R.id.sellerButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAddress();
            }
        });

        sellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Address_Page.this, SellerSignIn.class));
            }
        });

    }
    public void sendAddress(){
        EditText editText1 = (EditText) findViewById(R.id.text_Address);
        String text = editText1.getText().toString();

        Intent intent = new Intent(this, StrainsNearYou.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
            }
}

