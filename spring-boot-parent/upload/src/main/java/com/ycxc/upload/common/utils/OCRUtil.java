package com.ycxc.upload.common.utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class OCRUtil {

    private static String ocr(String filePath) {
        String result = null;
        try {
            double start = System.currentTimeMillis();
            BufferedImage textImage = ImageIO.read(new File(filePath));
            // 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分
            textImage = ImageHelper.convertImageToGrayscale(textImage);
            // 图片锐化
            textImage = ImageHelper.convertImageToBinary(textImage);
            // 图片放大倍数,增强识别率(很多图片本身无法识别,放大5倍时就可以轻易识,但是考滤到客户电脑配置低,针式打印机打印不连贯的问题,这里就放大5倍)
            textImage = ImageHelper.getScaledInstance(textImage, textImage.getWidth() * 1, textImage.getHeight() * 1);
            textImage = ImageHelper.convertImageToBinary(textImage);
            ImageIO.write(textImage, "png", new File("E:\\test\\img_temp.jpg"));
            Tesseract instance = new Tesseract();
            instance.setDatapath(System.getProperty("user.dir") + "\\tessdata");//设置训练库的位置
            instance.setLanguage("chi_simmm");//中文识别
            instance.setLanguage("chi_simm");//中文识别
            instance.setLanguage("chi_sim");//中文识别
            //instance.setLanguage("chi_tra");//中文识别
            //instance.setLanguage("eng");//英文识别
            result = instance.doOCR(textImage);
            double end = System.currentTimeMillis();
            System.out.println("耗时" + (end - start) / 1000 + " s");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ocr("C:\\Users\\Administrator\\Pictures\\Camera Roll\\timg.jpg"));
    }
}
