package com.example.android.vakilman;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class post_query extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 234;
    private Button choose,submit;
     private ImageView docimg;
     private Uri filepath;
     public int count =0 ;
     private EditText desc;
    private DatabaseReference mdatabaseReference;
  //  public static String em=logging.email_id;

    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_query);
        Intent post=getIntent() ;
       mStorageRef = FirebaseStorage.getInstance().getReference();
       choose=(Button)findViewById(R.id.upload);
       submit=(Button)findViewById(R.id.submit);
       docimg=(ImageView)findViewById(R.id.doc_image);
       desc=(EditText)findViewById(R.id.write) ;
      /* if(logging.email_id == "")
           em=MainActivity.email_id;
       else
           em=logging.email_id;
       */ mdatabaseReference= FirebaseDatabase.getInstance().getReference("database");
    }
    private void showFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select an image"),PICK_IMAGE_REQUEST) ;
    }
    private void uploadfile() {
        System.out.println("upload file entered function---------------------------------------") ;
        String description=desc.getText().toString().trim();
        if (filepath != null) {
          final ProgressDialog pg=new ProgressDialog(this);
            pg.setTitle("Uploading....");
            pg.show();

             StorageReference riversRef = mStorageRef.child("images/client_doc"+System.currentTimeMillis()+".jpg");
            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         pg.dismiss();
                            chu(taskSnapshot.getUploadSessionUri().toString());

                            Toast.makeText(getApplicationContext(),"uploaded succesfully",Toast.LENGTH_SHORT).show();

                  startActivity(new Intent(post_query.this,success.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            pg.dismiss();
                            Toast.makeText(getApplicationContext(),"error in registration",Toast.LENGTH_SHORT).show();
                        }
                    })
                     .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress =(100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount() ;
                    pg.setMessage(((int)progress) + "%Uploaded...");
                }
            });

        }
        else
        {
            Toast.makeText(post_query.this,"error in chosing file",Toast.LENGTH_SHORT).show();
        }
        /*--------------------------------------
        if (description != null) {
           /* final ProgressDialog pg=new ProgressDialog(this);
            pg.setTitle("Uploading....");
            pg.show();

            StorageReference riversRef = mStorageRef.child("description/client_doc"+count+".jpg");
            riversRef.putFile(description)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //    pg.dismiss();
                            Toast.makeText(getApplicationContext(),"uploaded text succesfully",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                      //      pg.dismiss();
                            Toast.makeText(getApplicationContext(),"error in text uploading",Toast.LENGTH_SHORT).show();
                        }
                    });
                    /*.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount() ;
                            pg.setMessage(((int)progress) + "%Uploaded...");
                        }
                    });

        }
        else
        {
            Toast.makeText(post_query.this,"plz write again",Toast.LENGTH_SHORT).show();
        }
*/

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
          filepath=data.getData();
          try{
              Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
              docimg.setImageBitmap(bitmap);
          }
          catch(IOException e){
              System.out.println("error") ;
        }
        }
       // System.out.println("--------------------------------------"+filepath+"------------------------------------") ;
    }

    public void submit_doc(View view)
    {

        uploadfile();
        count++;
    }
    public void choosefile(View view)
    {
        showFileChooser();
    }

    public void chu(String x)
    {
        System.out.println("-------------------------------chu-----------------------------");
        System.out.println("-----------------------"+x+"----------------------------------") ;
        String uid=mdatabaseReference.push().getKey();
        System.out.println("-------------------------"+uid+"--------------------");
        Upload upload =new Upload(uid,desc.getText().toString().trim(),x);
        System.out.println("--------------------------done--------------------");
        mdatabaseReference.child(uid).setValue(upload);
    }
}
