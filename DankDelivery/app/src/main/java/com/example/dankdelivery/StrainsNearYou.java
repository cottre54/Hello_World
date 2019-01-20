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
import android.widget.ImageButton;
import android.widget.TextView;

public class StrainsNearYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strains_near_you);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Strains Near You");

        ImageButton btn_strain = (ImageButton) findViewById(R.id.button_Strain);
        ImageButton btn_strain1 = (ImageButton) findViewById(R.id.button_Strain1);
        ImageButton btn_strain2 = (ImageButton) findViewById(R.id.button_Strain2);
        Button btn_filter = (Button) findViewById(R.id.button_Filter);

        btn_strain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStrainInformation();
            }
        });
        btn_strain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStrainInformation1();
            }
        });

        btn_strain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStrainInformation2();
            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilterResults();
            }
        });
        Intent intent = getIntent();
        String text = ((Intent) intent).getStringExtra(Address_Page.EXTRA_TEXT);

        TextView textView1 = (TextView) findViewById(R.id.textView);

        textView1.setText("Strains near " +text);
    }

    private void openStrainInformation() {
        Intent intent = new Intent(StrainsNearYou.this, StrainInformation.class);
        startActivity(intent);
    }

    private void openStrainInformation1() {
        Intent intent = new Intent(StrainsNearYou.this, StrainInformation1.class);
        startActivity(intent);
    }

    private void openStrainInformation2() {
        Intent intent = new Intent(StrainsNearYou.this, StrainInformation2.class);
        startActivity(intent);
    }



    private void openFilterResults(){
        Intent intent = new Intent(StrainsNearYou.this, FiltersPage.class);
        startActivity(intent);
    }
}
