package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Signup extends AppCompatActivity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String FNAME_KEY = "fname_key";
    public static final String LNAME_KEY = "lname_key";
    SharedPreferences sharedpreferences;
    String fname, lname;
    EditText gender,date1;
    private int year;
    private int month;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText fnameEdt = findViewById(R.id.fname);
        EditText lnameEdt = findViewById(R.id.lname);
        gender = findViewById(R.id.gender);
        date1 = findViewById(R.id.date);
        Button loginBtn = findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        fname = sharedpreferences.getString("fname_key", null);
        lname = sharedpreferences.getString("lname_key", null);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(fnameEdt.getText().toString()) && TextUtils.isEmpty(lnameEdt.getText().toString())) {
                    Toast.makeText(Signup.this, "Please Enter First and Last Name", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(FNAME_KEY, fnameEdt.getText().toString());
                    editor.putString(LNAME_KEY, lnameEdt.getText().toString());
                    editor.apply();
                    Intent i = new Intent(Signup.this, MainActivity.class);
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

    public void gender(View view) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Signup.this);
        builderSingle.setTitle("Select Gender");
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(Signup.this, android.R.layout.simple_list_item_1);
        arrayAdapter1.add("Male");
        arrayAdapter1.add("Female");
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle.setAdapter(arrayAdapter1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter1.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(Signup.this);
                builderInner.setMessage(strName);
                gender.setText(strName);
            }
        });
        builderSingle.show();
    }

    public void ondate(View view) {

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog dd = new android.app.DatePickerDialog(this,
                new android.app.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            Date date = formatter.parse(dateInString);
                            date1.setText(formatter.format(date));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, year, month, day);

        dd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dd.show();
    }
}