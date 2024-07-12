package com.example.myapplication;

public class Document {
    private int id;
    private int userId;
    private String docType;
    private byte[] frontImage;
    private byte[] backImage;
    private byte[] qrCode;

    // Constructor, getters và setters
    public Document(int id, int userId, String docType, byte[] frontImage, byte[] backImage, byte[] qrCode) {
        this.id = id;
        this.userId = userId;
        this.docType = docType;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.qrCode = qrCode;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getDocType() { return docType; }
    public void setDocType(String docType) { this.docType = docType; }

    public byte[] getFrontImage() { return frontImage; }
    public void setFrontImage(byte[] frontImage) { this.frontImage = frontImage; }

    public byte[] getBackImage() { return backImage; }
    public void setBackImage(byte[] backImage) { this.backImage = backImage; }

    public byte[] getQrCode() { return qrCode; }
    public void setQrCode(byte[] qrCode) { this.qrCode = qrCode; }
}
