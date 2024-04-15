package cn.edu.pzhu.mytest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码相关测试
 *
 * @author zhangcz
 * @since 20201028
 */
@SpringBootTest
public class QRCodeTest {


    @Test
    @SneakyThrows
    public void testEncode() {
        String path = "/Users/zhangchao/Desktop/qrcode.png";
        String text = "https://u.wechat.com/ECIZRjz8uytw49U1CdLePYU";
        int width = 1000;
        int height = 1000;

        encode(text, width, height, path);
    }


    @Test
    @SneakyThrows
    public void testDecode() {
        String path = "/Users/zhangchao/Desktop/WechatCG.jpeg";
        String result = decode(path);

        System.out.println(result);
    }


    /**
     * 解析二维码
     *
     * @param path 二维码路径
     * @return 二维码内容
     */
    public String decode(String path) throws Exception {
        File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        QRCodeReader reader = new QRCodeReader();
        Map<DecodeHintType, Object> map = new HashMap<>();
        map.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Result result = reader.decode(binaryBitmap, map);
        return result.getText();
    }

    /**
     * 生成二维码
     *
     * @param text     二维码内容
     * @param width    宽
     * @param height   高
     * @param filePath 二维码文件输出路径
     */
    public void encode(String text, int width, int height, String filePath) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Map<EncodeHintType, Object> map = new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        map.put(EncodeHintType.MARGIN, 5);

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, map);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
    }
}
