package com.tigerapi.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @ClassName AESUtils
 * @Description
 * @Author jerry
 * @Since 2022/2/8
 */
public class AESUtils {

    /**
     * AES加密方法
     *
     * @param str 需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String aesEncryption(String str, String password) throws Exception {
        //创建AES的Key生产者
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        //利用用户密码作为随机数初始化出
        keyGenerator.init(128, random);
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
        //根据用户密码，生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //返回基本编码格式的密钥，如果此密钥不支持编码，则返回
        byte[] enCodeFormat = secretKey.getEncoded();
        // 转换为AES专用密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        //创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        //初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //加密
        byte[] result = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return parseByte2HexStr(result);
    }

    /**
     * AES解密方法
     *
     * @param str 需要解密的字符串
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String str, String password) throws Exception {
        //创建AES的Key生产者
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        //利用用户密码作为随机数初始化出
        keyGenerator.init(128, random);
        //根据用户密码，生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //返回基本编码格式的密钥
        byte[] enCodeFormat = secretKey.getEncoded();
        //转换为AES专用密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        //创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        //初始化为解密模式的密码器
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(str)));
        return new String(result);
    }

    /**
     * 二进制转换成十六进制
     *
     * @param buffer 二进制数组
     * @return
     */
    private static String parseByte2HexStr(byte buffer[]) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < buffer.length; i++) {
            String hex = Integer.toHexString(buffer[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            stringBuffer.append(hex.toUpperCase());
        }
        return stringBuffer.toString();
    }

    /**
     * 十六进制转换成二进制数组
     *
     * @param hexStr 十六进制字符串
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
