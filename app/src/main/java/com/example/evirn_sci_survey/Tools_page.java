package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.CheckBox;

public class Tools_page extends AppCompatActivity {

    private Button toolsBTN;
    private CheckBox scissor_checkbox;
    private CheckBox tape_checkbox;
    private CheckBox screw_driver_checkbox;
    private CheckBox pen_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_page);

        //we will be seting up these checkboxes to make the students select at least one of them
        //before coninuing

        scissor_checkbox = findViewById(R.id.Scissors);
        tape_checkbox = findViewById(R.id.Tape);
        screw_driver_checkbox = findViewById(R.id.Screw_Driver);
        pen_checkbox = findViewById(R.id.Pen);


        if(scissor_checkbox.isChecked()){
            System.out.println("Working");
        }

        toolsBTN = (Button) findViewById(R.id.tool_page_button_next);
        toolsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent tools_i = new Intent(Tools_page.this, Enjoyment_level.class);
                startActivity(tools_i);
            }
        });
    }

    public void OnCheckboxClicked(View view){
        // we will check to see if the boxes are check and make the method continue working if possible
        boolean checked_Scissors = scissor_checkbox.isChecked();


    }
}