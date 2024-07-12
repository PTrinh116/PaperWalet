package com.example.myapplication;

import static com.example.myapplication.giao_dien_chup_anh.REQUEST_IMAGE_CAPTURE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class chup_the extends AppCompatActivity {
    private TextView textView;
    private DatabaseHelper dbHelper;
    private String documentType;
    private byte[] frontImage;
    private byte[] backImage;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chup_the);
        textView = findViewById(R.id.textView4);
        dbHelper = new DatabaseHelper(this);

        // Get userId from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);

        if (userId == -1) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra("text");
            if (text != null) {
                textView.setText(text);
            }
        }
    }

    public void front(View view) {
        Intent intent = new Intent(chup_the.this, giao_dien_chup_anh.class);
        intent.putExtra("is_front_image", true); // Đặt cờ là chụp mặt trước
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void back(View view) {
        Intent intent = new Intent(chup_the.this, giao_dien_chup_anh.class);
        intent.putExtra("is_front_image", false); // Đặt cờ là chụp mặt sau
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            byte[] image = data.getByteArrayExtra("captured_image");
            if (image != null) {
                if (requestCode == REQUEST_IMAGE_CAPTURE) {
                    if (data.getBooleanExtra("is_front_image", true)) {
                        frontImage = image;
                        Toast.makeText(this, "Front image captured", Toast.LENGTH_SHORT).show();
                    } else {
                        backImage = image;
                        Toast.makeText(this, "Back image captured", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public void saveDocumentType(View view) {
        documentType = textView.getText().toString();
        if (frontImage != null && backImage != null) {
            byte[] qrCode = generateQRCode(frontImage, backImage);
            dbHelper.addDocument(userId, documentType, frontImage, backImage, qrCode);
            Toast.makeText(this, "Document saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please capture both front and back images", Toast.LENGTH_SHORT).show();
        }
    }

    private byte[] generateQRCode(byte[] frontImage, byte[] backImage) {
        // Generate a QR code from the front and back images
        // This is a placeholder implementation
        Bitmap qrCodeBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        qrCodeBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
