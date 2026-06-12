package service;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class QRGenerator {

    public static void generateQR(String data, String path) {

        try {

            Hashtable<EncodeHintType, String> map =
                    new Hashtable<>();

            map.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix matrix =
                    new MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    300,
                    300);

            MatrixToImageWriter.writeToPath(
                    matrix,
                    "PNG",
                    new File(path).toPath());

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}