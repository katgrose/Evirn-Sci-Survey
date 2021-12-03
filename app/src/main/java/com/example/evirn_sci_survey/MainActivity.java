package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
/*import android.database;
import android.database.sqlite;*/
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);
        int activeSurvey = sharedPref.getInt(getString(R.string.saved_active_survey_key), 1);

        mFirstName = findViewById(R.id.User_Name);
        Button nextButton = findViewById(R.id.main_button_next);

        nextButton.setOnClickListener(v -> {
            if(mFirstName.getText().toString().equals("admin")) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.saved_logged_in_key), true);
                editor.apply();
                startActivity(AdminActivity.getIntent(MainActivity.this));
            } else {
                Intent intent = new Intent(MainActivity.this, Page_02.class);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }
}