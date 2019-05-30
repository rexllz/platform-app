package com.scuthku.idiotswithlove.itforum.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String hash(String pwd, String salt)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(pwd.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e) {
            return "Server Internal Error.";
        }
    }
}
