package com.example.teamproject03.capture;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teamproject03.R;

public class CheckingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_checking);

        TextView cancelButton = (TextView) findViewById(R.id.cancelButton);
        TextView recaptureButton = (TextView) findViewById(R.id.recaptureButton);
        TextView addButton = (TextView) findViewById(R.id.addButton);



    }
}
