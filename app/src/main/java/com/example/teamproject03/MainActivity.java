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
import com.example.teamproject03.model.Food;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
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

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // get random food
//                ArrayList<Object> ls = getRandomFood();
//                Food f = new Food((String)ls.get(0), (int)ls.get(1),
//                        (int)ls.get(2), (int)ls.get(3), (String)ls.get(4));

                //
                Intent captureIntent = new Intent(getBaseContext(), CaptureActivity.class);
                startActivity(captureIntent);
                


            }
        }


        );
    }

    // random food generator
    public ArrayList<Object> getRandomFood(){
        Random random = new Random();
        String name = random.ints(97, 122 + 1)
                .limit(4)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        int curDate = ThreadLocalRandom.current().nextInt(0, 0 + 30);
        int due = ThreadLocalRandom.current().nextInt(20, 0 + 30);
        int left = due-curDate;
        String storage = random.ints(97, 99 + 1)
                .limit(4)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        ArrayList<Object> list = new ArrayList<>();
        list.add(name); list.add(left); list.add(curDate); list.add(due); list.add(storage);
        return list;
    }
}

