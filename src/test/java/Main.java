import com.google.zxing.WriterException;

import java.io.IOException;

public class Main {
    public static void main( String[] args ) throws IOException, WriterException {
        QRUtils qr = new QRUtils();
        qr.getQRCode("小黄毛");
    }
}
