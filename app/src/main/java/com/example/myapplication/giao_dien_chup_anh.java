package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class giao_dien_chup_anh extends AppCompatActivity {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private byte[] capturedImage; // Biến để lưu hình ảnh chụp
    private boolean isFrontImage; // Biến để xác định xem hình ảnh được chụp là mặt trước hay mặt sau

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giao_dien_chup_anh);
        imageView = findViewById(R.id.imageView3);
        isFrontImage = getIntent().getBooleanExtra("is_front_image", true); // Mặc định là chụp mặt trước
        takePicture();
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Không tìm thấy ứng dụng camera.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            imageView.setVisibility(View.VISIBLE); // Hiển thị imageView khi có ảnh

            // Chuyển đổi bitmap thành mảng byte và lưu vào capturedImage
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            capturedImage = stream.toByteArray();
        }
    }

    public void saveAndReturn(View view) {
        if (capturedImage != null) {
            // Truyền mảng byte của ảnh chụp qua Intent
            Intent returnIntent = new Intent();
            returnIntent.putExtra("captured_image", capturedImage);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else {
            Toast.makeText(this, "Chưa chụp ảnh.", Toast.LENGTH_SHORT).show();
        }
    }

    public void take_pt(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Không tìm thấy ứng dụng camera.", Toast.LENGTH_SHORT).show();
        }
    }
}
