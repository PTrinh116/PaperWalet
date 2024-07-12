package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USER = "user";
    public static final String TABLE_DOCUMENT = "document";

    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USER + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT, "
                + "password TEXT)";

        String createDocumentTable = "CREATE TABLE " + TABLE_DOCUMENT + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id INTEGER, "
                + "document_type TEXT, "
                + "front_image BLOB, "
                + "back_image BLOB, "
                + "qr_code BLOB, "
                + "FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id))";

        db.execSQL(createUserTable);
        db.execSQL(createDocumentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String createDocumentTable = "CREATE TABLE " + TABLE_DOCUMENT + " ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "user_id INTEGER, "
                    + "document_type TEXT, "
                    + "front_image BLOB, "
                    + "back_image BLOB, "
                    + "qr_code BLOB, "
                    + "FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id))";
            db.execSQL(createDocumentTable);
        }
    }

    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_USER + " (username, password) VALUES (?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, username);
        statement.bindString(2, password);
        statement.executeInsert();
        db.close();
    }

    public void addDocument(int userId, String documentType, byte[] frontImage, byte[] backImage, byte[] qrCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_DOCUMENT + " (user_id, document_type, front_image, back_image, qr_code) VALUES (?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindLong(1, userId);
        statement.bindString(2, documentType);
        statement.bindBlob(3, frontImage);
        statement.bindBlob(4, backImage);
        statement.bindBlob(5, qrCode);
        statement.executeInsert();
        db.close();
    }

    public Cursor getDocumentsByUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_DOCUMENT + " WHERE user_id = ?";
        return db.rawQuery(sql, new String[]{String.valueOf(userId)});
    }

    public int checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT id FROM user WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();
        return userId;
    }

    public byte[] getCardImage(String documentType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT front_image FROM " + TABLE_DOCUMENT + " WHERE document_type = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{documentType});
        byte[] imageData = null;
        if (cursor.moveToFirst()) {
            imageData = cursor.getBlob(cursor.getColumnIndex("front_image"));
        }
        cursor.close();
        db.close();
        return imageData;
    }

    public void fetchDataFromServer() {
        NetworkClient networkClient = new NetworkClient(context);
        new Thread(() -> {
            try {
                String response = networkClient.makeRequest("https://localhost/api/data");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
