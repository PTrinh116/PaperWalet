package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;


public class luu_tru_the extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_luu_tru_cac_loai_the);
    }
    public void user(View view){
        Intent intent = new Intent(luu_tru_the.this,thong_tin_user.class);
        startActivity(intent);
    }
}
