package com.example.android.vakilman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class success extends AppCompatActivity {
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        txt=(EditText)findViewById(R.id.ref);

        txt.setText("REFERENCE NO :"+System.currentTimeMillis());
        Toast.makeText(this,"please save it for future refernce",Toast.LENGTH_SHORT).show();

    }
}
