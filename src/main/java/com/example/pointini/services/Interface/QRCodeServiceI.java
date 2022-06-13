package com.example.pointini.services.Interface;

public interface QRCodeServiceI {
    byte[] generateQRCode(String qrContent, int width, int height);

}
