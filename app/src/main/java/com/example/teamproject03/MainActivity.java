package com.example.teamproject03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.RecyclerView;
import com.example.teamproject03.capture.CaptureActivity;
import com.example.teamproject03.data.DBHelper;
import com.example.teamproject03.data.DBManager;
import com.example.teamproject03.data.FoodAdapter;
import androidx.annotation.RequiresApi;
import com.example.teamproject03.model.Food;

import java.util.Collections;
import java.util.Comparator;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private DBManager dbManager;
    LinearLayout addButton;
    public static LayoutInflater inflater;
    public LinearLayout parentLayout;
    public RecyclerView recyclerView;
    public FoodAdapter foodAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        inflater = LayoutInflater.from(this);
        addButton = (LinearLayout) findViewById(R.id.addButton);
        dbHelper = new DBHelper(this);
        dbManager = new DBManager(this);
        foodAdapter = new FoodAdapter(dbHelper, dbManager);
        Collections.sort(foodAdapter.list, new Comparator<Food>(){
            @Override
            public int compare(Food f1, Food f2){
                return f1.getLeftDate().compareTo(f2.getLeftDate());
            }
        });
        recyclerView.setAdapter(foodAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent captureIntent = new Intent(getBaseContext(), CaptureActivity.class);
                startActivity(captureIntent);
            }
        });

//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @SuppressLint("DefaultLocale")
//                    @Override public void onItemClick(View view, int position) {
//                        Food f = foodAdapter.list.get(position);
//                        LinearLayout foodInfoLayout = (LinearLayout) inflater.inflate(R.layout.food_information, parentLayout);
//                        LinearLayout linearLayout = (LinearLayout)foodInfoLayout.findViewById(R.id.linearLayout);
//                        LinearLayout base = (LinearLayout) linearLayout.getChildAt(0);
//                        ((TextView)base.getChildAt(0)).setText(f.getName());
//                        ((TextView)base.getChildAt(1)).setText(f.getDescription());
//                        // setting second linear layout in food_information
//                        // second one
//                        base = (LinearLayout) linearLayout.getChildAt(1);
//                        ((TextView)base.getChildAt(1)).setText(f.getStorageType().toString());
//                        // third one
//                        base = (LinearLayout) linearLayout.getChildAt(2);
//                        ((TextView)base.getChildAt(1)).setText(String.format("%d일", f.getLeftDate()));
//                        parentLayout.addView(foodInfoLayout);
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        // do whatever
//                    }
//                })
//        );
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

