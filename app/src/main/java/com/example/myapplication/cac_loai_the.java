package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class cac_loai_the extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cac_loai_the);
        NetworkClient networkClient = new NetworkClient(this);

        new Thread(() -> {
            try {
                String response = networkClient.makeRequest("https://localhost/api/endpoint");
                runOnUiThread(() -> {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void cccd(View view){
        String text ="Chụp ảnh thẻ căn cước công dân";
        Intent intent= new Intent(cac_loai_the.this,xem_the.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }
    public void bank(View view){
        String text ="Chụp ảnh thẻ ngân hàng";
        Intent intent= new Intent(cac_loai_the.this,xem_the.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }
    public void bang_lai_xe(View view){
        String text ="Chụp ảnh bằng lái xe";
        Intent intent= new Intent(cac_loai_the.this,xem_the.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }
    public void the_mua_sam(View view){
        String text ="Chụp ảnh thẻ thành viên mua sắm";
        Intent intent= new Intent(cac_loai_the.this,xem_the.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }
    public void the_y_te(View view){
        String text ="Chụp ảnh thẻ bảo hiểm y tế";
        Intent intent= new Intent(cac_loai_the.this,xem_the.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }
    public void khac(View view){
        String text ="Chụp ảnh thẻ của bạn";
        Intent intent= new Intent(cac_loai_the.this,xem_the.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }
    public void user(View view){
        Intent intent = new Intent(cac_loai_the.this,thong_tin_user.class);
        startActivity(intent);
    }
    public void add(View view){
        Intent intent = new Intent(cac_loai_the.this,chon_loai_the_muon_them.class);
        startActivity(intent);
    }
}