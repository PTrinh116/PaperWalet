package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import okhttp3.OkHttpClient;

public class Dang_nhap extends AppCompatActivity {
    EditText iedtEmail, iedtPassword;
    TextView btn_signIn;
    TextView tv_signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        mAuth= FirebaseAuth.getInstance();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApiKey("AIzaSyCk0ZtRAA6A9-Bdpe4e9Q28Ay4B9h7CeQw")
                .setApplicationId("1:1021265905251:android:b458c1f5b8e924c88d9a56")
                .build();

        iedtEmail = findViewById(R.id.editText3);
        iedtPassword = findViewById(R.id.editText4);
        btn_signIn = findViewById(R.id.button2);
        tv_signUp = findViewById(R.id.textView2);

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Dang_nhap.this,dang_ky.class);
                startActivity(intent);
                finish();
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    public void login(){
        String email, password;
        email = iedtEmail.getText().toString().trim();
        password = iedtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Dang_nhap.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(Dang_nhap.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Sign in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dang_nhap.this, cac_loai_the.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}