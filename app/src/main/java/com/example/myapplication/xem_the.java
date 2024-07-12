package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class xem_the extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ImageView imageView;
    private ImageView qrView;
    private String documentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_truoc_va_ma_hoa);
        imageView = findViewById(R.id.imageView3);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            documentType = intent.getStringExtra("document_type");
            if (documentType != null) {
                // Lấy ảnh thẻ và mã QR tương ứng từ cơ sở dữ liệu
                byte[] cardImage = dbHelper.getCardImage(documentType);

                if (cardImage != null) {
                    // Hiển thị ảnh thẻ lên imageView
                    Bitmap cardBitmap = BitmapFactory.decodeByteArray(cardImage, 0, cardImage.length);
                    imageView.setImageBitmap(cardBitmap);
                } else {
                    Toast.makeText(this, "Không tìm thấy ảnh thẻ", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void user(View view) {
        Intent intent = new Intent(xem_the.this, thong_tin_user.class);
        startActivity(intent);
    }
}
