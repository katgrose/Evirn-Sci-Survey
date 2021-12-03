package edu.csumb.bamagana.scienceworkshopsurvey;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
/*import android.database;
import android.database.sqlite;*/
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;


import android.os.Bundle;

public class Page_02 extends AppCompatActivity {

     private Button page02_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_02);

        page02_btn = (Button) findViewById(R.id.second_page_button_next);
        page02_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent p2I = new Intent(Page_02.this, Transportation_page.class);
                startActivity(p2I);
            }
        });

    }
}