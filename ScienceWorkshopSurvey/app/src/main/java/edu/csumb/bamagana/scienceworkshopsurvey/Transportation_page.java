package edu.csumb.bamagana.scienceworkshopsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Transportation_page extends AppCompatActivity {

    private Button transportationBTN;
    private String answer_Question;
    private EditText editText_Transportation;

    //the interaction with the button and the field text can be done in this instances
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation_page);

        editText_Transportation = (EditText) findViewById(R.id.transportation_page) ;

        transportationBTN = (Button) findViewById(R.id.transportation_page_button_next);
        transportationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent tran_i = new Intent(Transportation_page.this, Tools_page.class);
                startActivity(tran_i);
            }
        });

        // calling the text thats been entred will be processed here if need to for information storing

        answer_Question = editText_Transportation.toString();


    }
}