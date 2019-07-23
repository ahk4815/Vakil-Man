package com.example.android.vakilman;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logging extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private Button b;
    public static String email_id="";
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        Intent i=getIntent();
        firebaseAuth=FirebaseAuth.getInstance();
        password=(EditText)findViewById(R.id.password1);
        email=(EditText)findViewById(R.id.email1);
        b=(Button)findViewById(R.id.login);
    }

    public void loginUser(View view)
    {
        email_id +=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email_id,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(logging.this,"valid user",Toast.LENGTH_SHORT).show();
                    Intent description=new Intent(logging.this,description.class);
                    startActivity(description);
                }
                else
                {
                    Toast.makeText(logging.this,"incorrect",Toast.LENGTH_SHORT).show();
                   return;
                }
            }
        });
    }
}
