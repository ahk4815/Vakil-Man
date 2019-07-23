package com.example.android.vakilman;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class appointment extends AppCompatActivity {
    CalendarView calendar;
    DatabaseReference db;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent book_cal=getIntent();
        name=(EditText)findViewById(R.id.name);
        calendar=(CalendarView)findViewById(R.id.cal);
        db= FirebaseDatabase.getInstance().getReference("schedule");
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

               String username=name.getText().toString().trim();
               if(!TextUtils.isEmpty(username)) {
                   Toast.makeText(getBaseContext()," selected "+dayOfMonth+"/"+month+"/"+year,Toast.LENGTH_LONG).show() ;
                   String id = db.push().getKey();
                   Book_appointment bk = new Book_appointment(id, dayOfMonth, month, year,username);
                   db.child(id).setValue(bk);

               }
               else
               {
                   System.out.println("--------------xxxxxxxxxxxxxherexxxxxxxxxxxxxx----------------------------------------------------------");
                   Toast.makeText(getApplicationContext(),"plzz enetr your name",Toast.LENGTH_SHORT).show() ;
               }
            }
        });
    }

}
