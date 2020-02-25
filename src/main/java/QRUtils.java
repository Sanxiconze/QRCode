import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class QRUtils {

    int width = 300;
    int height = 300;
    String format = "png";
    HashMap hints;
    BitMatrix bitMatrix;
    QRUtils(){
        hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN,2);
    }

    String getQRCode(String text) throws WriterException, IOException {
        bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,width,height,hints);
        File file = new File("d:/image.png");
        writeToPath(bitMatrix,format,file);
        return file.toString();
    }
    public static void writeToPath( BitMatrix bitMatrix, String format, File file){
        int matrixWidth = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < image.getWidth();i++){
            for(int j = 0; j < image.getHeight();j++){
                image.setRGB(i,j,bitMatrix.get(i,j) == true?0:0xFFFFFF);
            }
        }
        try {
            ImageIO.write(image,format,new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }
}
