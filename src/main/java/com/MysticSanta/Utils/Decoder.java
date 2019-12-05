package com.MysticSanta.Utils;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.function.BiPredicate;

@Component
public class Decoder {

    private static Cipher initCipher(byte[] keyBytes, int mode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        final IvParameterSpec iv = new IvParameterSpec(new byte[16]);

        cipher.init(mode, key, iv);
        return cipher;
    }

    //не работает
    public static byte[] decode(String message, String userKey) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        Cipher cipher = initCipher(userKey.getBytes("UTF-8"), Cipher.DECRYPT_MODE);
        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    //не работает
    public static String encode(byte[] mes, String userKey) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        Cipher cipher = initCipher(userKey.getBytes("UTF-8"), Cipher.ENCRYPT_MODE);

        return new String(cipher.doFinal(mes));
    }

    public static byte[] primitiveDecode(String message, String userKey) {
        byte[] mes = message.getBytes();
        char[] keyChars = userKey.toCharArray();
        int key = 0;
        for (char keyChar : keyChars) {
            key += keyChar;
        }
        for (int i = 0; i < mes.length; i++) {
            mes[i] = (byte) (mes[i] ^ key);
        }

        return mes;
    }

    public static String primitiveEncode(byte[] mes, String userKey) {

        char[] keyChars = userKey.toCharArray();
        int key = 0;
        for (char keyChar : keyChars) {
            key += keyChar;
        }

        for (int i = 0; i < mes.length; i++) {
            mes[i] = (byte) (mes[i] ^ key);
        }
        return new String(mes);
    }

    public static void main(String[] args) {
        byte[] bytes = Decoder.primitiveDecode("Павел Зубков", "1234456789123456");
        for (byte b : bytes) System.out.println(b);
        String ss = Decoder.primitiveEncode(bytes, "1234456789123456");
        System.out.println(ss);
    }
}