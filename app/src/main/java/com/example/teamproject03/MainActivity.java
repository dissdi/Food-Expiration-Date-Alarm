package com.example.teamproject03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.RecyclerView;
import com.example.teamproject03.capture.CaptureActivity;
import com.example.teamproject03.data.DBHelper;
import com.example.teamproject03.data.DBManager;
import com.example.teamproject03.data.FoodAdapter;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private DBManager dbManager;
    LinearLayout addButton;
    public static LayoutInflater inflater;
    public RecyclerView recyclerView;
    public FoodAdapter foodAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        inflater = LayoutInflater.from(this);
        addButton = (LinearLayout) findViewById(R.id.addButton);
        dbHelper = new DBHelper(this);
        dbManager = new DBManager(this);
        foodAdapter = new FoodAdapter(dbHelper, dbManager);
        recyclerView.setAdapter(foodAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent captureIntent = new Intent(getBaseContext(), CaptureActivity.class);
                startActivity(captureIntent);
            }
        });


    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        FoodListInfo foodListInfo = FoodListInfo.getInstance();
        foodAdapter.addFoods(foodListInfo.getFoodList());
        foodAdapter.notifyDataSetChanged();
    }
}

