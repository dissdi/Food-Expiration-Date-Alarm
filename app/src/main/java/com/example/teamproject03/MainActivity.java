package com.example.teamproject03;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.example.teamproject03.capture.CaptureActivity;
import com.example.teamproject03.data.DatabaseHelper;
import com.example.teamproject03.model.Food;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    FrameLayout parentLayout;
    ScrollView scrollView;
    public static LinearLayout linearLayout;
    LinearLayout addButton;
    public static LayoutInflater inflater;
    public static ArrayList<Food> foodList = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = (FrameLayout) findViewById(R.id.parentLayout);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        inflater = LayoutInflater.from(this);
        addButton = (LinearLayout) findViewById(R.id.addButton);
        dbHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent captureIntent = new Intent(getBaseContext(), CaptureActivity.class);
                startActivity(captureIntent);
            }
        }
        );
    }
}

