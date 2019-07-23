package com.example.android.vakilman;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class chambers extends AppCompatActivity {
TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chambers);
        tx=(TextView)findViewById(R.id.location);


    }

    public void mmaps(View view)
    {
        Intent intent =null;
        Intent chooser=null;
        intent = new Intent(android.content.Intent.ACTION_VIEW);
       intent.setData(Uri.parse("geo:22.565279,88.353302"));
      //  intent.setData(Uri.parse("geo:19.076,72.8777"));
       // chooser=Intent.createChooser(intent,"LAUNCH MAP");
        //startActivity(chooser);
    startActivity(intent);
    }
}
