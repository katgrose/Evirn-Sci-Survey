package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, LoginActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);

        mUsername = findViewById(R.id.usernameEditText);
        mPassword = findViewById(R.id.passwordEditText);
        mLoginButton = findViewById(R.id.loginButton);


        mLoginButton.setOnClickListener(view -> {
            String currentUsername = mUsername.getText().toString();
            String currentPassword = mPassword.getText().toString();
            if(currentUsername.equals("admin") && currentPassword.equals("scienceWorkshop")){
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.saved_logged_in_key), true);
                editor.apply();
                startActivity(AdminActivity.getIntent(LoginActivity.this));
            }
        });
    }
}