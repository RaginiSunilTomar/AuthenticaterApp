package com.gauri.authenticaterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText mname,mEmail,mPaassword,mPhone;
    Button register;
    TextView mlogin;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mname=findViewById(R.id.name);
        mEmail=findViewById(R.id.email);
        mPaassword=findViewById(R.id.passsword1);
        mPhone=findViewById(R.id.phone);
        register=findViewById(R.id.register);
        mlogin=findViewById(R.id.alreadylogin);
        progressBar=findViewById(R.id.progressbar);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPaassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPaassword.setError("password is required");
                    return;
                }
                if (password.length()<6){
                    mPaassword.setError("password must be more than 6 digit ");
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "user successfully created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RegisterActivity.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
