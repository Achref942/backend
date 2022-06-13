package com.example.pointini.controllers;
import com.example.pointini.services.QRCodeService;
import com.example.pointini.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
@Controller
@RequestMapping("/test")

public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    private UserService userService;



    @PostMapping("/showQRCode")
    public String showQRCode(Model model) {
        model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + "qrContent");
        return "show-qr-code";
    }

    @GetMapping(path = "/generateQRCode/{id}")
    public void generateQRCode(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        byte[] qrCode = qrCodeService.generateQRCode(userService.findUserById(id).toString(), 500, 500);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCode);
    }
}
