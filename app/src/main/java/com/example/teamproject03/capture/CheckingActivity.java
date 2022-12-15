package com.example.teamproject03.capture;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teamproject03.MainActivity;
import com.example.teamproject03.R;
import com.example.teamproject03.data.DBHelper;
import com.example.teamproject03.data.DBManager;
import com.example.teamproject03.data.DataHashMap;
import com.example.teamproject03.model.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CheckingActivity extends AppCompatActivity {
    LinearLayout scrollLinearLayout;
    TextView cancelButton;
    TextView recaptureButton;
    TextView addButton;
    LayoutInflater inflater;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_checking);

        scrollLinearLayout = (LinearLayout) findViewById(R.id.scrollLinearLayout);
        cancelButton = (TextView) findViewById(R.id.cancelButton);
        recaptureButton = (TextView) findViewById(R.id.recaptureButton);
        addButton = (TextView) findViewById(R.id.addButton);
        dbManager = new DBManager(this);

        ArrayList<String> captureList = (ArrayList<String>) getIntent().getSerializableExtra("captureList");
        inflater = LayoutInflater.from(this);
        ArrayList<LinearLayout> layoutList = new ArrayList<LinearLayout>();

        // first of all show capture list
        for(int i=0; i<captureList.size(); i++){
            LinearLayout foodLayout = (LinearLayout) inflater.inflate
                    (R.layout.capture_food_button, scrollLinearLayout, false);
            ((EditText)foodLayout.findViewById(R.id.name)).setText(captureList.get(i));
            ImageView deleteIcon = (ImageView)foodLayout.findViewById(R.id.delete);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scrollLinearLayout.removeView(foodLayout);
                }
            });

            Spinner spinner = (Spinner)foodLayout.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                    (this, R.array.storage_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            scrollLinearLayout.addView(foodLayout);
            layoutList.add(foodLayout);
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        recaptureButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // update database with each food's information(due date, storage type, etc...)
                ArrayList<Food> foodList = new ArrayList<>();
                int count = scrollLinearLayout.getChildCount();
                View v = null;

                for(int i=0; i<count; i++){
                    v = scrollLinearLayout.getChildAt(i);
                    String name = ((EditText)v.findViewById(R.id.name)).getText().toString();
                    String storageType = ((Spinner)v.findViewById(R.id.spinner)).getSelectedItem().toString();
                    boolean cautionChecked = ((CheckBox)v.findViewById(R.id.checkBox)).isChecked();
                    Food f = new Food(name);
                    f.setStorageType(storageType);
                    // Left date 몇일이었더라?
                    if(cautionChecked) f.setLeftDate(10);
                    f.setLeftDate(getLeftDate(name));
                    foodList.add(f);
                }

                // add SQL food items
                DBHelper foodDBHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = foodDBHelper.getWritableDatabase();
                Collections.sort(foodList, new Comparator<Food>() {
                    @Override
                    public int compare(Food o1, Food o2) {
                        return o1.getLeftDate().compareTo(o2.getLeftDate());
                    }
                });
                for(Food f : foodList){
                    dbManager.insert(f);
                }
                startActivity(intent);
            }
        });

    }

    int getLeftDate(String name){
        // 함수 구현 필요 mapping해서 거시기하기
        DataHashMap dataHM = new DataHashMap();
        return dataHM.getLeftDate(name);
    }
}
