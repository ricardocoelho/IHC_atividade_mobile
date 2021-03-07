package com.example.myapplication;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.txt_number1); // ID from component
        //Toast.makeText(this, "Value: " + et1.getText().toString(), Toast.LENGTH_SHORT).show();

    }

    public void sendMsg(View view) {
        Intent i = new Intent(this, ShowMessageActivity.class);
        i.putExtra("data", et1.getText().toString());
        startActivity(i);
    }


}

