 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddFriend extends AppCompatActivity {
    private EditText courseNameEdt, courseDescEdt;
    private Button  saveBtn;
    private RecyclerView courseRV;
    private FrientAdapter adapter;
    private List<FrientList> courseModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescEdt = findViewById(R.id.idEdtCourseDescription);
        saveBtn = findViewById(R.id.idBtnSave);
        courseRV = findViewById(R.id.idRVCourses);
        loadData();
        buildRecyclerView();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(courseNameEdt.getText().toString()) && TextUtils.isEmpty(courseDescEdt.getText().toString())) {
                    Toast.makeText(AddFriend.this, "Please Enter First and Last Name", Toast.LENGTH_SHORT).show();
                } else {
                    courseModalArrayList.add(new FrientList(courseNameEdt.getText().toString(), courseDescEdt.getText().toString(),R.drawable.girl));
                    adapter.notifyItemInserted(courseModalArrayList.size());
                    saveData();
                }

            }
        });
    }

    private void buildRecyclerView() {
        adapter = new FrientAdapter(courseModalArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("friends", null);
        Type type = new TypeToken<ArrayList<FrientList>>() {}.getType();
        courseModalArrayList = gson.fromJson(json, type);
        if (courseModalArrayList == null) {
            courseModalArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courseModalArrayList);
        editor.putString("friends", json);
        editor.apply();
        Toast.makeText(this, "Added Friend Successfully. ", Toast.LENGTH_SHORT).show();
        courseNameEdt.setText("");
        courseDescEdt.setText("");
    }
}