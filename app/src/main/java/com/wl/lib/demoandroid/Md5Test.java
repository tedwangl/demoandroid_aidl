package com.wl.lib.demoandroid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：author wl
 * 时间：2019/7/19:10:34
 * 说明：
 */
public class Md5Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String sc = "THIS IS A MD5 TEST STRING";
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] bytes = md.digest(sc.getBytes());
        System.out.println(bytesToHex(bytes));
        for (byte b : bytes) {
            System.out.println(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        //把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if(digital < 0) {
                digital += 256;
            }
            if(digital < 16){
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }



}
