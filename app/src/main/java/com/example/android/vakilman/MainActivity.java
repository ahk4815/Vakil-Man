package com.example.android.vakilman;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
     private EditText email;
    private EditText password;
    private Button signin;
    private TextView loggin;
    private FirebaseAuth firebaseAuth;
   private ProgressDialog progressDialog;
   public static String email_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        loggin=(TextView)findViewById(R.id.login);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        signin=(Button)findViewById(R.id.signin);

    }

    public void register(View view)
    {
         email_id +=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if((TextUtils.isEmpty(email_id))||(TextUtils.isEmpty(email_id)))
        {
            Toast.makeText(this,"please enter email and password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering message");
       progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email_id,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"registered succesfully",Toast.LENGTH_SHORT).show();
                    Intent description=new Intent(MainActivity.this,description.class);
                    startActivity(description);
                    progressDialog.hide();
                //    Intent description=new Intent(MainActivity.this,description.class);
                  //  startActivity(description);
                }
            }
        });

    }
    public void log_in(View view)
    {
        Intent i=new Intent(MainActivity.this,logging.class);
        startActivity(i);


    }
}
