package com.example.dankdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StrainsNearYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strains_near_you);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_strain1 = (Button)findViewById(R.id.button_strain1);

        btn_strain1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openStrainInformation();
            }
        });

    }

    private void openStrainInformation() {
            Intent intent = new Intent(StrainsNearYou.this, StrainInformation.class);
            startActivity(intent);
            }
}
