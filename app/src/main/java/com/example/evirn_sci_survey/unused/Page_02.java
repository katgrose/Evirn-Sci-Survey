package com.example.evirn_sci_survey.unused;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
/*import android.database;
import android.database.sqlite;*/
import android.content.Intent;
import android.widget.Button;

import com.example.evirn_sci_survey.R;

public class Page_02 extends AppCompatActivity {

     private Button page02_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_02);

        page02_btn = (Button) findViewById(R.id.second_page_button_next);
        page02_btn.setOnClickListener(v -> {
            Intent p2I = new Intent(Page_02.this, Transportation_page.class);
            startActivity(p2I);
        });

    }
}