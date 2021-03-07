package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import android.os.Bundle;

public class ShowMessageActivity extends AppCompatActivity {

    private TextView ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
        ed1 = (TextView) findViewById(R.id.textView1);
        String data = getIntent().getStringExtra("data");
        ed1.setText(data);
    }
}