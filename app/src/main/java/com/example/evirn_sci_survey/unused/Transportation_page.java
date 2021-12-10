package com.example.evirn_sci_survey.unused;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.evirn_sci_survey.R;

public class Transportation_page extends AppCompatActivity {

    private Button transportationBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation_page);

        transportationBTN = (Button) findViewById(R.id.transportation_page_button_next);
        transportationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent tran_i = new Intent(Transportation_page.this, Tools_page.class);
                startActivity(tran_i);
            }
        });
    }
}