package com.example.teamproject03;

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

import androidx.core.content.ContextCompat;
import com.example.teamproject03.model.food;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    FrameLayout parentLayout;
    ScrollView scrollView;
    LinearLayout linearLayout;
    LinearLayout addButton;

    LayoutInflater inflater;
    ArrayList<food> foodList = new ArrayList<food>();
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
                ArrayList<Object> ls = getRandomFood();
                food f = new food((String)ls.get(0), (int)ls.get(1),
                        (int)ls.get(2), (int)ls.get(3), (String)ls.get(4));

                // Order foods by their left date
                // get right index for new food
                int index = 0;
                if(foodList.isEmpty()) foodList.add(f);
                else {
                    Comparator<food> fComparator = new Comparator<food>() {
                        @Override
                        public int compare(food f1, food f2) {
                            if(f1.getLeftDate()==f2.getLeftDate())
                                return f1.getName().compareTo(f2.getName());
                            return f1.getLeftDate().compareTo(f2.getLeftDate());
                        }
                    };
                    index = Collections.binarySearch(foodList, f, fComparator);
                    if(index < 0) {
                        index *= -1;
                        index--;
                    }
                    foodList.add(index, f);
                }

                // add food
                LinearLayout foodLayout;
                if(f.getLeftDate()>=20){foodLayout = (LinearLayout) inflater.inflate
                        (R.layout.food_button_basic, linearLayout, false);}
                else if(f.getLeftDate()>=10){foodLayout = (LinearLayout) inflater.inflate
                        (R.layout.food_button_careful, linearLayout, false);}
                else if(f.getLeftDate()>=5){foodLayout = (LinearLayout) inflater.inflate
                        (R.layout.food_button_caution, linearLayout, false);}
                else if(f.getLeftDate()>=0){foodLayout = (LinearLayout) inflater.inflate
                        (R.layout.food_button_danger, linearLayout, false);}
                else {foodLayout = (LinearLayout) inflater.inflate
                        (R.layout.food_button_rotten, linearLayout, false);}

                ((TextView)foodLayout.getChildAt(1)).setText(String.format("%d일",f.getLeftDate()));
                ((TextView)foodLayout.getChildAt(2)).setText(String.valueOf(f.getName()));

               // set Tag as hash code
                Integer h = f.hashCode();
                f.setCode(h);
                foodLayout.setTag(h);
                linearLayout.addView(foodLayout, index);

                // when food layout clicked:
                foodLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout foodInfoLayout;
                        if(f.getLevel() >= 2) {
                            foodInfoLayout = (LinearLayout) inflater.inflate
                                    (R.layout.food_information, linearLayout, false);
                        } else {
                            foodInfoLayout = (LinearLayout) inflater.inflate
                                    (R.layout.food_information_text_color_black, linearLayout, false);
                        }
                        LinearLayout base = (LinearLayout)foodInfoLayout.getChildAt(0);
                        base.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(f.getColor())));
                        // setting first linear layout in food_information.xml
                        LinearLayout ll = (LinearLayout)base.getChildAt(0);
                        ((TextView)ll.getChildAt(0)).setText(f.getName());
                        ((TextView)ll.getChildAt(1)).setText("bla bla bla bla");
                        ((TextView)ll.getChildAt(2)).setText(f.getDescription());
                        // setting second linear layout in food_information
                        ll = (LinearLayout)base.getChildAt(1);
                        ((TextView)ll.getChildAt(1)).setText(String.format("%d일",f.getBuyDate()));
                        // third one
                        ll = (LinearLayout) base.getChildAt(2);
                        ((TextView)ll.getChildAt(1)).setText(f.getStorageType().toString());
                        // forth one
                        ll = (LinearLayout) base.getChildAt(3);
                        ((TextView)ll.getChildAt(1)).setText(String.format("%d일", f.getDueDate()));
                        parentLayout.addView(foodInfoLayout);
                        foodInfoLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                parentLayout.removeView(foodInfoLayout);
                            }
                        });
                    }
                });


                // when food layout's delete button clicked:
                ImageView icDelete = (ImageView)foodLayout.getChildAt(0);
                icDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) foodLayout.getParent()).removeView(foodLayout);
                        foodList.remove(f);
                    }
                });

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

