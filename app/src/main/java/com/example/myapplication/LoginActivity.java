package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String FNAME_KEY = "fname_key";
    public static final String LNAME_KEY = "lname_key";
    SharedPreferences sharedpreferences;
    String fname, lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText fnameEdt = findViewById(R.id.username);
        EditText lnameEdt = findViewById(R.id.pass);
        Button loginBtn = findViewById(R.id.button);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        fname = sharedpreferences.getString("fname_key", null);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(fnameEdt.getText().toString()) && TextUtils.isEmpty(lnameEdt.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(FNAME_KEY, fnameEdt.getText().toString());
                    editor.apply();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}