package com.example.myapplication;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private TextView et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.txt_number1); // ID from component
        //Toast.makeText(this, "Value: " + et1.getText().toString(), Toast.LENGTH_SHORT).show();

        et2 = (EditText) findViewById(R.id.txt_number2); // ID from component
        //Toast.makeText(this, "Value: " + et2.getText().toString(), Toast.LENGTH_SHORT).show();

        et3 = (TextView) findViewById(R.id.textView1); // ID from component
        et3.setText("RESULT: ");
        //Toast.makeText(this, "Result: " + et3.getText().toString(), Toast.LENGTH_SHORT).show();
        // The activity is created
    }

    public void somar(View view) {
        try {
            int sum = Integer.parseInt(et1.getText().toString()) + Integer.parseInt(et2.getText().toString());
            et3.setText("RESULT: " + Integer.toString(sum));
        } catch (NumberFormatException e) {
            et3.setText("RESULT: ");
        }
//code here
    }
}

