package edu.csumb.bamagana.scienceworkshopsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class Tools_page extends AppCompatActivity {

    private Button toolsBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_page);

//        toolsBTN = (Button) findViewById(R.id.transportation_page_button_next);
//        toolsBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                Intent tools_i = new Intent(Tools_page.this, Tools_page.class);
//                startActivity(tools_i);
//            }
//        });
    }
}