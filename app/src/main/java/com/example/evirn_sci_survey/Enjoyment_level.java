package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Enjoyment_level extends AppCompatActivity {

    TextView number_string;
    String status;
    String emotion;

    private Button enjoyButton;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoyment_level);

        seekBar = (SeekBar) findViewById(R.id.seekBar_0_5);
        number_string = (TextView) findViewById(R.id.number_string);
        enjoyButton = (Button) findViewById(R.id.Enjoyment_button);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 5){
                    status = "5";
                    emotion = "Awesome";
                }else if (progress >= 4){
                    status = "4";
                    emotion = "Good";
                }else if (progress >= 3){
                    status = "3";
                    emotion = "Neutral";
                }else if (progress >= 2){
                    status = "2";
                    emotion = "Ok";
                }else if(progress >= 1){
                    status = "1";
                    emotion = "Poor";
                }else{
                    status = "0";
                    emotion = "Bad";
                }
                number_string.setText(emotion + " : " + status);
                number_string.setVisibility(View.VISIBLE);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        enjoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent_enjoy = new Intent(Enjoyment_level.this, Camera_Interaction.class);
                startActivity(intent_enjoy);
            }
        });

    }


}