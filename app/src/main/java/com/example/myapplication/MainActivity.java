package com.example.myapplication;

import static com.example.myapplication.Signup.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FrientAdapter frientAdapter;
    List<FrientList> frientListList=new ArrayList<>();

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String FNAME_KEY = "fname_key";
    public static final String LNAME_KEY = "lname_key";
    SharedPreferences sharedpreferences;
    String fname,lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        fname = sharedpreferences.getString("fname_key", "");
        lname = sharedpreferences.getString("lname_key", "");
        TextView tv_fname = findViewById(R.id.tv_name);
        tv_fname.setText(fname+" "+lname);
        TextView logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(MainActivity.this, SplashScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("friends", null);
        Type type = new TypeToken<ArrayList<FrientList>>() {}.getType();
        frientListList = gson.fromJson(json, type);
        if (frientListList == null) {
            frientListList = new ArrayList<>();
        }

        frientAdapter=new FrientAdapter(frientListList,this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(frientAdapter);
    }

    public void add(View view) {
        Intent intent=new Intent(MainActivity.this,AddFriend.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}