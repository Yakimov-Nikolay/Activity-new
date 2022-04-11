package activity_new.activity_new.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeService {

    byte [] generateQRCode(String qrContent, int width, int height) throws WriterException, IOException;
}
