package com.app.mvc.captcha;

import com.app.mvc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.net.URL;

/**
 * Created by jimin on 15/11/21.
 */
@Slf4j
public class CaptchaImgFont {

    // 常量，由于太长，放到文件中，服务启动时加载到内存中
    public static String fontStr = null;

    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new ByteArrayInputStream(hex2byte(fontStr)));
            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        } catch (Exception e) {
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

    private byte[] hex2byte(String str) {
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    public static void initImgFont() {
        URL fontUrl = CaptchaImgFont.class.getClassLoader().getResource("font.txt");
        try {
            fontStr = FileUtil.readFile(fontUrl);
        } catch (Exception e) {
            fontStr = "";
        }
    }
}