package com.app.mvc.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureUtils {
    // 获得时间戳
    public static long GetTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    // 获得签名
    public static String CalculationSignature(String timeStamp, String allianceID, String secretKey, String sid, String requestType)
            throws NoSuchAlgorithmException, Exception {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(timeStamp);

        if (Integer.parseInt(allianceID) > 0) {
            stringBuilder.append(allianceID);
        }

        if (secretKey != null && !secretKey.equals("")) {
            String sectetKeyMD5 = MD5Encoding(secretKey).toUpperCase();
            stringBuilder.append(sectetKeyMD5);
        } else {
            throw new Exception("SecretKey should not be null or empty!");
        }

        if (Integer.parseInt(sid) > 0) {
            stringBuilder.append(sid);
        }

        if (requestType != null && !requestType.trim().equals("")) {
            stringBuilder.append(requestType);
        }

        String result = stringBuilder.toString();
        String signature = "";
        try {
            signature = MD5Encoding(result).toUpperCase();
        } catch (NoSuchAlgorithmException e) {

        }
        return signature;
    }

    // MD5加密
    private static String MD5Encoding(String source) throws NoSuchAlgorithmException {
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        byte[] input = source.getBytes();
        mdInst.update(input);
        byte[] output = mdInst.digest();

        int i = 0;

        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < output.length; offset++) {
            i = output[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
}