package com.example.teamproject03.capture;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teamproject03.MainActivity;
import com.example.teamproject03.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CheckingActivity extends AppCompatActivity {
    LinearLayout scrollLinearLayout;
    TextView cancelButton;
    TextView recaptureButton;
    TextView addButton;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_checking);

        scrollLinearLayout = (LinearLayout) findViewById(R.id.scrollLinearLayout);
        cancelButton = (TextView) findViewById(R.id.cancelButton);
        recaptureButton = (TextView) findViewById(R.id.recaptureButton);
        addButton = (TextView) findViewById(R.id.addButton);

        ArrayList<String> captureList = (ArrayList<String>) getIntent().getSerializableExtra("captureList");
        inflater = LayoutInflater.from(this);
        ArrayList<LinearLayout> layoutList = new ArrayList<LinearLayout>();

        // first of all show capture list
        for(int i=0; i<captureList.size(); i++){
            LinearLayout foodLayout = (LinearLayout) inflater.inflate
                    (R.layout.capture_food_button, scrollLinearLayout, false);
            ((TextView)foodLayout.findViewById(R.id.name)).setText(captureList.get(i));
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
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // check each food's information(due date, storage type, etc...)

                // add SQL food items

                startActivity(intent);
            }
        });




    }
}
