package com.example.android.vakilman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class description extends AppCompatActivity {
    Button pq,call,bk,m ;
    Spinner sp;
    private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        final Intent description = getIntent() ;
    pq=(Button)findViewById(R.id.post_query);
    call=(Button)findViewById(R.id.call);
        bk=(Button)findViewById(R.id.book);
       m=(Button)findViewById(R.id.mail);
        sp=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> myadapter=ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_spinner_item);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(myadapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1)
                    startActivity(new Intent(description.this,about_us.class));
                if(position==2)
                    startActivity(new Intent(description.this,services.class));
                if(position==3)
                    startActivity(new Intent(description.this,chambers.class));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void postQuery(View view)
    {
       Intent post=new Intent(description.this,post_query.class);
       startActivity(post);
    }
    public void calling(View view)
    {
        String number="8582878052";
        Intent it=new Intent(Intent.ACTION_CALL);
        it.setData(Uri.parse("tel:"+number)) ;

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
            requestStoragePermission();
        }
        else{

          startActivity(it);}
    }
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CALL_PHONE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(description.this,
                                    new String[] {Manifest.permission.CALL_PHONE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CALL_PHONE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void booking(View view)
    {
        Intent book_cal=new Intent(description.this,appointment.class);
        startActivity(book_cal);
    }

    public void mailing(View view)
    {Intent intent=null,chooser=null;
    intent =new Intent(Intent.ACTION_SEND);
    intent.setData(Uri.parse("mailto:"));
    String[] to={"ahk4815@gmail.com"} ;
    intent.putExtra(Intent.EXTRA_EMAIL,to);
    intent.setType("message/rfc822");
    chooser=Intent.createChooser(intent,"Send Email");
    startActivity(chooser);

    }
}
